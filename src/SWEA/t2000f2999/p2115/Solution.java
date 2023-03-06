/**
 * 문제 이름 : 벌꿀 채취
 * 문제 번호 : 2115
 * 문제 설명 :
 * 정사각형에 벌꿀이 있고, 일꾼이 두명이 바구니를 들고 있다. 바구니에 최대 벌꿀을 수용할 수 있는 양이 있고 일꾼도 가로로 범위가 있다.
 * 서로 범위가 겹치면 안된다. 이 때 벌꿀을 최대로 채취하면 얼마나 채취할 수 있는지 출력해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t2000f2999.p2115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int T;
    static int tmpNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int N, M, C;
            String[] tmp = br.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            M = Integer.parseInt(tmp[1]);
            C = Integer.parseInt(tmp[2]);

            int[][] arr = new int[N][N];
            for (int j = 0; j < N; j++) {
                arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.printf("#%d %d %n", i, beeHoney(N, M, C, arr));
        }
    }

    static int beeHoney(int N, int M, int C, int[][] arr) {
        int[][] newArr = new int[N][N];
        int[][] checkArr = new int[N][N];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + M > N)
                    break;
                int[] tmpArr = new int[M];
                for (int k = 0; k < M; k++) {
                    checkArr[i][j + k] = cnt;
                    tmpArr[k] = arr[i][j + k];
                }
                cnt++;
                tmpNum = 0;
                check(tmpArr, C, new ArrayList<>(), 0);
                newArr[i][j] = tmpNum;
            }
        }
        return maxHoney(newArr, checkArr, M);
    }

    static int maxHoney(int[][] arr, int[][] checkArr, int M) {
        // 각 지역에서 채취할 수 있는 벌꿀을 가지고 두 양봉업자가 구할 수 있는 최대값을 구해줌
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int tmp = arr[i][j];
                for (int x = 0; x < arr.length; x++) {
                    for (int y = 0; y < arr.length; y++) {
                        if (i == x && checkArr[x][y] - checkArr[i][j] <= M - 1)
                            // 겹치는 부분은 넘겨줌
                            continue;
                        int tmp2 = arr[x][y];
                        result = Math.max(result, tmp + tmp2);
                    }
                }
            }
        }

        return result;
    }

    static void check(int[] arr, int C, List<Integer> honey, int idx) {
        // 채취할 벌꿀을 배열에 저장해줌
        // 겹치는 부분은 신경 안씀
        if (C < 0) {
            return;
        }
        if (honey.size() >= 1) {
            int num = 0;

            for (int honeyAmount : honey) {
                num += honeyAmount * honeyAmount;
            }
            tmpNum = Math.max(tmpNum, num);
        }


        for (int i = idx; i < arr.length; i++) {
            honey.add(arr[i]);
            check(arr, C - arr[i], honey, i + 1);
            honey.remove(honey.size() - 1);
        }
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
}
