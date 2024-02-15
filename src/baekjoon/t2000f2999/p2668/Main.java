/**
 * 문제 이름 : 숫자고르기
 * URL : https://www.acmicpc.net/problem/2668
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static int N;
    static int[] arr;
    static HashSet<Integer> hashSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        check();
    }

    static void check() {
        boolean[] cycle = new boolean[N + 1];
        int cnt = 0;
        cnt += initCycle(cycle);

        for (int i = 1; i <= N; i++) {
            if (cycle[i])
                continue;

            hashSet = new HashSet<>();
            if (checkCycle(i, i)) {
                for (int num : hashSet) {
                    cycle[num] = true;
                    cnt += 1;
                }
            }
        }

        System.out.println(cnt);
        for (int i = 1; i <= N; i++) {
            if (cycle[i])
                System.out.println(i);
        }
    }

    static boolean checkCycle(int startNum, int x) {
        // startNum => 시작 수로, 다시 이 수로 돌아온다면 사이클이 발생한 것
        // x => 다음에 갈 수
        // hashSet => 지금까지 나온 수의 집합
        if (hashSet.size() != 0 && startNum == x) {
            // 사이클이 발생한 것
            return true;
        }
        if (hashSet.contains(x)) {
            // 현재 수로 사이클이 발생하지 않은 것 => 다른 수로 사이클이 발생한 것
            return false;
        }
        int nx = arr[x];
        hashSet.add(x);
        return checkCycle(startNum, nx);
    }

    static int initCycle(boolean[] cycle) {
        // 자기 자신으로 가는 것에 먼저 true 해주기
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (arr[i] == i) {
                cycle[i] = true;
                cnt += 1;
            }
        }
        return cnt;
    }
}

/*
    사이클 판별 문제인 것 같음 => 사이클은 다 출력하면 됨

    [사이클 판별 방법]
    1. dfs로 하기
    2. union-find로 하기

    나는 dfs로 할 것
    => dfs를 할 때, 처음 시작한 수를 매개변수로 담고 시작하기... 이 때, 방문 처리가 된 것이 나온다면 그대로 종료
    => 여기서 방문 처리된 것이 자기 자신이면 사이클이 생긴 것
    => 사이클의 결과는 true, false로
    => 사이클이 생기는 숫자는 true로, 아닌것은 false로 하기
 */