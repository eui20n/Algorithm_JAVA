/**
 * 문제 이름 : 어두운 건 무서워
 * URL : https://www.acmicpc.net/problem/16507
 * 문제 설명 :
 * 사진에서 각 픽셀의 밝기가 나옴
 * 구간이 주어질 때, 해당 구간의 밝기의 평균을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 2차원 누적합
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R;
    static int C;
    static int T;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        T = Integer.parseInt(tmp[2]);

        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] sumArr = makeArr(arr);

        for (int t = 0; t < T; t++) {
            int[] loc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            check(sumArr, loc);
        }
    }

    static void check(int[][] sumArr, int[] loc) {
        int smallX = loc[0] - 1;
        int smallY = loc[1] - 1;
        int bigX = loc[2] - 1;
        int bigY = loc[3] - 1;

        int bigSquare = sumArr[bigX][bigY];
        int smallSquareX = smallX - 1 >= 0 ? sumArr[smallX - 1][bigY] : 0;
        int smallSquareY = smallY - 1 >= 0 ? sumArr[bigX][smallY - 1] : 0;
        int smallSquare = smallX - 1 >= 0 && smallY - 1 >= 0 ? sumArr[smallX - 1][smallY - 1] : 0;

        int result = bigSquare - smallSquareX - smallSquareY + smallSquare;

        System.out.println(result / ((bigX - smallX + 1) * (bigY - smallY + 1)));
    }

    static int[][] makeArr(int[][] arr) {
        int[][] sumArr = new int[R + 1][C + 1];
        int[][] resultArr = new int[R][C];

        for (int i = 1; i < R + 1; i++) {
            for (int j = 1; j < C + 1; j++) {
                sumArr[i][j] = sumArr[i - 1][j] + sumArr[i][j - 1] + arr[i - 1][j - 1] - sumArr[i - 1][j - 1];

                resultArr[i - 1][j - 1] = sumArr[i][j];
            }
        }
        return resultArr;
    }
}
