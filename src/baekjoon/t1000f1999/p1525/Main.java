/**
 * 문제 이름 : 퍼즐
 * URL : https://www.acmicpc.net/problem/1525
 * 문제 설명 :
 * 빈칸과 인접해 있는 숫자는 빈칸으로 이동이 가능하다. 이때, 숫자가 정렬된 상태로 되고 싶을 때, 최소 몇번 움직여야 하는지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

public class Main {
    static class Point {
        int x, y, time;
        String arr;

        public Point(int x, int y, int time, String arr) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.arr = arr;
        }
    }

    static int[][] arr = new int[3][3];
    static int[] startPoint;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0)
                    startPoint = new int[] {i, j};
            }
        }
        check();

    }

    static void check() {
        System.out.println(bfs());
    }

    static int bfs() {
        Deque<Point> q = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        visited.add(arrToString(arr));
        q.add(new Point(startPoint[0], startPoint[1], 0, arrToString(arr))); // x y time arr

        while (true) {
            if (q.isEmpty())
                break;

            Point tmp = q.poll();
            int x = tmp.x;
            int y = tmp.y;
            int time = tmp.time;
            String str = tmp.arr;

            arr = stringToArr(str);

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= 3)
                    continue;
                if (0 > ny || ny >= 3)
                    continue;
                if (arrToString(arr).equals("123456780"))
                    return time;
                // 방문 처리 코드
                // arr의 상태가 바뀜 => 이 부분도 다른 것으로 바뀔 예정
                arr[x][y] = arr[nx][ny];
                arr[nx][ny] = 0;
                String newStr = arrToString(arr);
                if (visited.contains(newStr)) {
                    arr[nx][ny] = arr[x][y];
                    arr[x][y] = 0;
                    continue;
                }
                q.add(new Point(nx, ny, time + 1, newStr));
                visited.add(newStr);
                arr[nx][ny] = arr[x][y];
                arr[x][y] = 0;
            }
        }
        return -1;
    }

     static String arrToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
     }

     static int[][] stringToArr(String str) {
        int[][] newArr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newArr[i][j] = str.charAt(j + 3 * i) - '0';
            }
        }
        return newArr;
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
}