/**
 * 문제 이름 : 빵집
 * URL : https://www.acmicpc.net/problem/3109
 * 문제 설명 : 빵집에 가스관을 설치하려고 한다. 가스관은 1열에 있고, 빵집은 마지막 열에 있다. 가스관 끼리는 서로 겹칠 수 없을 때, 가스관을
 * 최대로 몇개 설치할 수 있는지 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C;
    static String[][] arr;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = new String[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().split("");
        }
        check();
        System.out.println(cnt);
    }

    static void check() {
        int[][] board = new int[R][C];
        for (int i = 0; i < R; i++) {
            board[i][0] = 1;
            go(board, i, 0);
        }
//        showArr(board);
    }

    static void go(int[][] board, int x, int y) {
        if (y == C - 1) {
            cnt++;
            return;
        }

        for (int z = 0; z < 3; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= R)
                continue;
            if (0 > ny || ny >= C)
                continue;
            if (arr[nx][ny].equals("x"))
                continue;
            if (board[nx][ny] == 1)
                continue;
            board[nx][ny] = 1;
            go(board, nx, ny);
            return;
        }
    }

    static void showArr(int[][] arr) {
        for (int[] intArr : arr) {
            System.out.println(Arrays.toString(intArr));
        }
        System.out.println();
    }

    static void showArr(String[][] arr) {
        for (String[] strArr : arr) {
            System.out.println(Arrays.toString(strArr));
        }
        System.out.println();
    }
}