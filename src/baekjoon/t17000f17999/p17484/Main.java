/**
 * 문제 이름 : 진우의 달 여행 (Small)
 * URL : https://www.acmicpc.net/problem/17484
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t17000f17999.p17484;

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
         입력으로 주어지는 배열의 크기가 굉장히 작음 => 완전 탐색을 통해서 답을 찾으면 됨

 */