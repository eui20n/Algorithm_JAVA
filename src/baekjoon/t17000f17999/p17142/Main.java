/**
 * 문제 이름 : 연구소 3
 * URL : https://www.acmicpc.net/problem/17142
 * 문제 설명 : 연구소에 바이러스가 있는데, 이것을 모두 퍼트리는데 걸리는 최소시간 구하기
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N;
    static int M;
    static int spread;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        int[][] arr = new int[N][N];
        List<int[]> virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] tmp2 = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (tmp2[j].equals("2")) {
                    int[] virusLoc = { i, j };
                    virus.add(virusLoc);
                    arr[i][j] = 1;
                } else if (tmp2[j].equals("0")) {
                    spread += 1;
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = -1;
                }
            }
        }

        checkVirus(arr, virus, 0, 0, new boolean[virus.size()]);
        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);

    }

    static void checkVirus(int[][] arr, List<int[]> virus, int cnt, int start, boolean[] visited) {
        if (cnt == M) {
            bfs(arr, virus, visited);
            return;
        }
        for (int i = start; i < virus.size(); i++) {
            visited[i] = true;
            checkVirus(arr, virus, cnt + 1, i + 1, visited);
            visited[i] = false;
        }
    }

    static void bfs(int[][] arr, List<int[]> virus, boolean[] visited) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        boolean[][] bfsVisited = new boolean[N][N];
        int result = 0;
        int checkSpread = 0;

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                int x = virus.get(i)[0];
                int y = virus.get(i)[1];
                bfsVisited[x][y] = true;
                int[] add = { x, y, 0 };
                q.add(add);
            }
        }

        while (true) {
            if (q.isEmpty())
                break;

            if (ans <= result)
                break;

            if (checkSpread == spread)
                break;

            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            int time = p[2];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];
                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= N)
                    continue;
                if (bfsVisited[nx][ny])
                    continue;
                if (arr[nx][ny] == -1)
                    continue;
                if (arr[nx][ny] != 1) {
                    checkSpread += 1;

                }
                result = Math.max(result, time + 1);
                q.add(new int[] { nx, ny, time + 1 });
                bfsVisited[nx][ny] = true;
            }
        }

        if (checkSpread == spread) {
            ans = Math.min(result, ans);
        }

    }

}