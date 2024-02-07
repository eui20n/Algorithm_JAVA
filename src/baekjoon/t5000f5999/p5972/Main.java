/**
 * 문제 이름 : 택배 배송
 * URL : https://www.acmicpc.net/problem/5972
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t5000f5999.p5972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<int[]>> graphInfo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        M = tmpInput[1];

        makeGraph();
        for (int i = 0; i < M; i++) {
            int[] tmpInput2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 양 방향으로 갈 수 있으니 양 방향으로 값 넣어주기
            graphInfo.get(tmpInput2[0]).add(new int[] {tmpInput2[1], tmpInput2[2]});
            graphInfo.get(tmpInput2[1]).add(new int[] {tmpInput2[0], tmpInput2[2]});
        }
        check();
    }

    static void check() {
        int[] dist = makeArr();
        dist[1] = 0;

//        Deque<int[]> q = new ArrayDeque<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        q.add(new int[] {1, 0}); // 출발 위치, 비용

        while (true) {
            if (q.isEmpty())
                break;

            int[] p = q.poll();
            int x = p[0];
            int cost = p[1];

            if (x == N) {
                break;
            }

            for (int[] roadArr : graphInfo.get(x)) {
                int nx = roadArr[0];
                int nCost = roadArr[1] + cost;

                if (dist[nx] <= nCost)
                    continue;

                q.add(new int[] {nx, nCost});
                dist[nx] = nCost;
            }
        }
        System.out.println(dist[N]);
    }

    static int[] makeArr() {
        int[] returnArr = new int[N + 1];

        for (int i = 0; i < returnArr.length; i++) {
            returnArr[i] = Integer.MAX_VALUE;
        }
        return returnArr;
    }

    static void makeGraph() {
        graphInfo = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graphInfo.add(new ArrayList<>());
        }
    }
}

/*
    다익스트라
 */