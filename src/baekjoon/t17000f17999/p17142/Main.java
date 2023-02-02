/**
 * 문제 이름 :
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        int[][] arr = new int[N][N];
        List<int[]> virus = new ArrayList<>();

        int cnt = 1;
        for (int i = 0; i < N; i++) {
            String[] tmp2 = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (tmp2[j].equals("2")) {
                    int[] virusLoc = {i, j};
                    virus.add(virusLoc);
                    arr[i][j] = cnt++;
                } else if (tmp2[j].equals("0")) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = -1;
                }
            }
        }
        checkVirus(arr, virus, 0, 0, new boolean[M]);
    }

    static void checkVirus(int[][] arr, List<int[]> virus, int cnt, int start, boolean[] visited) {
        if (cnt == M) {
            bfs(arr, virus, visited);
            return;
        }
        for (int i = start; i < M; i++) {
            visited[i] = true;
            checkVirus(arr, virus, cnt + 1, i + 1, visited);
            visited[i] = false;
        }
    }

    static void bfs(int[][] arr, List<int[]> virus, boolean[] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] bfsVisited = new boolean[N][N];
        int result = 0;

        Queue<int[][]> q = new LinkedList<>();
        int[][] tmp = new int[M][];
        int idx = 0;
        for (int i = 0; i < visited.length; i++) {
            int x = virus.get(i)[0];
            int y = virus.get(i)[1];
            bfsVisited[x][y] = true;

            if (visited[i]) {
                int[] add = {x, y};
                tmp[idx++] = add;
            }
        }
        q.add(tmp);

        while (true) {
            int[][] p = q.poll();

            int[][] temp;
            for (int[] data : p){
                int x = data[0];
                int y = data[1];

                for (int z = 0; z < 4; z++) {
                    int nx = x + dx[z];
                    int ny = y + dy[z];
                    if (0 > nx || nx >= N) continue;
                    if (0 > ny || ny >= N) continue;
                    if (bfsVisited[nx][ny]) continue;
                    if (arr[nx][ny] == -1) continue;
                    result += 1;
                    bfsVisited[nx][ny] = true;

                }

            }


        }

    }


}

// 각각의 경우에 대해서 바이러스를 퍼트렸을때와 아닐때로 구분해서 계산