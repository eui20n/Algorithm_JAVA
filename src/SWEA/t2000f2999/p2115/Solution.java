/**
 * 문제 이름 : 벌꿀 채취
 * 문제 번호 : 2115
 * 문제 설명 :
 * 정사각형에 벌꿀이 있고, 일꾼이 두명이 바구니를 들고 있다. 바구니에 최대 벌꿀을 수용할 수 있는 양이 있고 일꾼도 가로로 범위가 있다.
 * 서로 범위가 겹치면 안된다. 이 때 벌꿀을 최대로 채취하면 얼마나 채취할 수 있는지 출력해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t2000f2999.p2115;

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
            int N, M, C;
            String[] tmp = br.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            M = Integer.parseInt(tmp[1]);
            C = Integer.parseInt(tmp[2]);

            int[][] arr = new int[N][N];
            for (int j = 0; j < N; j++) {
                arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            beeHoney(N, M, C, arr);
        }
    }

    static void beeHoney(int N, int M, int C, int[][] arr) {

    }
}
