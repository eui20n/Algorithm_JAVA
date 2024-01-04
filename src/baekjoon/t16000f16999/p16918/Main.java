/**
 * 문제 이름 : 붐버맨
 * URL : https://www.acmicpc.net/problem/16918
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] timeArr;
    static char[][] arr;
    static int R, C, T;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");

        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        T = Integer.parseInt(tmp[2]);

        arr = new char[R][C];
        timeArr = new int[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'O')
                    timeArr[i][j] = 2;
            }
        }

        check();
    }

    static void check() {
        int time = 2;

        while (true) {
            if (T == 1 || time == T + 1)
                break;

            // 폭탄 시간 늘려주기
            boomAdd();

            if (time % 2 == 0) {
                // 폭탄 설치
                installBooms();
            } else if (time % 2 == 1) {
                // 폭발할 폭탄 폭발해주기
                boom();
            }

            time += 1;
        }

        if (T == 2) {
            installBooms();
        }

//        printTime();
        printResult();
    }

    static void printResult() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }
    static void boomAdd() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (timeArr[i][j] != 0) {
                    timeArr[i][j] += 1;
                }
            }
        }
    }

    static void installBooms() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 'O') {
                    arr[i][j] = 'O';
                    timeArr[i][j] = 1;
                }
            }
        }
    }

    static void boom() {
        // 폭발할 것들을 넣어주기 => 동시에 폭발하기 때문에 넣은 후 처리해야함
        List<int[]> boomList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (timeArr[i][j] >= 3) {
                    boomList.add(new int[] {i, j});
                }
            }
        }

        for (int[] boom : boomList) {
            int x = boom[0];
            int y = boom[1];

            for (int z = 0; z < 5; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;

                arr[nx][ny] = '.';
                timeArr[nx][ny] = 0;
            }
        }
    }

    static void printTime() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(timeArr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
