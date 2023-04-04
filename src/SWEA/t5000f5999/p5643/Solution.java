/**
 * 문제 이름 : 키 순서
 * 문제 번호 : 5643
 * 문제 설명 :
 * 자신의 키가 몇번째인지 알 수 있는 학생이 몇명인지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */
package SWEA.t5000f5999.p5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int T, N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            arr = new int[N][N];

            for (int j = 0; j < M; j++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int to = tmp[0] - 1;
                int from = tmp[1] - 1;

                arr[to][from] = 1;
            }
            check();
        }
    }

    static void check() {
        int[][] visited = new int[N][N];

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j)
                        continue;
                    if (arr[i][k] == 0 && arr[k][j] == 0)
                        continue;
                    visited[i][j] = 1;
                }
            }
        }

        print(visited);
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
// 모든 학생과 다 연결되어 있으면 됨(오던, 가던)
// fail