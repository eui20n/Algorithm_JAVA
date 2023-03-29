/**
 * 문제 이름 : RGB 거리
 * URL : https://www.acmicpc.net/problem/1149
 * 문제 설명 :
 * 1. 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * 2. N번 집의 색은 N - 1 번 집의 색과 같지 않아야 한다.
 * 3. i번 집의 색은 i - 1번, i + 1번 집의 색과 같지 않아야 한다.
 * 위 조건을 만족하면서 집을 칠할 때 최소값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp();
    }

    static void dp() {
        int[][] dpTable = new int[N + 1][3];
        dpTable[1][0] = arr[0][0];
        dpTable[1][1] = arr[0][1];
        dpTable[1][2] = arr[0][2];

        for (int i = 2; i <= N; i++) {
            // 그냥 이전 집에서 자기랑 다른 색을 보면 됨
            dpTable[i][0] = Math.min(dpTable[i - 1][1] + arr[i - 1][0], dpTable[i - 1][2] + arr[i - 1][0]);
            dpTable[i][1] = Math.min(dpTable[i - 1][2] + arr[i - 1][1], dpTable[i - 1][0] + arr[i - 1][1]);
            dpTable[i][2] = Math.min(dpTable[i - 1][1] + arr[i - 1][2], dpTable[i - 1][0] + arr[i - 1][2]);
        }

        int result = Arrays.stream(dpTable[N]).min().getAsInt();
        System.out.println(result);

    }
}