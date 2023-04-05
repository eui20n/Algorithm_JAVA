/**
 * 문제 이름 : 탈출
 * URL : https://www.acmicpc.net/problem/3055
 * 문제 설명 :
 * 비버의 굴에는 매 분 물이 인접한 칸으로 차오른다. 고슴도치도 매 분 인접한 칸으로 이동한다. 이 때, 고슴도치가 비버의 굴을 안전하게 탈출하기
 * 위한 최소 시간을 구해라.
 * 만약에 탈출을 할 수 없다면 KAKTUS 를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int R, C;
    static char[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        List<int[]> water = new ArrayList<>();
        int[] exit = new int[2];
        int[] hedgehog = new int[2];
        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'D') {
                    exit[0] = i;
                    exit[1] = j;
                } else if (arr[i][j] == 'S') {
                    hedgehog[0] = i;
                    hedgehog[1] = j;
                } else if (arr[i][j] == '*') {
                    water.add(new int[]{i, j});
                }
            }
        }
        check(water, exit, hedgehog);
    }

    static void check(List<int[]> water, int[] exit, int[] hedgehog) {
        int result = escape(water, exit, hedgehog);
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    static int escape(List<int[]> water, int[] exit, int[] hedgehog) {
        boolean[][] visited = new boolean[R][C];

        Queue<int[]> q = new ArrayDeque<>();
        for (int[] waterLoc : water) {
            q.add(new int[]{waterLoc[0], waterLoc[1], 0, 0}); // 물의 좌표와 시간, 0(물)
            visited[waterLoc[0]][waterLoc[1]] = true;
        }

        q.add(new int[]{hedgehog[0], hedgehog[1], 0, 1}); // 고슴도치의 좌표와 시간, 1(고슴도치)
        visited[hedgehog[0]][hedgehog[1]] = true;

        while (true) {
            if (q.isEmpty())
                break;
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int time = tmp[2];
            int check = tmp[3];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (arr[nx][ny] == 'X')
                    continue;
                if (check == 0 && nx == exit[0] && ny == exit[1])
                    continue;
                if (check == 1 && nx == exit[0] && ny == exit[1])
                    return time + 1;
                q.add(new int[]{nx, ny, time + 1, check});
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}
