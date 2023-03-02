/**
 * 문제 이름 : 벽돌 깨기
 * 문제 번호 : 5656
 * 문제 설명 :
 * 구술을 쏘아 구술을 쏘아 벽돌을 깨트리는 게임을 하려고 한다.
 * 구슬은 N번만 쏠 수 있고, 벽돌들의 정보는 아래와 같이 W x H 배열로 주어진다.
 * 게임의 규칙은 다음과 같다.
 * 1. 구슬을 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * 2. 벽돌의 숫자 1 ~ 9로 표현되며, 구술이 명중한 벽돌은 상하좌우로 (벽돌에 적힌 숫자 - 1) 칸 만큼 같이 제거된다.
 * N개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하려고 한다.
 * N, W, H와 벽돌의 정보가 주어질 때, 남은 벽돌의 개수를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t5000f5999.p5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");
            int N = Integer.parseInt(tmp[0]);
            int W = Integer.parseInt(tmp[1]); // 열
            int H = Integer.parseInt(tmp[2]); // 행

            int[][] arr = new int[H][W];

            for (int j = 0; j < H; j++) {
                arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            check(arr, new int[N], N, W, H, 0);
            print(arr);
        }
    }

    static void check(int[][] arr, int[] broken, int N, int W, int H, int cnt) {
        if (N == cnt) {
            int[][] newArr = deepCopy(arr, W, H);
            breakWall(newArr, broken, W, H);
        }

        for (int i = 0; i < W; i++) {
            broken[cnt] = i + 1;
            check(arr, broken, N, W, H, cnt + 1);
            broken[cnt] = 0;
        }
    }

    static void breakWall(int[][] arr, int[] broken, int W, int H) {
        for (int wall : broken) {
            for (int i = H - 1; i >= 0; i--) {
                if (arr[i][wall - 1] == 0)
                    continue;

                // 3방향 보기 => 한방향씩 보면서 처리하기, 위는 안봐도됨
                // 처리는 백트랙킹하기
                broken(arr, i, wall - 1);
            }
        }
    }

    static void broken(int[][] arr, int x, int y) {
        // 해당 위치에 있는 벽돌 부수기
        int go = arr[x][y] - 1; // 얼만큼 가는지 알려주는 변수
        for (int z = 0; z < 3; z++) {
            for (int i = 0; i < go; i++) {

            }
        }
    }

    static int[][] deepCopy(int[][] arr, int W, int H) {
        int[][] newArr = new int[H][W];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, newArr[i].length);
        }
        return newArr;
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
