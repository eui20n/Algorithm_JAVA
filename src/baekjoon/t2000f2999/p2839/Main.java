/**
 * 문제 이름 : 설탕 배달
 * URL : https://www.acmicpc.net/problem/2839
 * 문제 설명 : 설탕을 담는 봉지가 3킬로 그램과 5킬로 그램이 있다. 이 때, 최대한 적은 봉지를 사용하려고 할때, 그 봉지 수를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2839;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sugar(N);
    }

    static void sugar(int n) {
        int[] dpTable = new int[5001];
        for (int i = 0; i <= n; i++) {
            dpTable[i] = 9999999;
        }

        dpTable[3] = 1;
        dpTable[5] = 1;

        for (int i = 6; i <= n; i++) {
            dpTable[i] = Math.min(dpTable[i - 3], dpTable[i - 5]) + 1;
        }
        System.out.println(dpTable[n] >= 9999999? -1:dpTable[n]);
    }
}
