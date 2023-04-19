/**
 * 문제 이름 : 달력
 * URL : https://www.acmicpc.net/problem/20207
 * 문제 설명 :
 * 달력에 코딩지를 할껀데, 일정을 다 덮어야한다. 이때, 필요한 코팅지의 최소값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int N;
    static int[][] schedule;
    static int[][] calendar = new int[1002][1002]; // 제출할 때, 1002로 바꾸기
    static boolean[][] visited = new boolean[1002][1002];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        schedule = new int[N][2];

        for (int i = 0; i < N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = tmp[0];
            int end = tmp[1];

            drawCalendar(start, end);
        }

        check();
        System.out.println(result);
//        print(calendar);
    }

    static void check() {
        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar.length; j++) {
                if (visited[i][j] || calendar[i][j] == 0)
                    continue;

                int[] size = bfs(i, j);
                result += (Math.abs(size[0] - size[1]) + 1) * (Math.abs(size[2] - size[3]) + 1);
//                System.out.println(Arrays.toString(size));
//                print(visited);
            }
        }
    }

    static int[] bfs(int startX, int startY) {
        // 제일 처음 방문처리 안하고 한번 더 제일 처음인 곳도 가게 할 것
        int[] loc = new int[4];
        loc[0] = 10000;
        loc[2] = 10000;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {startX, startY});

        while (true) {
            if (deque.isEmpty())
                break;

            int[] tmp = deque.pop();
            int x = tmp[0];
            int y = tmp[1];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= calendar.length)
                    // 절대 올 수 없는 조건
                    continue;
                if (0 > ny || ny >= calendar.length)
                    // 절대 올 수 없는 조건
                    continue;
                if (visited[nx][ny])
                    continue;
                if (calendar[nx][ny] == 0) {
                    // 왼쪽은 더 작은 값 2 y
                    // 오른쪽은 더 큰 값 3 y
                    // 위쪽은 더 작은 값 0 x
                    // 이래쪽은 더 큰 값 1 x
                    if (z == 0) {
                        // 작은 값, x
                        loc[z] = Math.min(loc[z], x);
                    } else if (z == 1) {
                        // 큰 값, x
                        loc[z] = Math.max(loc[z], x);
                    } else if (z == 2) {
                        // 작은 값, y
                        loc[z] = Math.min(loc[z], y);
                    } else if (z == 3) {
                        // 큰 값, y
                        loc[z] = Math.max(loc[z], y);
                    }

                }
                if (calendar[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    deque.add(new int[] {nx, ny});
                }

            }

        }
        return loc;
    }

    static void drawCalendar(int start, int end) {
        for (int i = start; i <= end; i++) {
            int idx = 1;
            while (true) {
                if (calendar[idx][i] == 0) {
                    calendar[idx][i] = 1;
                    break;
                }
                idx += 1;
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
                int printValue = arr[i][j] ? 1 : 0;
                System.out.print(printValue + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
// 0 1 bfs?