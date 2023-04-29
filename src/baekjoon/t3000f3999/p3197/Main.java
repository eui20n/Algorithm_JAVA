/**
 * 문제 이름 : 백조의 호수
 * URL : https://www.acmicpc.net/problem/3197
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C;
    static char[][] arr;
    static int[][] swan = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = new char[R][C];
        int swan_idx = 0;

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'L') {
                    swan[swan_idx] = new int[]{i, j};
                    System.out.println(Arrays.toString(swan[swan_idx]));
                    swan_idx++;
                }
            }
        }
        print(arr);
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
}
