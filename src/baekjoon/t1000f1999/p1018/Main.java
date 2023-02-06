package baekjoon.t1000f1999.p1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = tmpInput[0];
        M = tmpInput[1];

        String[][] arr = new String[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split("");
        }

        System.out.println(redrawBoard(arr));
    }

    static int redrawBoard(String[][] arr) {
        // 완전 탐색
        // 현재 위치에서 8 by 8을 확인
        // 확인을 할 때, 처음이 검정색인거랑, 흰색인거로 확인
        // 확인한 값들 중 가장 작은 값을 출력

        int result = Integer.MAX_VALUE;

        for (int x = 0; x <= N - 8; x++) {
            for (int y = 0; y <= M - 8; y++) {
                result = Math.min(result, redrawWhite(arr, x, y));
                result = Math.min(result, redrawBlack(arr, x, y));
//				System.out.println(result);
//				System.out.printf("White : %d, Black : %d %n", redrawWhite(arr, x, y), redrawBlack(arr, x, y));
            }
        }

        return result;
    }

    static int redrawWhite(String[][] arr, int r, int c) {
        // 그냥 해보고, 만약에 정답이 이상하게 나오면, 깊은 복사하기
        // 시작이 흰색일 때

        int white = 0;
        int index;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // (0 0) (0 1) (0 2) => 두 수의 합이 짝수이면 시작하는 것(흰)
                // (1 0) (1 1) (1 2) => 두 수의 합이 홀수이면 다른 것(검)
                index = x + y;

                if (index % 2 == 0 && arr[x + r][y + c].equals("B")) {
                    white += 1;
                    continue;
                }
                if (index % 2 == 1 && arr[x + r][y + c].equals("W")) {
                    white += 1;
                    continue;
                }

            }
        }

        return white;

    }

    static int redrawBlack(String[][] arr, int r, int c) {
        // 그냥 해보고, 만약에 정답이 이상하게 나오면, 깊은 복사하기
        // 시작이 검정색 일 때

        int black = 0;
        int index;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // (0 0) (0 1) (0 2) => 두 수의 합이 짝수이면 시작하는 것(검)
                // (1 0) (1 1) (1 2) => 두 수의 합이 홀수이면 다른 것(흰)
                index = x + y;

                if (index % 2 == 0 && arr[x + r][y + c].equals("W")) {
                    black += 1;
                    continue;
                }
                if (index % 2 == 1 && arr[x + r][y + c].equals("B")) {
                    black += 1;
                    continue;
                }
            }
        }
        return black;
    }
}
