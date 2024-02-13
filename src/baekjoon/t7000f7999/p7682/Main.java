/**
 * 문제 이름 : 틱택토
 * URL : https://www.acmicpc.net/problem/7682
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t7000f7999.p7682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] gameArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            gameArr = br.readLine().toCharArray();

            if (gameArr[0] != 'e') {
                check();
            } else {
                break;
            }
        }

    }

    static void check() {
        // 배열 만들기
        char[][] arr = makeArr();

        /*
                확인 해야할 것
            1. X와 O의 개수 => 이 개수로 가득찬지 확인 가능
            2. 게임을 끝낼 조건이 되는지
         */

        int[] XOCnt = cntXO(arr);
        int[] colRowCntArr = colRowCnt(arr);
        int[] diagonalCntArr = diagonalCnt(arr);

        System.out.println(valid(XOCnt, colRowCntArr, diagonalCntArr) ? "valid" : "invalid");
    }

    static boolean valid(int[] XOCnt, int[] colRowCntArr, int[] diagonalCntArr) {
        // 되는 경우를 찾고, 나머지를 false

        // X 5개, O 4개
        if (XOCnt[0] == 5 && XOCnt[1] == 4) {
            // X의 가로 세로 한 개 씩
            if (colRowCntArr[0] == 1 && colRowCntArr[1] == 1 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // X의 가로 한 개
            if (colRowCntArr[0] == 1 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // X의 세로 한 개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 1 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // X의 대각선 2개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 2 && diagonalCntArr[1] == 0)
                return true;

            // X의 대각선 1개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 1 && diagonalCntArr[1] == 0)
                return true;

            // X의 대각선 1개, 가로 1개
            if (colRowCntArr[0] == 1 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 1 && diagonalCntArr[1] == 0)
                return true;

            // X의 대각선 1개, 세로 1개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 1 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 1 && diagonalCntArr[1] == 0)
                return true;

            // 아무 경우도 아닌 경우
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;
        }

        // X와 O의 개수가 1개 차이나는 경우 => X가 이긴 경우
        if (XOCnt[0] - XOCnt[1] == 1) {
            // X의 가로 한 개씩
            if (colRowCntArr[0] == 1 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // X의 세로 한 개씩
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 1 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // X의 대각선 1개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 1 && diagonalCntArr[1] == 0)
                return true;
        }

        // X와 O의 개수가 같은 경우 => O가 이긴 경우
        if (XOCnt[0] == XOCnt[1] && XOCnt[0] > 0) {
            // O의 가로 한 개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 1 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // O의 세로 한 개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 1 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 0)
                return true;

            // O의 대각선 한 개
            if (colRowCntArr[0] == 0 && colRowCntArr[1] == 0 && colRowCntArr[2] == 0 && colRowCntArr[3] == 0 && diagonalCntArr[0] == 0 && diagonalCntArr[1] == 1)
                return true;
        }

        // 판에 아무 것도 없는 경우
        if (XOCnt[0] == 0 && XOCnt[1] == 0)
            return true;
        return false;
    }

    static int[] diagonalCnt(char[][] arr) {
        /*
            대각선이 몇개인지 출력
            => 몇개인지를 반환할 것
            => 이게 올바른 경우인지는 밖에서 검사를 할 것
         */
        int[] cntArr = new int[2]; // X의 대각선 개수, O의 대각선 개수

        char startHorse = arr[0][0];
        int startCnt = 0;

        char endHorse = arr[0][2];
        int endCnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (startHorse == arr[i][i] && startHorse != '.') {
                startCnt += 1;
            }
            if (endHorse == arr[i][arr.length - 1 - i] && endHorse != '.') {
                endCnt += 1;
            }
        }

        if (startCnt == 3) {
            if (startHorse == 'X') {
                cntArr[0] += 1;
            } else {
                cntArr[1] += 1;
            }
        }
        if (endCnt == 3) {
            if (endHorse == 'X') {
                cntArr[0] += 1;
            } else {
                cntArr[1] += 1;
            }
        }
        return cntArr;
    }

    static int[] colRowCnt(char[][] arr) {
        /*
            가로 세로로 끝이 나는 경우
            => 누가 승리했는지, 그리고 몇개인지를 반환할 것
            => 이게 올바른 경우인지는 밖에서 검사를 할 것
         */
        int[] cntArr = new int[4]; // X 가로 수, X 세로 수, O 가로 수, O 세로 수
        for (int i = 0; i < arr.length; i++) {
            char rowHorse = arr[i][0];
            int rowCnt = 0;

            char colHorse = arr[0][i];
            int colCnt = 0;
            for (int j = 0; j < arr[i].length; j++) {
                if (rowHorse == arr[i][j] && rowHorse != '.')
                    rowCnt += 1;
                if (colHorse == arr[j][i] && colHorse != '.')
                    colCnt += 1;
            }
            if (rowCnt == 3) {
                if (rowHorse == 'X') {
                    cntArr[0] += 1;
                } else {
                    cntArr[2] += 1;
                }
            }
            if (colCnt == 3) {
                if (colHorse == 'X') {
                    cntArr[1] += 1;
                } else {
                    cntArr[3] += 1;
                }
            }

        }
        return cntArr;
    }


    static int[] cntXO(char[][] arr) {
        /*
            X, O의 개수를 세주는 메소드
         */
        int[] XOCnt = new int[2]; // X의 개수, O의 개수

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'X')
                    XOCnt[0] += 1;
                if (arr[i][j] == 'O')
                    XOCnt[1] += 1;
            }
        }
        return XOCnt;
    }

    static char[][] makeArr() {
        char[][] newArr = new char[3][3];

        newArr[0][0] = gameArr[0];
        newArr[0][1] = gameArr[1];
        newArr[0][2] = gameArr[2];
        newArr[1][0] = gameArr[3];
        newArr[1][1] = gameArr[4];
        newArr[1][2] = gameArr[5];
        newArr[2][0] = gameArr[6];
        newArr[2][1] = gameArr[7];
        newArr[2][2] = gameArr[8];

        return newArr;
    }
}

/*
    그냥 구현
    그냥 구현인데 너무 귀찮은 그냥 구현
 */