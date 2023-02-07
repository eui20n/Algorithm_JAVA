/**
 * 문제 이름 : 감시
 * URL : https://www.acmicpc.net/problem/15683
 * 문제 설명 : CCTV를 적절히 설치해서 만들 수 있는 최소 사각지대를 구해라
 * 시간복잡도 : ....
 * 핵심 로직 및 생각 : 음.. 그냥 굉장히 귀찮은 문제로 리팩토링을 통해서 코드를 깔끔하게 할 필요가 있음
 *                  핵심은 디테일한 조건을 잘 따져서 구현을 해야함
 * 소요 시간 : 1시간 30분
 * 제출할 때, package 삭제할 것
 */
package baekjoon.t15000f15999.p15683;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] arr;
    static List<int[]> CCTV;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];

        CCTV = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int inputValue = sc.nextInt();
                if (inputValue == 0) {
                    arr[i][j] = inputValue;
                } else if (inputValue == 6){
                    arr[i][j] = -1;
                } else {
                    CCTV.add(new int[]{i, j, inputValue});
                    arr[i][j] = 0;
                }
            }
        }
        check(0, arr);
        System.out.println(result);
    }

    static void check(int idx, int[][] arr) {
        if (idx == CCTV.size()) {
//            showArr(arr);
            int cnt = 0;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    if (arr[i][j] == 0){
                        cnt += 1;
                    }
                }
            }
            result = Math.min(cnt, result);
            return;
        }

        int[] CCTVInfo = CCTV.get(idx);
        int x = CCTVInfo[0];
        int y = CCTVInfo[1];
        int num = CCTVInfo[2];
//        System.out.printf("x : %d, y : %d, num : %d %n", x, y, num);

        if (num == 1) {
            for (int z = 0; z < 4; z++) {
                go(arr, x, y, z, idx + 1, true);
                check(idx + 1, arr);
                go(arr, x, y, z, idx + 1, false);
            }
        } else if (num == 2) {
            int tmp = 0;
            for (int i = 0; i < 2; i++) {
                if (i == 1)
                    tmp = 1;
                go(arr, x, y, tmp, idx + 1, true);
                go(arr, x, y, tmp + 2, idx + 1, true);
                check(idx + 1, arr);
                go(arr, x, y, tmp, idx + 1, false);
                go(arr, x, y, tmp + 2, idx + 1, false);
            }

        } else if (num == 3) {
            for (int dxZ : new int[]{0, 2})
                for (int dyZ : new int[]{1, 3}) {
                    go(arr, x, y, dxZ, idx + 1, true);
                    go(arr, x, y, dyZ, idx + 1, true);
                    check(idx + 1, arr);
                    go(arr, x, y, dxZ, idx + 1, false);
                    go(arr, x, y, dyZ, idx + 1, false);
                }

        } else if (num == 4) {
            go(arr, x, y, 0, idx + 1, true);
            go(arr, x, y, 1, idx + 1, true);
            go(arr, x, y, 2, idx + 1, true);
            check(idx + 1, arr);
            go(arr, x, y, 0, idx + 1, false);
            go(arr, x, y, 1, idx + 1, false);
            go(arr, x, y, 2, idx + 1, false);

            go(arr, x, y, 0, idx + 1, true);
            go(arr, x, y, 1, idx + 1, true);
            go(arr, x, y, 3, idx + 1, true);
            check(idx + 1, arr);
            go(arr, x, y, 0, idx + 1, false);
            go(arr, x, y, 1, idx + 1, false);
            go(arr, x, y, 3, idx + 1, false);

            go(arr, x, y, 0, idx + 1, true);
            go(arr, x, y, 2, idx + 1, true);
            go(arr, x, y, 3, idx + 1, true);
            check(idx + 1, arr);
            go(arr, x, y, 0, idx + 1, false);
            go(arr, x, y, 2, idx + 1, false);
            go(arr, x, y, 3, idx + 1, false);

            go(arr, x, y, 1, idx + 1, true);
            go(arr, x, y, 2, idx + 1, true);
            go(arr, x, y, 3, idx + 1, true);
            check(idx + 1, arr);
            go(arr, x, y, 1, idx + 1, false);
            go(arr, x, y, 2, idx + 1, false);
            go(arr, x, y, 3, idx + 1, false);

        } else if (num == 5) {
            go(arr, x, y, 0, idx + 1, true);
            go(arr, x, y, 1, idx + 1, true);
            go(arr, x, y, 2, idx + 1, true);
            go(arr, x, y, 3, idx + 1, true);
            check(idx + 1, arr);
            go(arr, x, y, 0, idx + 1, false);
            go(arr, x, y, 1, idx + 1, false);
            go(arr, x, y, 2, idx + 1, false);
            go(arr, x, y, 3, idx + 1, false);
        }
    }

    static void go(int[][] arr, int x, int y, int d, int idx, boolean check) {
        int checked = idx;
        if (!(check)) {
            checked = idx * (-1);
        }
        arr[x][y] += checked;
        while (true) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (0 > nx || nx >= N)
                break;
            if (0 > ny || ny >= M)
                break;
            if (arr[nx][ny] == -1)
                break;
            arr[nx][ny] += checked;
            x = nx;
            y = ny;
        }
    }

    static void showArr(int[][] arr){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
