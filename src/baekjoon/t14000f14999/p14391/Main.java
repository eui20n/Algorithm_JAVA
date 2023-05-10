/**
 * 문제 이름 : 종이 조각
 * URL : https://www.acmicpc.net/problem/14391
 * 문제 설명 :
 * 종이를 잘라서 가장 큰 수를 만들면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14391;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        check();
    }

    static void check() {
        paper(0, 0, 0, visited);
        System.out.println(ans);
    }

    static void paper(int x, int y, int result, boolean[][] visited) {
        if (x == N && y == 0) {
            // 기저 조건
            ans = Math.max(result, ans);
            return;
        }

        if (y >= M) {
            paper(x + 1, 0, result, visited);
            return;
        }

        if (visited[x][y]) {
            paper(x, y + 1, result, visited);
            return;
        }

        for (int m = 0; m < M - y; m++) {
            List<Integer> col = new ArrayList<>();
            int tmpNum = 0;
            for (int i = y; i < m + y + 1; i++) {
                // 열로 더하기
                col.add(arr[x][i]);
                visited[x][i] = true;
            }
            for (int j = 0; j < col.size(); j++) {
                tmpNum += col.get(j) * Math.pow(10, j);
            }
            // 재귀
            paper(x, y + 1, result + tmpNum, visited);
            for (int i = y; i < m + y + 1; i++) {
                visited[x][i] = false;
            }
        }

        for (int n = 0; n < N - x; n++) {
            List<Integer> row = new ArrayList<>();
            int tmpNum = 0;
            for (int i = x; i < n + x + 1; i++) {
                // 행으로 더하기
                row.add(arr[i][y]);
                visited[i][y] = true;
            }
            for (int j = 0; j < row.size(); j++) {
                tmpNum += row.get(j) * Math.pow(10, j);
            }
            // 재귀
            paper(x, y + 1, result + tmpNum, visited);
            for (int i = x; i < n + x + 1; i++) {
                visited[i][y] = false;
            }
        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
