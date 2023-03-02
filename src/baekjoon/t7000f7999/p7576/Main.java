/**
 * 문제 이름 : 토마토
 * URL : https://www.acmicpc.net/problem/7576
 * 문제 설명 :
 * N x M에 토마토를 관리하려고 한다. 아직 익지 않은 토마토가 있을 수 있는데, 보관 후 하루가 지나면 익은 토마토들의 인접한 곳에 있는
 * 익지 않은 토마토들은 익는다. 모든 토마도가 익는 최소 시간을 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t7000f7999.p7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] arr;
    static List<int[]> tomatoLoc = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[1]);
        C = Integer.parseInt(tmp[0]);

        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr[i].length; j++) {
                // 입력을 받으면서 현재 토마토의 위치를 가져옴
                if (arr[i][j] == 1)
                    tomatoLoc.add(new int[] {i, j});
            }
        }
        System.out.println(tomato());
    }

    /**
     * 인접한 토마토를 익게 해주고, 시간을 반환해주는 메소드
     * return int : 토마토가 익는 최소시간으로, 만약에 모든 토마토가 익을 수 없다면 -1을 반환
     * */
    static int tomato() {
        boolean[][] visited = new boolean[R][C];
        Deque<int[]> q = new ArrayDeque<>();

        for (int[] start : tomatoLoc) {
            // 입력으로 받은 토마토의 위치를 이용해서 초기에 방문 처리 및 q에 넣어줌
            int x = start[0];
            int y = start[1];

            q.add(new int[] {x, y, 1}); // x, y, time
            visited[x][y] = true;
        }

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int time = tmp[2];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (arr[nx][ny] == -1)
                    continue;
                // 인접한 토마토에 시간을 넣어서 한번에 처리
                arr[nx][ny] = time + 1;
                // 사실 방문처리 리스트토 필요없음 => 시간으로 처리가 가능함
                visited[nx][ny] = true;
                // 인접한 칸을 넣어줌
                q.add(new int[] {nx, ny, time + 1});
            }
        }
        return result();
    }

    /**
     * 토마토가 모두 익었는지 알려주는 메소드
     * return int : 토마토가 익는데 걸리는 최소시간을 알려줌, 만약에 모든 토마토가 다 익지 못한다면 -1을 리턴
     * */
    static int result() {
        int time = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 0)
                    // 인접한 토마토를 모두 다 익게 했는데, 아직도 안익은 토마토가 있다면 -1을 리턴
                    return -1;
                if (arr[i][j] == -1)
                    // 토마토가 없는 칸은 볼 필요 없음
                    continue;
                // 최대값을 보는 이유는 마지막에 익은 토마토는 그 중 가장 큰 값이기 때문
                time = Math.max(time, arr[i][j]);
            }
        }
        // 시간에 1을 더했기 때문에 -1을 해서 리턴해줌
        return time - 1;
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
