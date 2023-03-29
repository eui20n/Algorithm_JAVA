/**
 * 문제 이름 : 다리 놓기
 * URL : https://www.acmicpc.net/problem/1010
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 조합을 DP를 활용해서 나타내기
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    static int[][] comb = new int[31][31];
    public static void main(String[] args) throws IOException {
        makeComb();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N, M;
            String[] tmp = br.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            M = Integer.parseInt(tmp[1]);

            System.out.println(comb[M + 1][N + 1]);
        }
    }

    static void makeComb() {
        comb[1][1] = 1;

        for (int n = 2; n < 31; n++) {
            for (int m = 1; m < 31; m++) {
                comb[n][m] = comb[n - 1][m - 1] + comb[n - 1][m];
            }
        }

    }
}

// 파스칼 삼각형
// arr[N][M] = arr[N - 1][M - 1] + arr[N - 1][M]