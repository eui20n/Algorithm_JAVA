/**
 * 문제 이름 : 아스키 도형
 * URL : https://www.acmicpc.net/problem/3495
 * 문제 설명 :
 * 메모장에 '.', '\', '/' 을 이용해서 1 x 1 단위 정사각형을 그렸다.
 * 이 때, 이 정사각형들이 합쳐져서 만들어진 도형의 넓이를 구해라
 * 단, 도형은 1개이다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3495;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {
    static int h;
    static int w;
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        h = Integer.parseInt(tmp[0]);
        w = Integer.parseInt(tmp[1]);

        arr = new int[h + 1][w + 1];
        for (int i = 0; i < h; i++) {
            String[] tmp2 = br.readLine().split("");
            for (int j = 0; j < tmp2.length; j++) {
                if (tmp2[j].equals("."))
                    continue;
                if (tmp2[j].equals("/")) {
                    arr[i + 1][j] = 1;
                    arr[i][j + 1] = 1;
                }
                if (tmp2[j].equals("\\")) {
                    arr[i][j] = 1;
                    arr[i + 1][j + 1] = 1;
                }
            }
        }
        print(arr);
        check();
    }

    static void check() {
        boolean[][] visited = new boolean[h + 1][w + 1];
        int cnt = 0;

        for (int i = 0; i < h + 1; i++) {
            if (arr[i][0] == 0) {
                bfs(visited, i, 0);
            }
            if (arr[i][w] == 0) {
                bfs(visited, i, w);
            }
        }

        for (int i = 0; i < w + 1; i++) {
            if (arr[0][i] == 0) {
                bfs(visited, 0, i);
            }
            if (arr[h][i] == 0) {
                bfs(visited, h, i);
            }

        }

        for (int i = 0; i < h + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (visited[i][j])
                    continue;
                if (arr[i][j] == 1)
                    continue;
                cnt++;
            }
        }
        System.out.println(cnt * 2);
//        print(visited);
    }

    static void bfs(boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[]{x, y});

        while (true) {
            if (q.isEmpty())
                break;
            int[] p = q.pop();
            int newX = p[0];
            int newY = p[1];

            for (int z = 0; z < 4; z++) {
                int nx = newX + dx[z];
                int ny = newY + dy[z];

                if (0 > nx || nx >= h + 1)
                    continue;
                if (0 > ny || ny >= w + 1)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (arr[nx][ny] == 1)
                    continue;
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
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

    static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
4 5
/\/\.
\...\
.\../
..\/.

4 5
/\/\.
\...\
.\/\/
.....


 */