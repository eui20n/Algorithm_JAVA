/**
 * 문제 이름 : 쿼드 트리
 * URL : https://www.acmicpc.net/problem/1992
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        check(0, 0, N);
        System.out.print(sb);
    }

    static void check(int x, int y, int size) {
        int sum = 0;

        for (int i = x; i < size + x; i++) {
            for (int j = y; j < size + y; j++) {
                if (arr[i][j] == 1)
                    sum += 1;
            }
        }

        if (sum == size * size) {
            sb.append(1);
        } else if (sum == 0) {
            sb.append(0);
        } else {
            int half = size / 2;
            sb.append("(");
            check(x, y, half);
            check(x, y + half, half);
            check(x + half, y, half);
            check(x + half, y + half, half);
            sb.append(")");
        }
    }
}
