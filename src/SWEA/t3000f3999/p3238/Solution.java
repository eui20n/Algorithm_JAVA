/**
 * 문제 이름 : 이항계수 구하기
 * URL : https://www.acmicpc.net/problem/3238
 * 문제 설명 :
 * nCr (조합)의 값을 소수 P로 나눈 나머지를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package SWEA.t3000f3999.p3238;

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
            int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = num[0];
            int r = num[1];
            int p = num[2];
        }

    }
}
