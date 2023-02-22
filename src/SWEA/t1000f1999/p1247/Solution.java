/**
 * 문제 이름 : 최적 경로
 * 문제 번호 : 1247
 * 문제 설명 : 회사에서 출발하여 N명의 고객을 방문 후 다시 집에 돌아가려고 한다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 외판원 순회 문제 => N의 크기가 작아서 완전 탐색으로 풀이 가능
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1247;

import java.util.*;

public class Solution {
    static int T;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int[] company = new int[2];
            int[] home = new int[2];
            int[][] customers = new int[N][2];

            company[0] = sc.nextInt();
            company[1] = sc.nextInt();
            home[0] = sc.nextInt();
            home[1] = sc.nextInt();

            for (int j = 0; j < N; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                customers[j][0] = x;
                customers[j][1] = y;
            }

            result = Integer.MAX_VALUE;
            check(N, 0, 0, company, home, customers, new boolean[N]);
            System.out.printf("#%d %d %n", i, result);
        }
    }

    static void check(int N, int cnt, int sum, int[] standard, int[] home, int[][] customers, boolean[] visited) {
        if (cnt == N) {
            int sumNum = Math.abs(standard[0] - home[0]) + Math.abs(standard[1] - home[1]);
            sum += sumNum;
            result = Math.min(sum, result);
            return;
        }

        int standardX = standard[0];
        int standardy = standard[1];

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;
            int x = customers[i][0];
            int y = customers[i][1];
            int sumNum = Math.abs(x - standardX) + Math.abs(y - standardy);
            sum += sumNum;
            visited[i] = true;
            check(N, cnt + 1, sum, customers[i], home, customers, visited);
            visited[i] = false;
            sum -= sumNum;
        }
    }
}
