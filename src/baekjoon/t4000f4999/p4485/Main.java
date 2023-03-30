/**
 * 문제 이름 : 녹색 옷 입은 애가 젤다지?
 * URL : https://www.acmicpc.net/problem/4485
 * 문제 설명 :
 * (0, 0)에서 (N - 1, N - 1)까지 이동하는데 드는 최소 비용을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t4000f4999.p4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[][] arr;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;
            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            int result = check();
            sb.append("Problem ").append(cnt++).append(": ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static int check() {
        // x, y, cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });

        int[][] dist = makeArr();
        dist[0][0] = arr[0][0];
        pq.add(new int[]{0, 0, arr[0][0]});

        while (true) {
            if (pq.isEmpty())
                break;

            int[] tmp = pq.poll();
            int x = tmp[0];
            int y = tmp[1];
            int cost = tmp[2];

            if (x == N - 1 && y == N - 1)
                break;

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= N)
                    continue;
                if (dist[nx][ny] <= cost + arr[nx][ny])
                    continue;
                pq.add(new int[]{nx, ny, cost + arr[nx][ny]});
                dist[nx][ny] = cost + arr[nx][ny];
            }
        }

        return dist[N - 1][N - 1];
    }

    static int[][] makeArr() {
        int[][] newArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newArr[i][j] = Integer.MAX_VALUE;
            }
        }
        return newArr;
    }
}

// 전형적인 다익스트라 문제
