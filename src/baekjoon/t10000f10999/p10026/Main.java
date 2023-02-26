/**
 * 문제 이름 : 적록색약
 * URL : https://www.acmicpc.net/problem/10026
 * 문제 설명 : 적록색약인 사람과 아닌 사람이 보는 영역을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t10000f10999.p10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N;
    static String[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new String[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split("");
        }
        check();
    }

    /**
     * 답을 구해주는 메소드
     * */
    static void check() {
        // 0번째는 정상인
        // 1번째는 적록색약
        boolean[][][] visited = new boolean[2][N][N]; // 정상인과 적록색약을 구분하기 위해서 3차원 배열을 생성함
        int person = 0;
        int other = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[0][i][j]) {
                    // bfs
                    // 방문처리가 되어 있지 않았다면 아직 탐색을 안했음
                    bfs(visited[0], true, i, j, arr[i][j]);
                    // 탐색을 진행했으면, 구역을 정한 것으로 +1을 해줘야함
                    person += 1;
                }
                if (!visited[1][i][j]) {
                    // bfs
                    // 방문처리가 되어 있지 않았다면 아직 탐색을 안했음
                    // 현재 구역의 색을 체크할때, G면 R로 바꿔서 함수를 호출
                    bfs(visited[1], false, i, j, arr[i][j].equals("G") ? "R" : arr[i][j]);
                    // 탐색을 진행했으면, 구역을 정한 것으로 +1을 해줘야함
                    other += 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(person).append(" ").append(other);
        System.out.println(sb);
    }
    /**
     * BFS로 구역을 나눠주는 함수
     * visited : 방문처리 배열
     * check : 정상과 적록색약을 구분해주는 파라미터
     * x : 현재의 행 값
     * y : 현재의 열 값
     * color : 현재 색이 어떤 색인지 알려주는 파라미터
     * */
    static void bfs(boolean[][] visited, boolean check, int x, int y, String color) {
        // check : true => 정상
        // check : false => 적록색약
        visited[x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        // 위치의 값만 넣어줌
        q.add(new int[] {x, y});

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            x = tmp[0];
            y = tmp[1];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= N)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (check) {
                    // true라는 것은 정상을 의미
                    if (!color.equals(arr[nx][ny]))
                        // 다음에 갈 곳이랑 현재 색이랑 다르면 탐색을 안함
                        continue;
                } else {
                    // 적록색약이라는 의미
                    // 다음에 갈 색이 G면 R로 바꿔줌
                    String arrColor = arr[nx][ny].equals("G") ? "R" : arr[nx][ny];
                    if (!color.equals(arrColor))
                        // 바꾼 다음에 갈 곳의 색이랑 현재랑 다르면 탐색 안함
                        continue;
                }
                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}