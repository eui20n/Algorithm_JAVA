/**
 * 문제 이름 : 소수 구하기
 * URL : https://www.acmicpc.net/problem/1929
 * 문제 설명 : M이상 N이하의 소수를 모두 출력하기
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1929;

import java.util.Scanner;

public class Main {
    static int M, N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();

        check();
    }

    static void check() {
        boolean[] visited = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {
            if (visited[i])
                continue;

            if (i >= M)
                System.out.println(i);

            for (int j = i; j <= N; j += i) {
                visited[j] = true;
            }
        }
    }
}
