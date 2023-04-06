/**
 * 문제 이름 : 조합
 * URL : https://www.acmicpc.net/problem/5607
 * 문제 설명 :
 * NCR (조합)의 값을 1234567891로 나눈 나머지를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package SWEA.t5000f5999.p5607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static long[] fac;
    static final int MOD = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = num[0];
            int R = num[1];

            fac = new long[N + 1];
            fac[1] = 1;

            for (int j = 2; j <= N; j++){
                fac[j] = (fac[j - 1] * j) % MOD;
            }
            long bottomNum = (fac[R] * fac[N - R]) % MOD;
            bottomNum = perma(bottomNum, MOD - 2);
            System.out.printf("#%d %d %n", i, (fac[N] * bottomNum) % MOD);
        }
    }

    static long perma(long num, int top) {
        // 페르마의 소정리
        if (top == 0)
            return 1;
        if (top == 1)
            return num;
        if (top % 2 == 0) {
            long tmp = perma(num, top/2);
            return (tmp * tmp) % MOD;
        }

        long tmp = perma(num, top - 1) % MOD;
        return (tmp * num) % MOD;
    }
}

// ?? ㅠㅠ
// 참고함