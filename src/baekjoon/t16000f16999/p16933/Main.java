/**
 * 문제 이름 : 벽부수고 이동하기3
 * URL : https://www.acmicpc.net/problem/16933
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t16000f16999.p16933;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int N, M;
    static int breakWall;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmpInput1 = br.readLine().split(" ");
        N = Integer.parseInt(tmpInput1[0]);
        M = Integer.parseInt(tmpInput1[1]);
        breakWall = Integer.parseInt(tmpInput1[2]);

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            int[] tmpInput2 = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            arr[i] = tmpInput2;
        }
        int result = breakWallAndGo();
        System.out.println(result);
    }

    static int breakWallAndGo() {
        int[][] visited = new int[N][M];
        visited[0][0] |= 1 << visited[0][0];

        Queue<int[]> q = new ArrayDeque<>();

        // x, y, 걸린 시간, 부순 벽의 수, 밤낮(1,0)
        q.add(new int[] { 0, 0, 0, 0, 0 });

        while (!(q.isEmpty())) {
            int[] qInfo = q.poll();
            int x = qInfo[0];
            int y = qInfo[1];
            int time = qInfo[2];
            int breakCnt = qInfo[3];
            int day = qInfo[4];

            boolean wall = true;

            if (x == N - 1 && y == M - 1) {
                return time + 1;
            }

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= M)
                    continue;
                if ((visited[nx][ny] & (1 << breakCnt)) >= 1)
                    continue;
                if (arr[nx][ny] == 1 && breakCnt < breakWall && (visited[nx][ny] & (1 << (breakCnt + 1))) == 0) {
                    if (day == 0) {
                        // 벽을 만날 경우 (낮)
                        visited[nx][ny] |= (1 << (breakCnt + 1));
                        q.add(new int[] { nx, ny, time + 1, breakCnt + 1, day ^ 1 });
                        continue;
                    } else if (day == 1 && wall) {
                        // 벽을 만날 경우 (밤)
                        q.add(new int[] { x, y, time + 1, breakCnt, day ^ 1 });
                        wall = false;
                        continue;
                    }
                }
                if (arr[nx][ny] == 0) {
                    visited[nx][ny] |= (1 << breakCnt);
                    q.add(new int[] { nx, ny, time + 1, breakCnt, day ^ 1 });
                }
            }
        }
        return -1;
    }
}