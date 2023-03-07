/**
 * 문제 이름 : 보급로
 * 문제 번호 : 1249
 * 문제 설명 :
 * 파괴된 도로에는 트럭이나 탱크가 지날 수 없다. 그래서 출발지에서 도착지까지 가기 위한 도로 복구를 빠른 시간 내에 하려고 한다.
 * 도로가 파여진 깊이에 비례해서 복구 시간은 증가한다. 출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 다익스트라
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static class Pointer {
        int x, y, dist;

        public Pointer(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int T;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            for (int j = 0; j < N; j++) {
                arr[j] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.printf("#%d %d %n", i, dijkstra(arr, N));
        }
    }

    static int dijkstra(int[][] arr, int N) {
        // 방문처리 배열 생성
        boolean[][] visited = new boolean[N][N];
        // 초기값 true로 바꿈
        visited[0][0] = true;

        // 시작점에서 부터의 거리를 저장할 배열 생성
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = INF; // 무한대로 선언해줌
            }
        }
        // 시작 -> 시작은 거리가 0임
        dist[0][0] = 0;

        // 우선순위 큐 생성 => 기준은 거리가 짧은 것음
        PriorityQueue<Pointer> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.dist - o2.dist;
        });

        // 우선순위 큐에 값 넣음
        pq.add(new Pointer(0, 0, 0));

        while (true) {
            // 큐가 빌때까지 진행
            if (pq.isEmpty())
                break;
            // 거리가 가장 짧은 것을 빼줌
            Pointer p = pq.poll();

            for (int z = 0; z < 4; z++) {
                // 그 위치에서 4방 탐색
                int nx = p.x + dx[z];
                int ny = p.y + dy[z];

                if (0 > nx || nx >= N)
                    // 범위를 벗어나면 안하기
                    continue;
                if (0 > ny || ny >= N)
                    // 범위를 벗어나면 안하기
                    continue;
                if (visited[nx][ny])
                    // 해당 위치가 방문처리가 되어 있다면 안하기
                    continue;
                if (dist[nx][ny] <= arr[nx][ny] + p.dist)
                    // 해당 위치의 거리가 더 짧으면 안하기
                    continue;
                // 거리가 더 길다는 것이기 때문에 새로운 거리를 넣어줌
                dist[nx][ny] = arr[nx][ny] + p.dist;
                // 해당 위치 방문처리
                visited[nx][ny] = true;
                // 새로운 거리를 넣은 값을 우선순위 큐에 넣어줌
                pq.add(new Pointer(nx, ny, dist[nx][ny]));
            }
        }
        // 도착점까지 걸린 최단거리를 출력
        return dist[N - 1][N - 1];
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
