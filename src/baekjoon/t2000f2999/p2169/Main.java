/**
 * 문제 이름 : 로봇 조종하기
 * URL : https://www.acmicpc.net/problem/2169
 * 문제 설명 :
 * 로봇은 아래, 왼쪽, 오른쪽으로 밖에 갈 수 없다. 각 격자판 위에 해당 판의 가치가 있을 때, (0, 0)에서 시작해서 (N, M) 까지는 가는데
 * 얻을 수 있는 가치의 최대값을 구해라. 단, 한번 간곳은 다시는 갈 수 없다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] cost;
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        arr = new int[N][M];
        cost = makeArr();

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();
    }

    static void check() {
        cost[0][0] = arr[0][0];
        controlRobot(0, 0);
    }

    static void controlRobot(int x, int y) {
        // 기저 조건
        if (x == N - 1 && y == M - 1) {
            return;
        }

        for (int z = 0; z < 3; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= N)
                continue;
            if (0 > ny || ny >= M)
                continue;
        }
    }

    static int[][] makeArr() {
        int[][] newArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newArr[i][j] = Integer.MIN_VALUE;
            }
        }
        return newArr;
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

// 각각의 칸에는 그 칸에 올 수 있는 최대값이 적혀있음
// 그 칸에 숫자가 있다는 것은 이미 최대의 값으로 방문을 했다는 의미
// 현재 칸에서 다음칸의 값을 더했을 때, 다음칸이 더 크면 갈 필요가 없음