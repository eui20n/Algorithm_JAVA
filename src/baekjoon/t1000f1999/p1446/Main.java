/**
 * 문제 이름 : 지름길
 * URL : https://www.acmicpc.net/problem/1446
 * 문제 설명 : 고속도로에 지름길이 있다. 이 때, 운전해야하는 최소값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1446;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int D;
    static int[][] shortcutArr;
    static int[] distance = new int[10001]; // 각 배열의 위치는 현재 위치까지 오는데 걸리는 최소 시간
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        N = Integer.parseInt(tmp[0]);
        D = Integer.parseInt(tmp[1]);

        shortcutArr = new int[N][3];

        for (int i = 0; i < N; i++) {
            shortcutArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();
    }

    static void check() {
        // 가기
        go();

        System.out.println(distance[D]);
    }

    static void go() {
        distanceInit();
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[] {0, 0}); // 현재 위치, 그 위치까지 가는 비용
        distance[0] = 0;

        while (true) {
            if (q.isEmpty())
                break;

            int[] p = q.poll();
            int x = p[0];
            int cost = p[1];

            if (x > D)
                continue;

            // 반복문 => 현재 위치에서 갈 수 있는 지름길이 있는지 확인하기
            for (int[] shortcut : shortcutArr) {
                if (x == shortcut[0] && distance[shortcut[1]] > cost + shortcut[2]) {
                    distance[shortcut[1]] = cost + shortcut[2];
                    q.add(new int[] {shortcut[1], cost + shortcut[2]});
                }
            }
            if (x + 1 > D)
                continue;
            if (distance[x + 1] > cost + 1) {
                distance[x + 1] = cost + 1;
                q.add(new int[] {x + 1, cost + 1});
            }
        }
    }

    static void distanceInit() {
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
    }
}


/*
        다익스트라
 */