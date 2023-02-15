/**
 * 문제 이름 : 배열 돌리기 3
 * URL : https://www.acmicpc.net/problem/16935
 * 문제 설명 : 배열을 돌리면 됨
 * 1. 상하 반전 -> 그냥 아래서 부터 올라가면 됨
 * 2. 좌우 반전 -> 회전 후 상하 반전하고 다시 회전
 * 3. 오른쪽으로 90도 -> 그냥 회전
 * 4. 왼쪽으로 90도 -> 왼쪽 3번 회전
 * 5. 구역을 나눠서 시계 방향
 * 6. 구역을 나눠서 반시계 방향
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, R;
    static int[] operatorArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        N = tmp[0];
        M = tmp[1];
        R = tmp[2];

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        operatorArr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] result = rotateArr(arr);
        print(result);
    }

    static int[][] rotateArr(int[][] arr) {
        for (int operator : operatorArr) {
            if (operator == 1) {
                arr = upSideDown(arr);
            } else if (operator == 2) {
                arr = reverseLeftAndRight(arr);
            } else if (operator == 3) {
                arr = rightRotate(arr);
                N = arr.length;
                M = arr[0].length;
            } else if (operator == 4) {
                arr = leftRotate(arr);
                N = arr.length;
                M = arr[0].length;
            } else if (operator == 5) {
                arr = operator5(arr);
            } else {
                arr = operator6(arr);
            }
        }
        return arr;
    }

    static int[][] upSideDown(int[][] arr) {
        int[][] newArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            newArr[i] = arr[N - 1 - i];
        }

        return newArr;
    }

    static int[][] reverseLeftAndRight(int[][] arr) {
        int[][] newArr = new int[N][M];
        for (int j = 0; j < M; j++){
            for (int i = 0; i < N; i++){
                newArr[i][j] = arr[i][M - 1- j];
            }
        }

        return newArr;
    }

    static int[][] rightRotate(int[][] arr) {
        int[][] newArr = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newArr[j][i] = arr[N - 1 - i][j];
            }
        }

        return newArr;
    }

    static int[][] leftRotate(int[][] arr) {
        int[][] newArr = new int[M][N];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                newArr[j][i] = arr[i][M - 1 - j];
            }
        }
        return newArr;
    }

    static int[][] operator5(int[][] arr){
        int[][] newArr = new int[N][M];

        for (int i = N / 2; i < N; i++){
            for (int j = 0; j < M / 2; j++){
                // 1구역으로 가기
                newArr[i - N / 2][j] = arr[i][j];
            }
        }

        for (int i = 0; i < N / 2; i++){
            for (int j = 0; j < M / 2; j++){
                // 2구역으로 가기
                newArr[i][j + M / 2] = arr[i][j];
            }
        }

        for (int i = 0; i < N / 2; i++){
            for (int j = M / 2; j < M; j++){
                // 3구역으로 가기
                newArr[i + N / 2][j] = arr[i][j];
            }
        }

        for (int i = N / 2; i < N; i++){
            for (int j = M / 2; j < M; j++){
                // 4구역으로 가기
                newArr[i][j - M / 2] = arr[i][j];
            }
        }
        return newArr;
    }

    static int[][] operator6(int[][] arr){
        int[][] newArr = new int[N][M];

        for (int i = 0; i < N / 2; i++){
            for (int j = M / 2; j < M; j++){
                // 1구역으로 가기
                newArr[i][j - M / 2] = arr[i][j];
            }
        }

        for (int i = N / 2; i < N; i++){
            for (int j = M / 2; j < M; j++){
                // 2구역으로 가기
                newArr[i - N / 2][j] = arr[i][j];
            }
        }

        for (int i = N / 2; i < N; i++){
            for (int j = 0; j < M / 2; j++){
                // 3구역으로 가기
                newArr[i][j + M / 2] = arr[i][j];
            }
        }

        for (int i = 0; i < N / 2; i++){
            for (int j = 0; j < M / 2; j++){
                // 4구역으로 가기
                newArr[i + N / 2][j] = arr[i][j];
            }
        }

        return newArr;
    }

    static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

// N과 M은 배열을 돌리면 값이 계속 바뀜
// N = 4, M = 6
// 1구역 (0, 0) => (1, 2)
// 2구역 (0, 3) => (1, 5)
// 3구역 (2, 3) => (3, 5)
// 4구역 (2, 0) => (3, 2)