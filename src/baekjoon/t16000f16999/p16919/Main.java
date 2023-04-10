/**
 * 문제 이름 : 붐버맨2
 * URL : https://www.acmicpc.net/problem/16919
 * 문제 설명 :
 * 아래의 연산이 반복된다.
 * 1. 가장 처음 붐버맨은 일부 칸에 폭탄을 설치, 모든 폭탄이 설치된 시간은 같다
 * 2. 다음 1초 동안 붐버맨은 아무것도 하지 않음
 * 3. 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 곳에 폭탄 설치, 폭탄은 모두 동시에 설치했다고 가정
 * 4. 1초가 지난 후 3초전에 설치된 폭탄이 모두 폭발
 * 5. 3번과 4번 반복
 * 폭탄은 설치 후 3초후에 터진다.
 * 이 때, 원하는 시간 후의 상태를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C, N;
    static char[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = tmp[0];
        C = tmp[1];
        N = tmp[2];

        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'O')
                    arr[i][j] = '1';
            }
        }
        bomBerMan();
    }

    static void bomBerMan() {
        if (N >= 1)
            timeAdd(); // 1
        if (N >= 2)
            addBomb(); // 2
        if (N >= 3)
            boom(); // 3
        if (N >= 4) {
            int loop = (N - 3) % 4;

            if (loop >= 1)
                addBomb();
            if (loop >= 2)
                boom();
            if (loop >= 3)
                addBomb();
        }
        result(arr);
    }

    static void boom() {
        // 시간 추가하기
        timeAdd();

        // 폭탄 터트리기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] != '3')
                    continue;

                for (int z = 0; z < 4; z++) {
                    int nx = i + dx[z];
                    int ny = j + dy[z];

                    if (0 > nx || nx >= R)
                        continue;
                    if (0 > ny || ny >= C)
                        continue;
                    if (arr[nx][ny] == '3')
                        continue;
                    arr[nx][ny] = '.';
                }
                arr[i][j] = '.';
            }
        }
    }

    static void addBomb() {
        // 시간 추가하기
        timeAdd();

        // 폭탄 추가하기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == '.')
                    arr[i][j] = '1';
            }
        }
    }

    static void timeAdd() {
        // 시간 추가
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if ('1' <= arr[i][j] && arr[i][j] < '3') {
                    arr[i][j] += 1;
                }
            }
        }
    }

    static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void result(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j] != '.' ? 'O' : arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

// 그럼 무조건 짝수 시간에는 모든 곳에 폭탄이 설치가된 상태
// 한번 터트리고 난 후가 초기의 상태라고 생각해야함