/**
 * 문제 이름 : 한빈이와 Spot Mart
 * 문제 번호 : 9229
 * 문제 설명 : 한빈이가 들고 갈 수 있는 최대 무게가 있다. 그 무게안에서 한빈이가 2개를 들고 갈때, 최대 무게를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t9000f9999.p9229;

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
            int N, M;

            int[] inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = inputArr[0];
            M = inputArr[1];

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr);

            System.out.printf("#%d %d %n", i, check(N, arr, M));
        }
    }

    static int check(int N, int[] arr, int M) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j)
                    continue;

                if (arr[i] + arr[j] <= M) {
                    result = Math.max(arr[i] + arr[j], result);
                } else {
                    break;
                }
            }
        }
        return result == Integer.MIN_VALUE ? -1 : result;
    }
}
