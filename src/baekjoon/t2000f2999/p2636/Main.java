/**
 * 문제 이름 : 치즈
 * URL : https://www.acmicpc.net/problem/2636
 * 문제 설명 :
 * 공기중에 닿은 치즈는 한 시간 후에 녹는다. 이 때, 치즈가 다 녹는데 걸리는 시간과 녹기 한시간 전에 얼마나 남아있었는지 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();

    }

    static void check() {
        int time = 0;
        int size = 0;
        while (true) {
            // 가장자리에는 무조건 치즈가 없기 때문에 bfs를 (0, 0)에서 시작하면 됨
            List<int[]> melting = bfs();
            if (melting.size() == 0)
                break;
            melt(melting);
            size = melting.size();
            time += 1;
        }
        System.out.println(time);
        System.out.println(size);
    }

    static void melt(List<int[]> list) {
        for (int[] meltArr : list) {
            int x = meltArr[0];
            int y = meltArr[1];

            arr[x][y] = 0;
        }
    }

    static List<int[]> bfs() {
        List<int[]> melting = new ArrayList<>();

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                if (arr[nx][ny] == 1) {
                    melting.add(new int[]{nx, ny});

                } else {
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return melting;
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

// 그냥 계속해서 탐색하기