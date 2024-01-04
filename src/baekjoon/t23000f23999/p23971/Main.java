/**
 * 문제 이름 : ZOAC 4
 * URL : https://www.acmicpc.net/problem/23971
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t23000f23999.p23971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int H, W, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        H = tmpInput[0]; // 행
        W = tmpInput[1]; // 열
        N = tmpInput[2]; // 세로줄 간격
        M = tmpInput[3]; // 가로줄 간격

        check();
    }

    static void check() {
        int HCnt = H % (N + 1) == 0 ? H / (N + 1) : H / (N + 1) + 1;; // 행에 최대로 있을 수 있는 사람 수
        int WCnt = W % (M + 1) == 0 ? W / (M + 1) : W / (M + 1) + 1; // 열에 최대로 있을 수 있는 사람 수

        System.out.println(HCnt * WCnt);
    }
}
