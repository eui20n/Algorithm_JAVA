/**
 * 문제 이름 : 달이 차오른다, 가자
 * URL : https://www.acmicpc.net/problem/1194
 * 문제 설명 :
 * 열쇠를 먹고 문을 열어서 출구 까지 가는데 최소 시간을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pointer {
        int x, y, key, time;

        public Pointer(int x, int y, int key, int time) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.time = time;
        }

    }

    static int R, C;
    static String[][] arr;
    static int[] start = new int[2];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Map<Character, Integer> keys = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = new String[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().split("");

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j].equals("0")) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        makeKey();
        System.out.println(moonGo());
    }

    static int moonGo() {
        boolean[][][] visited = initVisited();
        visited[0][start[0]][start[1]] = true;

        Deque<Pointer> q = new ArrayDeque<>();
        q.add(new Pointer(start[0], start[1], 0, 0));

        while (true) {
            if (q.isEmpty())
                break;
            Pointer p = q.poll();

            for (int z = 0; z < 4; z++) {
                int nx = p.x + dx[z];
                int ny = p.y + dy[z];

                if (0 > nx || nx >= R)
                    // 범위를 벗어나는 경우
                    continue;
                if (0 > ny || ny >= C)
                    // 범위를 벗어나는 경우
                    continue;
                if (arr[nx][ny].equals("#"))
                    // 벽!
                    continue;
                if (visited[p.key][nx][ny])
                    // 방문한 경우
                    continue;
                if (arr[nx][ny].equals("1"))
                    // 달이 차오른다, 가자
                    return p.time + 1;

                char value = arr[nx][ny].charAt(0);

                if (value >= 97 && value <= 122) {
                    // 소문자
                    if ((p.key & (1 << keys.get(value))) == 0) {
                        // 키가 없는 경우 => 키를 주음
                        visited[p.key | 1 << keys.get(value)][nx][ny] = true;
                        q.add(new Pointer(nx, ny, p.key | 1 << keys.get(value), p.time + 1));
                        continue;
                    }
                }

                if (value >= 65 && value <= 90) {
                    // 대문자
                    // 키가 있는지 확인해주기
                    char newValue = (char)(value + 32);

                    if ((p.key & (1 << keys.get(newValue))) == 0) {
                        // 키가 없는 경우
                        continue; // 갈 수 없음
                    }
                    // 키가 있는 경우 => 문을 열고 가면 됨
                }
                visited[p.key][nx][ny] = true;
                q.add(new Pointer(nx, ny, p.key, p.time + 1));
            }
        }
        return -1;
    }

    static boolean[][][] initVisited() {
        boolean[][][] visited = new boolean[(1 << 7) - 1][R][C];
        return visited;
    }

    static void makeKey() {
        keys.put('a', 1);
        keys.put('b', 2);
        keys.put('c', 3);
        keys.put('d', 4);
        keys.put('e', 5);
        keys.put('f', 6);
    }

    static void print(boolean[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
