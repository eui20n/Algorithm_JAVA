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
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    static int T;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");
            int N = Integer.parseInt(tmp[0]);
            int W = Integer.parseInt(tmp[1]); // 열
            int H = Integer.parseInt(tmp[2]); // 행

            int[][] arr = new int[H][W];
            result = Integer.MAX_VALUE;

            for (int j = 0; j < H; j++) {
                arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            check(arr, new int[N], N, W, H, 0);
            System.out.printf("#%d %d %n", i, result);
//            breakWall(arr, new int[] {3}, W, H);
//            print(arr);
        }
    }

    static void check(int[][] arr, int[] broken, int N, int W, int H, int cnt) {
        if (N == cnt) {
            int[][] newArr = deepCopy(arr, W, H);
            int leftWall = breakWall(newArr, broken, W, H);
            result = Math.min(leftWall, result);
            return;
        }

        for (int i = 0; i < W; i++) {
            if (result == 0)
                return;

            broken[cnt] = i + 1;
            check(arr, broken, N, W, H, cnt + 1);
            broken[cnt] = 0;
        }
    }

    static int breakWall(int[][] arr, int[] broken, int W, int H) {
        int leftWall = 0;
        for (int wall : broken) {
            for (int i = 0; i < H; i++) {
                if (arr[i][wall - 1] == 0)
                    continue;
                // 아무 벽돌도 없는 경우가 있을 수도 있음
                // 처리는 그냥 하기
                broken(arr, i, wall - 1);
//                print(arr);
                // 중력
                leftWall = gravity(arr);
                if (leftWall == 0)
                    return leftWall;

                break;
            }
        }
        return leftWall;
    }

    static int gravity(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;
        int amount = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        for (int j = 0; j < c; j++) {
            for (int i = r - 1; i >= 0; i--) {
                if (arr[i][j] == 0)
                    continue;
                dq.add(arr[i][j]);
                arr[i][j] = 0;
                amount += 1;
            }
            for (int i = r - 1; i >= 0; i--) {
                if (dq.isEmpty())
                    break;
                arr[i][j] = dq.pollFirst();
            }
        }

        return amount;
    }


    static void broken(int[][] arr, int x, int y) {
        // 해당 위치에 있는 벽돌 부수기
        int go = arr[x][y] - 1; // 얼만큼 가는지 알려주는 변수
        arr[x][y] = 0;

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y, go, 0});
        q.add(new int[]{x, y, go, 1});
        q.add(new int[]{x, y, go, 2});
        q.add(new int[]{x, y, go, 3});

        while (true) {
            if (q.isEmpty())
                break;
            int[] tmp = q.poll();
            x = tmp[0];
            y = tmp[1];
            int wallCnt = tmp[2];
            int z = tmp[3];

            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= arr.length)
                continue;
            if (0 > ny || ny >= arr[0].length)
                continue;
            if (wallCnt == 0)
                continue;
            if (arr[nx][ny] - 1 > 0) {
                q.add(new int[] {nx, ny, arr[nx][ny] - 1, 0});
                q.add(new int[] {nx, ny, arr[nx][ny] - 1, 1});
                q.add(new int[] {nx, ny, arr[nx][ny] - 1, 2});
                q.add(new int[] {nx, ny, arr[nx][ny] - 1, 3});
            }
            q.add(new int[] {nx, ny, wallCnt - 1, z});
            arr[nx][ny] = 0;
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
