/**
 * 문제 이름 : 효율적인 해킹
 * URL : https://www.acmicpc.net/problem/1325
 * 문제 설명 :
 * 한 번의 해킹으로 여러 개의 컴퓨터를 해킹할 수 있다. 이 때, 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] cost;
    static int cnt = 0;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];

        makeList();
        cost = new int[N];
        visited = new int[N];
        Arrays.fill(visited, -1);

        for (int i = 0; i < M; i++) {
            int[] tmp2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp2[0] - 1;
            int b = tmp2[1] - 1;

            list.get(b).add(a);
        }
        check();
    }

    static void check() {
        List<Integer> maxIdx = new ArrayList<>();
        int maxValue = 0;

        for (int i = 0; i < N; i++) {
            if (list.get(i).size() == 0)
                continue;
            cnt = 0;
            dfs(i, i);
            maxValue = hacking(maxIdx, maxValue, i);

        }
//        System.out.println(Arrays.toString(cost));
        print(maxIdx);
    }

    static void print(List<Integer> maxIdx) {
        StringBuilder sb = new StringBuilder();
        for (int idx : maxIdx) {
            sb.append(idx).append(" ");
        }
        System.out.println(sb);
    }

    static int hacking(List<Integer> maxIdx, int maxNum, int idx) {
        if (maxNum < cnt) {
            maxIdx.clear();
            maxIdx.add(idx + 1);
            maxNum = cnt;
        } else if (maxNum == cnt) {
            maxIdx.add(idx + 1);
        }
        return maxNum;
    }

    static void dfs(int start, int idx) {
        visited[idx] = start;

        for (int nextIdx : list.get(idx)) {
            cnt += 1;
            if (visited[nextIdx] == start) {
                continue;
            }
            dfs(start, nextIdx);
        }
    }

    static void makeList() {
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }
    }
}

// 이거 최대 10억번 연산이 일어나네 => 1만개의 컴퓨터가 연결되어 있는 경우(사이클)