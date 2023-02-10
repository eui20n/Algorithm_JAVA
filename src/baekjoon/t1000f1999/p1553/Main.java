/**
 * 문제 이름 : 도미노 찾기
 * URL : https://www.acmicpc.net/problem/1553
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1553;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board = new int[8][7];

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < 7; j++) {
                board[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        boolean[][] visited = new boolean[8][7];
        boolean[][] used = new boolean[7][7];

        dominoes(0, 0, visited, used);
        System.out.println(cnt);
    }

    static void dominoes(int x, int y, boolean[][] visited, boolean[][] used) {
        if (x == 7 && y == 6) {
            cnt += 1;
            return;
        }

        if (visited[x][y]) {
            if (y + 1 < 7) {
                dominoes(x, y + 1, visited, used);
            } else {
                dominoes(x + 1, 0, visited, used);
            }
            return;
        }

        for (int z = 0; z < dx.length; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= 8)
                continue;
            if (0 > ny || ny >= 7)
                continue;
            if (visited[nx][ny])
                continue;

            int boardX = board[x][y];
            int boardY = board[nx][ny];

            if (used[boardX][boardY] || used[boardY][boardX])
                continue;

            visited[x][y] = true;
            visited[nx][ny] = true;
            used[boardX][boardY] = true;
            used[boardY][boardX] = true;
            if (y + 1 < 7) {
                dominoes(x, y + 1, visited, used);
            } else {
                dominoes(x + 1, 0, visited, used);
            }
            used[boardY][boardX] = false;
            used[boardX][boardY] = false;
            visited[nx][ny] = false;
            visited[x][y] = false;

        }
    }
}

// 무조건 ㅡ 아니면 ㅣ 로 가야함
// 각 칸에서는 2가지만 보면 됨
// ㅡ로 갈 수 있는가? ㅣ로 갈 수 있는가?
// 다음 위치는 무조건 배열의 순서대로 가는 것임
// nx가 다음 위치가 아님