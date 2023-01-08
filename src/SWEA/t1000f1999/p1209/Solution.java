/**
 * 문제 이름 : Sum
 * 문제 설명 : 가로, 세로, 대각선 중 가장 합이 큰 것을 출력하면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 연습
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int[][] arr = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            T = Integer.parseInt(br.readLine());

            for (int idx = 0; idx < 100; idx++) {
                int[] tmpArr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                arr[idx] = tmpArr;
            }

            System.out.println("#" + T + " " + check());

        }
    }

    public static int check() {
        int result = 0;
        int tmp = 0;

        for (int x = 0; x < 100; x++) {
            tmp = 0;
            for (int y = 0; y < 100; y++) {
                tmp += arr[x][y];
            }
            result = Math.max(tmp, result);
        }

        for (int y = 0; y < 100; y++) {
            tmp = 0;
            for (int x = 0; x < 100; x++) {
                tmp += arr[x][y];
            }
            result = Math.max(tmp, result);
        }

        tmp = 0;
        for (int x = 0; x < 100; x++) {
            tmp += arr[x][x];
        }

        result = Math.max(tmp, result);

        tmp = 0;
        for (int x = 0; x < 100; x++) {
            int y = 100 - x - 1;
            tmp += arr[x][y];
        }

        result = Math.max(result, tmp);

        return result;

    }

}
