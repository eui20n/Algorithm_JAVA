/**
 * 문제 이름 : 말이 되고픈 원숭이
 * URL : https://www.acmicpc.net/problem/1600
 * 문제 설명 :
 * 말은 체스의 나이트 처럼 움직일 수 있다. 또한 장애물을 뛰어 넘을 수 있다. 원숭이는 이러한 말의 움직임을 K번만 움직일 수 있다.
 * 이 때, 원숭이가 도착지점으로 가기 위해서 움직여야 하는 최소 횟수를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 : BFS + 다차원
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int horseCnt;
    static int R, C;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        horseCnt = Integer.parseInt(br.readLine());

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[1]);
        C = Integer.parseInt(tmp[0]);

        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        if (R == 1 && C == 1) {
            System.out.println(0);
        } else {
            int result = monkeyWantToBeHorse();
            System.out.println(result);
        }

    }

    static int monkeyWantToBeHorse() {
        int[][] visited = new int[R][C];

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0, 0}); // x, y, 이동 횟수, 말형태로 이동 횟수

        visited[0][0] = 1;

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int cnt = tmp[2];
            int horse = tmp[3];

            for (int z = 0; z < 12; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];
                int newHorse = z >= 4 ? horse + 1 : horse;

                if (horse >= horseCnt && z >= 4)
                    break;
                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if ((visited[nx][ny] & (1 << newHorse)) > 0)
                    continue;
                if (arr[nx][ny] == 1)
                    continue;
                if (nx == R - 1 && ny == C - 1)
                    return cnt + 1;

                q.add(new int[]{nx, ny, cnt + 1, newHorse});
                visited[nx][ny] |= (1 << newHorse);
            }

        }
        return -1;
    }

}