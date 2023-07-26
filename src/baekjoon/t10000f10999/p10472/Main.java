/**
 * 문제 이름 : 십자뒤집기
 * URL : https://www.acmicpc.net/problem/10472
 * 문제 설명 :
 * 입력으로 주어지는 배열을 만들어라!!
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t10000f10999.p10472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] arr = new char[3][3];
    static char[][] initialArr = new char[3][3];

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static int T;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            setInitialArr();
            result = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                arr[i] = br.readLine().toCharArray();
            }

            check(0, 0, 0);
            System.out.println(result);
        }
    }

    static void check(int x, int y, int cnt) {
        if (valid()) {
            result = Math.min(result, cnt);
        }

        if (y >= 3) {
            check(x + 1, 0, cnt);
            return;
        }

        if (x >= 3) {
            return;
        }

        for (int z = 0; z < 5; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= 3)
                continue;
            if (0 > ny || ny >= 3)
                continue;
            initialArr[nx][ny] = change(initialArr[nx][ny]);
        }
        check(x, y + 1, cnt + 1);

        for (int z = 0; z < 5; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= 3)
                continue;
            if (0 > ny || ny >= 3)
                continue;
            initialArr[nx][ny] = change(initialArr[nx][ny]);
        }
        check(x, y + 1, cnt);
    }

    static char change(char value) {
        if (value == '.')
            return '*';
        return '.';
    }

    static boolean valid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initialArr[i][j] != arr[i][j])
                    return false;
            }
        }
        return true;
    }

    static void setInitialArr() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initialArr[i][j] = '.';
            }
        }
    }
}