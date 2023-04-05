/**
 * 문제 이름 : Gaaaaaaaaaarden
 * URL : https://www.acmicpc.net/problem/18809
 * 문제 설명 :
 * 초록색 배양액과 빨간색 배양액이 동시에 만나면 꽃이 필때, 필 수 있는 꽃의 최대값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t18000f18999.p18809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, green, red;
    static int[][] arr;
    static List<int[]> canArea = new ArrayList<>();
    static boolean[] visited;
    static Stack<Integer> canGreenFlower = new Stack<>();
    static Stack<Integer> canRedFlower = new Stack<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = tmp[0];
        C = tmp[1];
        green = tmp[2];
        red = tmp[3];

        arr = new int[R][C];

        int visitedCnt = 0;

        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) {
                    canArea.add(new int[]{i, j});
                    visitedCnt += 1;
                }
            }
        }
        visited = new boolean[visitedCnt];
        check();
    }

    static void check() {
        goGreen(green, 0);
        System.out.println(answer);
    }

    static void goGreen(int cnt, int idx) {
        if (cnt == 0) {
            goRed(red, 0);
            return;
        }

        for (int i = idx; i < visited.length; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            canGreenFlower.add(i);
            goGreen(cnt - 1, i);
            canGreenFlower.pop();
            visited[i] = false;
        }
    }

    static void goRed(int cnt, int idx) {
        if (cnt == 0) {
            int flower = flower();
            answer = Math.max(flower, answer);
            return;
        }

        for (int i = idx; i < visited.length; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            canRedFlower.add(i);
            goRed(cnt - 1, i);
            canRedFlower.pop();
            visited[i] = false;
        }
    }

    static int flower() {
        // 여기가 문제인듯
        Deque<int[]> q = new ArrayDeque<>();
        int[][][] flowerVisited = new int[2][R][C]; // 1 초록, 0 빨강

        boolean[][] goodFlower = new boolean[R][C];

        for (int idx : canGreenFlower) {
            int[] area = canArea.get(idx);
            q.add(new int[] {area[0], area[1], 1, 1}); // 배양액 위치, 시간, 색
            flowerVisited[1][area[0]][area[1]] = 1;
        }

        for (int idx : canRedFlower) {
            int[] area = canArea.get(idx);
            q.add(new int[] {area[0], area[1], 1, 0}); // 배양액 위치, 시간, 색
            flowerVisited[0][area[0]][area[1]] = 1;
        }

        int result = 0;

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int time = tmp[2];
            int color = tmp[3]; // 1 초록, 0 빨강

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (flowerVisited[color][nx][ny] != 0)
                    continue;
                if (arr[nx][ny] == 0)
                    continue;
                if (goodFlower[x][y])
                    continue;
                if (flowerVisited[color ^ 1][nx][ny] < time + 1 && flowerVisited[color ^ 1][nx][ny] != 0)
                    continue;
                if (flowerVisited[color ^ 1][nx][ny] == time + 1 && !goodFlower[nx][ny]){
                    result += 1;
                    goodFlower[nx][ny] = true;
                    continue;
                }
                q.add(new int[] {nx, ny, time + 1, color});
                flowerVisited[color][nx][ny] = time + 1;
            }
        }
        return result;
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

// 배양액을 뿌릴 수 있는 땅을 고른다음 조합으로 배양액을 넣기
// 배양액의 색별로 조합을 돌리면 될 듯
// 배양액을 뿌릴 수 있는 땅이 더 많음
// 겹처서 갈 수 없음