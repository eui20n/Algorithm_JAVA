/**
 * 문제 이름 : 무선 충전
 * 문제 번호 : 5644
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t5000f5999.p5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
    static int T;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int M, A;
            String[] tmp = br.readLine().split(" ");
            M = Integer.parseInt(tmp[0]);
            A = Integer.parseInt(tmp[1]);

            int[] person1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] person2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] BCInfo = new int[A][4];
            for (int j = 0; j < A; j++) {
                String[] tmp2 = br.readLine().split(" ");
                int y = Integer.parseInt(tmp2[1]) - 1;
                int x = Integer.parseInt(tmp2[0]) - 1;
                int range = Integer.parseInt(tmp2[2]);
                int charge = Integer.parseInt(tmp2[3]);

                BCInfo[j][0] = y;
                BCInfo[j][1] = x;
                BCInfo[j][2] = range;
                BCInfo[j][3] = charge;
            }

            check(person1, person2, BCInfo);
        }
    }

    static void check(int[] person1, int[] person2, int[][] BCInfo) {
        int[][] board = new int[10][10];

        int mark = 1;
        for (int[] BC : BCInfo) {
            int x = BC[0];
            int y = BC[1];
            int range = BC[2];

            bfs(x, y, range, mark++, board);
        }
        print(board);

        go(person1, person2, board, BCInfo);
    }

    static void go(int[] person1, int[] person2, int[][] board, int[][] BCInfo) {
        int result = 0;

        int person1X = 0;
        int person1Y = 0;
        int person2X = 9;
        int person2Y = 9;

        for (int i = 0; i < person1.length; i++) {

        }
    }

    static void bfs(int startX, int startY, int range, int mark, int[][] board) {
        board[startX][startY] |= 1 << mark;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {startX, startY, 0});

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int cnt = tmp[2];

            if (cnt == range) {
                break;
            }

            for (int z = 1; z < 5; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= 10)
                    continue;
                if (0 > ny || ny >= 10)
                    continue;
                if ((board[nx][ny] & (1 << mark)) != 0)
                    continue;
                q.add(new int[] {nx, ny, cnt + 1});
                board[nx][ny] |= 1 << mark;
            }
        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
