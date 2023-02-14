/**
 * 문제 이름 : 스도쿠 검증
 * 문제 번호 : 1974
 * 문제 설명 : 스도쿠가 입력으로 들어오면 해당 스도쿠가 스도쿠인지 아닌지 검증하는 프로그램을 만들면 됨
 * 시간복잡도 : O(9 x 9 x 3)
 * 핵심 로직 및 생각 : 완전탐색
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1974;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i < T + 1; i++) {
            for (int tmp = 0; tmp < 9; tmp++) {
                int[] tempBoard = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                board[tmp] = tempBoard;
            }

            int result = check();
            System.out.println("#" + i + " " + result);
        }

    }

    public static int check() {
        int[] checkNumber = new int[10];

        // 스도쿠인지 검증해주는 함수
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int value = board[x][y];
                if (checkNumber[value] == 1) {
                    return 0;
                }
                checkNumber[value] = 1;
            }
            checkNumber = new int[10];
        }

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int value = board[x][y];
                if (checkNumber[value] == 1) {
                    return 0;
                }
                checkNumber[value] = 1;
            }
            checkNumber = new int[10];
        }

        int[] X = {0, 3, 6};
        int[] Y = {0, 3, 6};

        for (int tmp_x : X) {
            for (int tmp_y : Y) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        int value = board[tmp_x + x][tmp_y + y];
                        if (checkNumber[value] == 1) {
                            return 0;
                        }
                        checkNumber[value] = 1;
                    }
                }
                checkNumber = new int[10];
            }
        }
        return 1;
    }
}

//(0, 0) (0, 1) (0, 2)  (0, 3) (0, 4) (0, 5)  (0, 6) (0, 7) (0, 8)
//(1, 0) (1, 1) (1, 2)  (1, 3) (1, 4) (1, 5)  (1, 6) (1, 7) (1, 8)
//(2, 0) (2, 1) (2, 2)  (2, 3) (2, 4) (2, 5)  (2, 6) (2, 7) (2, 8)
//
//(3, 0) (3, 1) (3, 2)  (3, 3) (3, 4) (3, 5)  (3, 6) (3, 7) (3, 8)
//(4, 0) (4, 1) (4, 2)  (4, 3) (4, 4) (4, 5)  (4, 6) (4, 7) (4, 8)
//(5, 0) (5, 1) (5, 2)  (5, 3) (5, 4) (5, 5)  (5, 6) (5, 7) (5, 8)
//
//(6, 0) (6, 1) (6, 2)  (6, 3) (6, 4) (6, 5)  (6, 6) (6, 7) (6, 8)
//(7, 0) (7, 1) (7, 2)  (7, 3) (7, 4) (7, 5)  (7, 6) (7, 7) (7, 8)
//(8, 0) (8, 1) (8, 2)  (8, 3) (8, 4) (8, 5)  (8, 6) (8, 7) (8, 8)