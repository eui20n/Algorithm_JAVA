/**
 * 문제 이름 : 색종이
 * URL : https://www.acmicpc.net/problem/2563
 * 문제 설명 : 양변의 길이가 10인 색종이를 붙여서 붙인 곳의 넓이를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] loc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        loc = new int[N][2];
        for (int i = 0; i < N; i++) {
            loc[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(check());
    }

    static int check() {
        boolean[][] paper = new boolean[100][100];
        int cnt = 0;

        for (int[] paperLoc : loc) {
            int x = paperLoc[0] - 1;
            int y = paperLoc[1] - 1;

            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    if (paper[i][j])
                        continue;
                    paper[i][j] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
// 그냥 반복문 돌리면 될듯
// 100 x 100이 최대