/**
 * 문제 이름 : 파이프 옮기기2
 * URL : https://www.acmicpc.net/problem/17069
 * 문제 설명 :
 * 파이프는 연속된 2칸을 차지하며 가로, 세로, 대각선으로 회전이 가능하다. 파이프는 오른쪽, 오른쪽아래, 아래 방향으로 설치할 수 있다.
 * 단, 벽이 있는 칸으로는 갈 수 없다.
 * 가로로 놓여 있는 파이프에는 다시 가로로 가는 경우와 대각선으로 가는 경우만 연결 가능
 * 세로로 놓여 있는 파이프에는 다시 세로로 가는 경우와 대각선으로 가는 경우만 연결 가능
 * 대각선으로 놓여 있는 파이프에는 가로, 세로, 대각선으로 다 연결 가능
 * 파이프가 (0, 0)에서 (N, M)로 가는 경우의 수가 몇개인지 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17069;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        installPipe();
    }

    static void installPipe() {
        long[][][] pipe = new long[3][N + 1][N + 1]; // 가로(0), 세로(1), 대각선(2)

        pipe[0][1][1] = 1;
        pipe[0][1][2] = 1;

        for (int x = 1; x < N + 1; x++) {
            for (int y = 3; y < N + 1; y++) {
                if (arr[x - 1][y - 1] == 1)
                    continue;
                // 가로에서 현재 경로로 올 수 있는 경우
                horizontal(pipe, x, y);

                // 세로에서 현재 경로로 올 수 있는 경우
                vertical(pipe, x, y);

                // 대각선에서 현재 경로로 올 수 있는 경우
                diagonal(pipe, x, y);
            }
        }

        long result = pipe[0][N][N] + pipe[1][N][N] + pipe[2][N][N];
        System.out.println(result);
    }

    static void horizontal(long[][][] pipe, int x, int y) {
        // 가로
        if (y <= N) {
            pipe[0][x][y] += pipe[0][x][y - 1];
            pipe[0][x][y] += pipe[2][x][y - 1];
        }
    }

    static void vertical(long[][][] pipe, int x, int y) {
        // 세로
        if (x <= N) {
            pipe[1][x][y] += pipe[1][x - 1][y];
            pipe[1][x][y] += pipe[2][x - 1][y];
        }
    }

    static void diagonal(long[][][] pipe, int x, int y) {
        // 대각선
        if (x <= N && y <= N) {
            // 벽인 위치에서는 올 수 없음
            if (arr[x - 2 < 0 ? 0 : x - 2][y - 1] != 1 && arr[x - 1][y - 2] != 1) {
                pipe[2][x][y] += pipe[0][x - 1][y - 1];
                pipe[2][x][y] += pipe[1][x - 1][y - 1];
                pipe[2][x][y] += pipe[2][x - 1][y - 1];
            }
        }
    }
}
// ??