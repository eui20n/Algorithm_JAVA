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


public class Main {
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
    }

    static void check() {
        bfs();
    }

    static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {startPoint[0], startPoint[1], 0}); // x y time

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

                if (0 > nx || nx >= 3)
                    continue;
                if (0 > ny || ny >= 3)
                    continue;
                // 방문 처리 코드
                // arr의 상태가 바뀜 => 이 부분도 다른 것으로 바뀔 예정
                arr[x][y] = arr[nx][ny];
                arr[nx][ny] = 0;
                q.add(new int[] {nx, ny, time + 1});

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
}

// 방문처리 가능?
// 메모리가 상당히 작음 => queue에 함부로 넣으면 안됨
// 근데 자바는 조금 많이 주네

// 이게 순서대로 만들어야하나?
// 그리고, 이미 만들어진 자리가 확정이 된 퍼즐의 위치는 절대로 바꾸면 안되는 건가?

// 그냥 모든 경우를 다 보기 => 최대 9!(360,000)임
// 저거는 정말 모든 경우이고, 실제로는 저 만큼은 나올 수 가 없음
// 큐에 새로운 데이터가 들어가야함 => 현재 상태 배열, 이게 있으면 메모리가 견디질 못함
// 모든 경우에 대해서 방문처리를 해야하는데, 그러기에는 메모리가 너무 적음 => 이거만 해결하면 됨