/**
 * 문제 이름 : 배
 * URL : https://www.acmicpc.net/problem/1092
 * 문제 설명 :
 * 크레인에 무게 제한이 있고, 이 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없때, 모든 박스를 옮기는데 드는 시간의 최솟값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] cranes;
    static int M;
    static int[] boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cranes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        M = Integer.parseInt(br.readLine());
        boxes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        if (cranes[N - 1] < boxes[M - 1]) {
            System.out.println(-1);
        } else {
            System.out.println(put());
        }
    }

    static int put() {
        boolean[] visited = new boolean[M];

        int time = 0;
        int cnt = 0;


        while (true) {
            if (cnt == M) {
                break;
            }

            int idx = cranes.length - 1;

            for (int i = M - 1; i >= 0; i--) {
                if (idx < 0)
                    break;
                if (visited[i])
                    continue;

                int box = boxes[i];
                if (cranes[idx] >= box) {
                    idx--;
                    cnt += 1;
                    visited[i] = true;
                }
            }

            time += 1;
        }

        return time;
    }
}
