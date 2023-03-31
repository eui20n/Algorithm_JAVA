/**
 * 문제 이름 : 최장 증가 부분 수열
 * 문제 번호 : 3307
 * 문제 설명 : 그냥 최장 증가 부분 수열을 구하면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t3000f3999.p3307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.printf("#%d %d %n", i, check(N, arr));
        }

    }

    static int check(int n, int[] arr) {
        int[] dpTable = new int[n + 1];
        int max = 0;

        for (int i = 1; i <= n; i++) {
            dpTable[i] = 1;

            for (int j = 1; j <= i; j++) {
                if (arr[i - 1] > arr[j - 1]) {
                    dpTable[i] = Math.max(dpTable[j] + 1, dpTable[i]);
                }
                max = Math.max(dpTable[i], max);
            }
        }
        return max;
    }
}
