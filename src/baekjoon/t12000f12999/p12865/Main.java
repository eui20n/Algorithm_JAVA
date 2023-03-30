/**
 * 문제 이름 : 평범한 배낭
 * URL : https://www.acmicpc.net/problem/12865
 * 문제 설명 :
 * 여행에 필요하다고 생각하는 물건 N개, 각 물건의 무게와 가치는 W, V, 배낭의 용량이 K 일 때
 * 최대로 넣을 수 있는 물건의 가치를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t12000f12999.p12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K;
    static int[][] knapsack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        knapsack = new int[N][2];

        for (int i = 0; i < N; i++) {
            knapsack[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        enjoyTrip();
    }

    static void enjoyTrip() {
        int[][] dpTable = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (knapsack[i - 1][0] > j)
                    dpTable[i][j] = dpTable[i - 1][j];
                else
                    dpTable[i][j] = Math.max(knapsack[i - 1][1] + dpTable[i - 1][j - knapsack[i - 1][0]], dpTable[i - 1][j]);
            }
        }

//        print(dpTable);
        System.out.println(dpTable[N][K]);
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 다시 풀기 => 뭔가 마음에 안듬
