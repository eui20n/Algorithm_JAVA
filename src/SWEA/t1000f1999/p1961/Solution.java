/**
 * 문제 이름 : 숫자 배열 회전
 * 문제 설명 : 입력으로 주어지는 배열을 90도, 180도, 270도 회전시켜서 출력하면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 연습
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int N;
    static int[][] arr;
    static int[] intArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][];

            for (int idx = 0; idx < N; idx++) {
                intArr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                arr[idx] = intArr;
            }

            int[][] result90 = rotation(arr);
            int[][] result180 = rotation(result90);
            int[][] result270 = rotation(result180);

            System.out.println("#" + i);
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    System.out.print(result90[x][y]);
                }
                System.out.print(" ");
                for (int y = 0; y < N; y++) {
                    System.out.print(result180[x][y]);
                }
                System.out.print(" ");
                for (int y = 0; y < N; y++) {
                    System.out.print(result270[x][y]);
                }
                System.out.println(" ");
            }
        }



    }

    public static int[][] rotation(int[][] arr) {
        int[][] result = new int[N][N];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                result[x][y] = arr[N - y - 1][x];
            }
        }
        return result;
    }

}
