/**
 * 문제 이름 : 정사각형 방
 * 문제 번호 : 1861
 * 문제 설명 : 방을 인접한 칸으로 이동할 수 있는데, 방의 번호는 정확히 1이 큰 방으로만 갈 수 있다.
 * 이때, 최대로 이동가능한 방의 수와 시작 방을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());

            int[][] rooms = new int[N][N];

            for (int j = 0; j < N; j++) {
                rooms[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[] result = check(rooms, N);

            sb.append("#").append(i).append(" ").append(result[1]).append(" ").append(result[0]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[] check(int[][] arr, int N) {
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j, N, board, arr);
            }
        }

        return findResult(board, arr);
    }

    static int dfs(int x, int y, int N, int[][] board, int[][] arr) {
        for (int z = 0; z < 4; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= N)
                continue;
            if (0 > ny || ny >= N)
                continue;
            if (arr[x][y] + 1 != arr[nx][ny])
                continue;
            // board의 값을 계속해서 갱신해줌
            if (board[nx][ny] != 0){
                board[x][y] = board[nx][ny] + 1;
            } else if (board[nx][ny] == 0){
                board[x][y] = dfs(nx, ny, N, board, arr) + 1;
            }
        }
        if (board[x][y] == 0){
            board[x][y] = 1;
        }
        return board[x][y];
    }

    static int[] findResult(int[][] board, int[][] arr) {
        int maxCnt = board[0][0]; // 횟수
        int maxValue = arr[0][0]; // 인덱스 값
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (maxCnt < board[i][j]) {
                    maxCnt = board[i][j];
                    maxValue = arr[i][j];
                } else if (maxCnt == board[i][j]) {
                    if (maxValue > arr[i][j]) {
                        maxCnt = board[i][j];
                        maxValue = arr[i][j];
                    }
                }
            }
        }

        return new int[]{maxCnt, maxValue};
    }
}
// 가는 느낌이 아니라 더하는 느낌