/**
 * 문제 이름 : 스도쿠
 * URL : https://www.acmicpc.net/problem/2239
 * 문제 설명 : 하다 만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램을 작성해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2239;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    static int[][] arr = new int[9][9];
    static int zeroCnt = 0;
    static boolean result = false;
    static int[][] checkArr = {{0, 0}, {0, 3}, {0, 6}, {3, 0}, {3, 3}, {3, 6}, {6, 0}, {6, 3}, {6, 6}};
    static boolean[][] visitedR = new boolean[9][9];
    static boolean[][] visitedC = new boolean[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 0)
                    zeroCnt += 1;
            }

            makeVisited();
        }
    }

    static void makeVisited() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == 0)
                    continue;
                visitedR[i][arr[i][j] - 1] = true;
            }
        }

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (arr[i][j] == 0)
                    continue;
                visitedR[j][arr[i][j] - 1] = true;
            }
        }
    }

    static void answer(int cnt) {
        if (cnt == zeroCnt) {
            if (check()) {
                print(arr);
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] != 0)
                    continue;

                for (int v = 1; v <= 9; v++) {
                    if (visitedR[i][v - 1])
                        continue;
                    if (visitedC[j][v - 1])
                        continue;

                    visitedR[i][v - 1] = true;
                    visitedC[j][v - 1] = true;
                    arr[i][j] = v;
                    answer(cnt + 1);
                    arr[i][j] = 0;
                    visitedR[i][v - 1] = false;
                    visitedC[j][v - 1] = false;

                }
            }
        }
    }

    static boolean check() {
        return true;

    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
// 그냥 다 만들고 다 만들면 검사하기