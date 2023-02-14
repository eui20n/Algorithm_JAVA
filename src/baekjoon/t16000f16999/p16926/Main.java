/**
 * 문제 이름 : 배열 돌리기 1
 * URL : https://www.acmicpc.net/problem/16926
 * 문제 설명 : 배열을 돌리면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, R;
    static int[][] arr;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int endCon;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tmp[0];
        M = tmp[1];
        R = tmp[2];

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
        int[][] board = new int[N][M];
        int[][] dir = makeDirectionArr(new int[N][M], 0);
        rotate(dir, board, 0);
        showArr(board);
    }

    static void rotate(int[][] dir, int[][] board, int cnt) {
        // 방향은 방향 배열대로 회전을 시키는 로직
        if (cnt * 2 == Math.min(M, N))
            return;

        int operatorCnt = (N - 2 * cnt) * 2 + (M - 2 * cnt) * 2 - 4;

        for (int y = cnt; y < M - cnt; y++) {
            int[] change1 = go(dir, operatorCnt, cnt, y);
            int changeX1 = change1[0];
            int changeY1 = change1[1];
            board[changeX1][changeY1] = arr[cnt][y];

            int[] change2 = go(dir, operatorCnt, N - 1 - cnt, y);
            int changeX2 = change2[0];
            int changeY2 = change2[1];

            board[changeX2][changeY2] = arr[N - 1 - cnt][y];
        }

        for (int x = cnt; x < N - cnt; x++) {
            int[] change1 = go(dir, operatorCnt, x, cnt);
            int changeX1 = change1[0];
            int changeY1 = change1[1];
            board[changeX1][changeY1] = arr[x][cnt];

            int[] change2 = go(dir, operatorCnt, x, M - 1 - cnt);
            int changeX2 = change2[0];
            int changeY2 = change2[1];
            board[changeX2][changeY2] = arr[x][M - 1 - cnt];
        }

        rotate(dir, board, cnt + 1);
    }

    // 방향 배열을 만들어 주는 함수
    static int[][] makeDirectionArr(int[][] dir, int cnt) {
        if (cnt * 2 == Math.min(M, N))
            return dir;

        // 상 1 하 2 좌 3 우 4
        for (int y = cnt + 1; y < M - 1 - cnt; y++) {
            dir[cnt][y] = 4;
            dir[N - 1 - cnt][y] = 3;
        }

        for (int x = cnt + 1; x < N - 1 - cnt; x++) {
            dir[x][cnt] = 2;
            dir[x][M - 1 - cnt] = 1;
        }
        dir[cnt][cnt] = 2;
        dir[N - 1 - cnt][cnt] = 3;
        dir[N - 1 - cnt][M - 1 - cnt] = 1;
        dir[cnt][M - 1 - cnt] = 4;

        return makeDirectionArr(dir, cnt + 1);
    }

    static int[] go(int[][] dir, int operatorCnt, int x, int y) {
        int operate = R % operatorCnt;
        int cnt = 0;
        while (true) {
            if (cnt == operate)
//            if (cnt == R)
                return new int[]{x, y};

            int nx = x + dx[dir[x][y]];
            int ny = y + dy[dir[x][y]];
            x = nx;
            y = ny;
            cnt++;
        }
    }

    static void showArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

// N 과 M중 짧은 것은 짝수를 보장 받음 => 문제의 조건
// 방향 배열을 만들어서 해당 방향으로만 가게함