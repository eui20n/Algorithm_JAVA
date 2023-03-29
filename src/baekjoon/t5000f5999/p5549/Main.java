/**
 * 문제 이름 : 행성 탐사
 * URL : https://www.acmicpc.net/problem/5549
 * 문제 설명 :
 * 정글, 바다, 얼음으로 뒤얽힌 행성이다. 거주 할 수 있는 구역의 지도를 만들어 지구로 보냈다.
 * 가로 N, 세로 M 직사각형 모양의 지도이다. 각 구역은 알파벳으로 표시되어 있다.
 * 정글 => J, 바다 => O, 얼음 => I
 * 조사 대상 영역을 K개 만들었다. 이때, 각 영역에 정글, 바다, 얼음이 각각 몇 개씩 있는지 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t5000f5999.p5549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C;
    static int K;
    static int[][] ice;
    static int[][] jungle;
    static int[][] ocean;
    static int[][] iceR;
    static int[][] jungleR;
    static int[][] oceanR;
    static int[][] iceC;
    static int[][] jungleC;
    static int[][] oceanC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(br.readLine());

        ice = new int[R][C];
        jungle = new int[R][C];
        ocean = new int[R][C];

        iceR = new int[R][C];
        jungleR = new int[R][C];
        oceanR = new int[R][C];
        iceC = new int[R][C];
        jungleC = new int[R][C];
        oceanC = new int[R][C];

        for (int i = 0; i < R; i++) {
            String[] tmp2 = br.readLine().split("");

            for (int j = 0; j < tmp2.length; j++) {
                if (tmp2[j].equals("O")) {
                    ocean[i][j] = 1;
                } else if (tmp2[j].equals("J")) {
                    jungle[i][j] = 1;
                } else if (tmp2[j].equals("I")) {
                    ice[i][j] = 1;
                }
            }
        }
        makeSumC();
        makeSumR();
        print(jungleR);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            int[] range = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int jungleSum = arrSum(range, jungleR, jungleC);
            int oceanSum = arrSum(range, oceanR, oceanC);
            int iceSum = arrSum(range, iceR, iceC);
            sb.append(jungleSum).append(" ").append(oceanSum).append(" ").append(iceSum).append("\n");
        }
        System.out.println(sb);

    }

    static int arrSum(int[] range, int[][] arrR, int[][] arrC) {
        int startX = range[0] - 1;
        int startY = range[1] - 1;
        int endX = range[2] - 1;
        int endY = range[3] - 1;

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        a = arrR[endX][endY];
        if (startX - 1 >= 0) {
            b = arrR[startX - 1][endY];
        }
        if (startY - 1 >= 0) {
            c = arrC[endX][startY - 1];
        }
        if (startX - 1 >= 0 && startY - 1 >= 0) {
            d = arrR[startX - 1][startY - 1];
        }
        return (a - b - c + d);

    }

    static void makeSumR() {
        jungleR[0][0] = jungle[0][0];
        oceanR[0][0] = ocean[0][0];
        iceR[0][0] = ice[0][0];

        for (int i = 0; i < R; i++) {
            if (i != 0) {
                jungleR[i][0] += jungle[i][0] + jungleR[i - 1][C - 1];
                oceanR[i][0] += ocean[i][0] + oceanR[i - 1][C - 1];
                iceR[i][0] += ice[i][0] + iceR[i - 1][C - 1];
            }
            for (int j = 1; j < C; j++) {
                // 정글
                jungleR[i][j] = (jungle[i][j] + jungleR[i][j - 1]);
                // 바다
                oceanR[i][j] = (ocean[i][j] + oceanR[i][j - 1]);
                // 얼음
                iceR[i][j] = (ice[i][j] + iceR[i][j - 1]);
            }
        }
    }

    static void makeSumC() {
        jungleC[0][0] = jungle[0][0];
        oceanC[0][0] = ocean[0][0];
        iceC[0][0] = ice[0][0];

        for (int j = 0; j < C; j++) {
            if (j != 0) {
                jungleC[0][j] += jungle[0][j] + jungleC[R - 1][j - 1];
                oceanC[0][j] += ocean[0][j] + oceanC[R - 1][j - 1];
                iceC[0][j] += ice[0][j] + iceC[R - 1][j - 1];
            }
            for (int i = 1; i < R; i++) {
                // 정글
                jungleC[i][j] = (jungle[i][j] + jungleC[i - 1][j]);
                // 바다
                oceanC[i][j] = (ocean[i][j] + oceanC[i - 1][j]);
                // 얼음
                iceC[i][j] = (ice[i][j] + iceC[i - 1][j]);
            }
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
// bfs 한번
//  100000

// 이거 구간합임
// 정글, 땅, 얼음을 나눠서 그냥 구간합을 하면 됨
// 에잉 쯥.... 그냥 구하기 => 구간합 기억이 안나네