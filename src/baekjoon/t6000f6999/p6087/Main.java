/**
 * 문제 이름 : 레이저 통신
 * URL : https://www.acmicpc.net/problem/6087
 * 문제 설명 :
 * 레이저를 통신하기 위해서 필요한 거울의 최소개수를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t6000f6999.p6087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static class Pointer {
        int x, y, mirrorCnt, dist, dir;
        public Pointer(int x, int y, int mirrorCnt, int dist, int dir) {
            this.x = x;
            this.y = y;
            this.mirrorCnt = mirrorCnt;
            this.dist = dist;
            this.dir = dir;
        }
    }

    static int R, C;
    static String[][] arr;
    static int[][] laserInfo = new int[2][2]; // 레이저 위치
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        R = Integer.parseInt(tmp[1]);
        C = Integer.parseInt(tmp[0]);
        arr = new String[R][C];
        int idx = 0;

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().split("");

            if (idx == 2)
                continue;
            for (int j = 0; j < C; j++) {
                if (arr[i][j].equals("C")) {
                    laserInfo[idx][0] = i;
                    laserInfo[idx][1] = j;
                    idx++;
                }
            }
        }

        System.out.println(laserCommunication());
    }

    static int laserCommunication() {
        int[] start = laserInfo[0];
        int[] end = laserInfo[1];

        int[][] visited = makeVisited();
        Deque<Pointer> q = new ArrayDeque<>();

        visited[start[0]][start[1]] = 0;
        q.add(new Pointer(start[0], start[1], 0, 0, -1));

        while (true) {
            if (q.isEmpty())
                break;
            Pointer p = q.poll();

            for (int z = 0; z < 4; z++) {
                int nx = p.x + dx[z];
                int ny = p.y + dy[z];
                int newMirrorCnt = p.mirrorCnt;

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (arr[nx][ny].equals("*"))
                    continue;
                if (visited[nx][ny] <= p.mirrorCnt)
                    continue;
                if (p.dir != -1 && p.dir != z) {
                    newMirrorCnt += 1;
                }
                visited[nx][ny] = newMirrorCnt;
                q.add(new Pointer(nx, ny, newMirrorCnt, p.dist + 1, z));
            }
        }
        print(visited);
        return visited[end[0]][end[1]];
    }

    static int[][] makeVisited() {
        int[][] newArr = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newArr[i][j] = Integer.MAX_VALUE;
            }
        }
        return newArr;
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == Integer.MAX_VALUE)
                    System.out.print("*" + " ");
                else
                    System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 각 점에 도달을 할 때, 거울을 설치가 비용을 카운트 해서 가장 작은 것만 가면 됨
// 거울을 설치한다는 것은 현재 방향에서 축이 바뀌었다는 소리(x축이였다면 y축으로, y축이였다면 x축으로)
/*

4 5
C..*
...*
...*
*.**
...C
=> 2

 */