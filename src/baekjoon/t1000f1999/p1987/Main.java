/**
 * 문제 이름 : 알파벳
 * URL : https://www.acmicpc.net/problem/1987
 * 문제 설명 : 세로 R칸, 가로 C칸으로 된 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 1행 1열에 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에
 * 적혀 있는 알파벳과는 달라야 한다. 1행 1열에서 시작해서 최대 몇 칸 갈 수 있는지 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int R, C;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            String tmp2 = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = tmp2.charAt(j) - 65;
            }
        }

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;
        int checkNum = (1 << board[0][0]);
        check(0, 0, checkNum, 0, visited);
        System.out.println(result + 1);
    }

    static void check(int x, int y, int checkNum, int cnt, boolean[][] visited) {
        for (int z = 0; z < 4; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= R)
                continue;
            if (0 > ny || ny >= C)
                continue;
            if ((checkNum & (1 << board[nx][ny])) >= 1)
                continue;
            if (visited[nx][ny])
                continue;

            visited[nx][ny] = true;
            checkNum |= (1 << board[nx][ny]);
            check(nx, ny, checkNum, cnt + 1, visited);
            checkNum &= ~(1 << board[nx][ny]);
            visited[nx][ny] = false;
        }

        result = Math.max(cnt, result);


    }
}

// 알파벳의 수가 최대 26개 라서 알파벳의 체크는 비트마스킹으로 진행 => int의 범위를 벗어나지 않음
// 비트마스킹을 수월하게 하기 위해서 알파벳들을 char의 배열로 받아서 숫자로 변환 => 65를 빼줌