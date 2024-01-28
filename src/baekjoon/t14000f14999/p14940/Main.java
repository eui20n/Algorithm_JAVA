/**
 * 문제 이름 : 쉬운 최단거리
 * URL : https://www.acmicpc.net/problem/14940
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int R, C;
    static int[][] arr;
    static int[] start = {1, -1};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = tmpInput[0];
        C = tmpInput[1];

        distance = new int[R][C];
        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (start[0] * start[1] < 0) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] == 2) {
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }
        }
        check();
    }

    static void check() {
        initDistArr();
        bfs();
        printDistance();
    }

    static void bfs() {
        int startX = start[0];
        int startY = start[1];
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[] {startX, startY, 0});

        while (true) {
            if (q.isEmpty())
                break;

            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            int dist = p[2];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (arr[nx][ny] == 0)
                    continue;
                if (distance[nx][ny] <= dist + 1)
                    continue;
                distance[nx][ny] = dist + 1;
                q.add(new int[] {nx, ny, dist + 1});
            }
        }
    }

    static void initDistArr() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 2)
                    continue;
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static void printDistance() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int printNum = distance[i][j];
                if (arr[i][j] == 1 && printNum == Integer.MAX_VALUE)
                    printNum = -1;
                if (arr[i][j] == 0)
                    printNum = 0;
                sb.append(printNum).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
