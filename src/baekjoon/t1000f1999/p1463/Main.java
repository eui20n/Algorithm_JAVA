/**
 * 문제 이름 : 1로 만들기
 * URL : https://www.acmicpc.net/problem/1463
 * 문제 설명 :
 * 1. x가 3으로 나누어 떨어지면, 3으로 나누기
 * 2. x가 2로 나누어 떨어지면, 2로 나누기
 * 3. 1을 빼기
 * 위 3개의 연산을 이용하여 주어지는 수를 1로 만드는 최소 연산 횟수를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1463;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        System.out.println(makeOne());
    }

    static int makeOne() {
        int[] one = new int[10000001];
        Arrays.fill(one, Integer.MAX_VALUE);
        one[1] = 0;
        for (int i = 1; i < 1000001; i++) {
            one[i + 1] = Math.min(one[i] + 1, one[i + 1]);
            one[i * 3] = Math.min(one[i] + 1, one[i * 3]);
            one[i * 2] = Math.min(one[i] + 1, one[i * 2]);
        }
        for (int i = 0; i < 100; i++) {
            System.out.printf("#%d %d ", i, one[i]);
        }

        return one[N];

    }
}
