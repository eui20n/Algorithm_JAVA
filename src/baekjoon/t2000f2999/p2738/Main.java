/**
 * 문제 이름 : 행렬 덧셈
 * URL : https://www.acmicpc.net/problem/2738
 * 문제 설명 : 행렬 2개가 입력으로 들어옴, 그 2개의 행렬을 서로 더해주면 됨
 * 시간복잡도 : O(NM) -> 행렬의 크기 N x M
 * 핵심 로직 및 생각 : 2차원 배열을 입력받는 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t2000f2999.p2738;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;

        String[] tempArr = br.readLine().split(" ");
        N = Integer.parseInt(tempArr[0]);
        M = Integer.parseInt(tempArr[1]);

        int[][] arr1 = new int[N][M];
        int[][] arr2 = new int[N][M];
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            arr1[i] = arr;
        }

        for (int i = N; i < 2 * N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            arr2[i - N] = arr;
        }

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                result[n][m] = arr1[n][m] + arr2[n][m];
                System.out.print(result[n][m] + " ");
            }
            System.out.println();
        }

    }
}
