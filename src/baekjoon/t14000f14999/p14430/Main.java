/**
 * 문제 이름 : 자원 캐기
 * URL : https://www.acmicpc.net/problem/14430
 * 문제 설명 :
 * 자원 로봇이 오른쪽, 아래쪽으로만 이동 가능할 때, 얻을 수 있는 최대 자원 수를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R;
    static int C;
    static int[][] board;
    static int[] dx = {-1, 0};
    static int[] dy = {0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        gatherResource();
    }

    static void gatherResource() {
        // 결과 값을 담는 테이블
        int[][] dpTable = new int[R][C];

        if (board[0][0] == 1) {
            dpTable[0][0] = 1;
        }

        int result = dpTable[0][0];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == 0 && j == 0)
                    continue;

                for (int z = 0; z < 2; z++) {
                    int nx = i + dx[z];
                    int ny = j + dy[z];

                    if (0 > nx || nx >= R)
                        continue;
                    if (0 > ny || ny >= C)
                        continue;
                    dpTable[i][j] = Math.max(dpTable[nx][ny] + board[i][j], dpTable[i][j]);
                    result = Math.max(dpTable[i][j], result);
                }
            }
        }
        System.out.println(result);
    }

    static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
