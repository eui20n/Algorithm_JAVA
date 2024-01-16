/**
 * 문제 이름 : 진우의 달 여행 (Large)
 * URL : https://www.acmicpc.net/problem/17485
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C;
    static int[][] arr;
    static int[] dy = {-1, 0, 1};
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmpInput = br.readLine().split(" ");

        R = Integer.parseInt(tmpInput[0]);
        C = Integer.parseInt(tmpInput[1]);

        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();
    }

    static void check() {
        // 완적 탐색을 통해서 정답을 찾는 메소드
        for (int i = 0; i < C; i++) {
            search(0, i, -1, arr[0][i]); // x, y, 이전 방향, 연료
        }
        System.out.println(result);
    }

    static void search(int x, int y, int preDir, int fuel) {
        if (x + 1 >= R) {
            // 종료 조건 => 달 도착
            result = Math.min(fuel, result);
            return;
        }

        for (int z = 0; z < 3; z++) {
            int nx = x + 1;
            int ny = y + dy[z];

            if (z == preDir)
                // 이전 방향
                continue;
            if (ny < 0 || ny >= C)
                // 범위를 벗어나는 경우
                continue;
            if (fuel + arr[nx][ny] > result)
                // 중간 부터 연료가 더 많은 경우
                continue;
            search(nx, ny, z, fuel + arr[nx][ny]);
        }
    }
}

/*
        small 이랑 동일한 코드를 낼 때, 시간초과남
        여기서 더 가지를 칠 것이 있는 듯

        아니면 가지를 치지말고, 그냥 점화식을 구해서 해도 될듯
        대신 3차원 배열로 만들어서 각 방향에서의 최솟값을 구하면 될 듯....?
 */
