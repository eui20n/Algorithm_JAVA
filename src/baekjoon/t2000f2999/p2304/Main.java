/**
 * 문제 이름 : 창고 다각형
 * URL : https://www.acmicpc.net/problem/2304
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static boolean[][] arr = new boolean[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x, y;
            int[] inputTmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            x = inputTmp[0];
            y = inputTmp[1];
            makeArr(x, y);
        }

        check();
    }

    static void check() {
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            int meet = 0; // 기둥을 만나면 기록을 해줌
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j]) {
                    if (meet != 0) {
                        result += j - meet - 1;
                    }
                    result += 1;
                    meet = j;
                }
            }
        }
        System.out.println(result);
    }

    static void makeArr(int x, int y) {
        for (int i = arr.length - 1; i >= arr.length - y; i--) {
            arr[i][x] = true;
        }
    }

    static void printArr(boolean[][] booleanArr) {
        for (int i = 0; i < booleanArr.length; i++) {
            for (int j = 0; j < booleanArr[i].length; j++) {
                int printNum = booleanArr[i][j] ? 1 : 0;
                System.out.print(printNum + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
    최대 크기가 1000 x 1000이니까, 먼저 1000 x 1000크기의 배열을 생성
    그 후, 입력을 받은 곳에 그림
    그러고 나서 가장 위에 부터 내려오면서 계산하면 될 듯
    공간을 아끼기 위해서 배열은 boolean의 배열로 만들기
 */