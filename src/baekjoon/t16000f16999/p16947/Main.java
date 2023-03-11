/**
 * 문제 이름 : 서울 지하철 2호선
 * URL : https://www.acmicpc.net/problem/16947
 * 문제 설명 :
 * 순환선 => 한 역에서 출발해서 다시 출발한 역으로 돌아올 수 있는 노선
 * 지선 => 순환선에 속하는 한 역에서 시작하는 트리 형태의 노선
 * 역 A와 순환선 사이의 거리는 A와 순환선에 속하는 역 사이의 거리중 최솟값이다.
 * 지하철 2호선과 같은 형태의 노선도가 주어졌을 때, 각 역과 순환선 사이의 거리를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16947;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static List<List<Integer>> subway;
    static boolean[] cycle;
    static int[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        subway = new ArrayList<>();
        cycle = new boolean[N + 1];
        visited = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            subway.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            int subway1 = Integer.parseInt(tmp[0]);
            int subway2 = Integer.parseInt(tmp[1]);

            subway.get(subway1).add(subway2);
            subway.get(subway2).add(subway1);
        }
        distByCycle();
        System.out.println(sb);
    }

    static void distByCycle() {
        findCycle(); // 순환선 찾기
        // 거리구하기
        // 기준 1. 순환선이 아닌 것 => 순환선까지의 거리인데, 이미 순환선까지의 거리를 구한게 있다면 거기까지만 구해서 더해주는 방식 => 메모리 제이션
        // 기준 2. 순환선을 기준 => 순환선이 아닌 것을 만나면 거기서 부터 그냥 거리를 구하기 => 나는 이 방식
        int[] result = operationDist();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
    }

    static int[] operationDist() {
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!cycle[i])
                continue;
            checkDist(dist, i, 0);
        }

        return dist;
    }

    static void checkDist(int[] distArr, int node, int dist) {
        for (int nextNode : subway.get(node)) {
            if (cycle[nextNode])
                continue;
            distArr[nextNode] = dist + 1;
            cycle[nextNode] = true;
            checkDist(distArr, nextNode, dist + 1);
        }
    }
    static void findCycle() {
        for (int i = 1; i <= N; i++) {
            visited[i] = i;
            checkCycle(i, -1, i);
        }
    }

    static void checkCycle(int node, int preNode, int visitedNum) {
        for (int nextNode : subway.get(node)) {
            if (nextNode == preNode)
                continue;
            if (nextNode == visitedNum) {
                cycle[nextNode] = true;
                return;
            }
            if (visited[nextNode] == visitedNum)
                continue;
            if (cycle[visitedNum])
                return;
            visited[nextNode] = visitedNum;
            checkCycle(nextNode, node, visitedNum);
        }

    }

    static void print(List<List<Integer>> list) {
        for (List<Integer> tmp : list) {
            System.out.println(Arrays.toString(tmp.toArray()));
        }
    }
}

// 연산 순서
// 1. 순환선 찾기
// 2. 그 사이의 거리 구하기

// 시간 복잡도 줄여보기 => dfs 한번에 순환인지 아닌지 찾을 수 있음
// 혹은 순환인지 아닌지를 다른 방법으로 찾을 수 있으면 찾아보기

/*
        정리
    순환선은 무조건 있음
    노드의 수와 간선의 수가 같음 => 순환이 무조건 1개만 생김, 이유는 아래 적혀있음
    트리의 간선은 노드 - 1개임 => 이 문제는 노드와 간선이 같음 => 노드중 한 곳만 연결이 되어 있음
    문제에서 순환선을 제외하면 트리형태라고 함
 */

