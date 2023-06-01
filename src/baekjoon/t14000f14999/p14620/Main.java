/**
 * 문제 이름 : 꽃길
 * URL : https://www.acmicpc.net/problem/14620
 * 문제 설명 :
 * 꽃 3개를 피울 수 있는 땅 중 가장 양분이 적은 땅을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();
    }

    static void check() {
        boolean[][] visited = new boolean[N][N];
        flowerPlanting(visited, 0, 0, 0, 0);
        System.out.println(ans);
    }

    static void flowerPlanting(boolean[][] visited, int x, int y, int cnt, int result) {
        // 외곽에는 어차피 꽃을 심을 수 없음 => 귀찮아서 그냥 할꺼고, 만약에 시간이 넘친다면 여기 먼저 바꾸기
        if (cnt == 3) {
            ans = Math.min(ans, result);
            return;
        }

        if (x >= N)
            // 끝까지 간 것
            return;

        if (y >= N) {
            // 범위를 벗어남
            flowerPlanting(visited, x + 1, 0, cnt, result);
            return;
        }

        if (visited[x][y]) {
            flowerPlanting(visited, x, y + 1, cnt, result);
            return;
        }

        int tmpValue = arr[x][y];

        for (int z = 0; z < 4; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny]) {
                flowerPlanting(visited, x, y + 1, cnt, result);
                return;
            }
            tmpValue += arr[nx][ny];
        }
        // 방문 처리
        checkVisited(visited, true, x, y);

        // 다음 으로 가기
        flowerPlanting(visited, x, y + 1, cnt + 1, result + tmpValue);

        // 돌아옴
        checkVisited(visited, false, x, y);

        // 현재 있는 곳을 선택하지 않고 넘어가는 경우
        flowerPlanting(visited, x, y + 1, cnt, result);

    }

    static void checkVisited(boolean[][] visited, boolean check, int x, int y) {
        for (int z = 0; z < 4; z++) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            visited[nx][ny] = check;
        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
