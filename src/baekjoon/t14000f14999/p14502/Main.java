/**
 * 문제 이름 : 연구소
 * URL : https://www.acmicpc.net/problem/14502
 * 문제 설명 :
 * 바이러스로 부터 안전한 지역의 최대 값을 구해라. 이 때, 벽을 꼭 3개를 세워야 한다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pointer {
        int x, y;

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int R, C;
    static int[][] arr;
    static List<int[]> virusLoc = new ArrayList<>(); // 초기에 바이러스 위치를 담는 리스트
    static int result = Integer.MIN_VALUE; // 결과를 저장할 변수
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int land = 0; // 땅인 것의 개수
    static int[][] visited; // 방문처리 리스트
    static int visitedCnt = 1; // 방문처리를 체크해줄 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = new int[R][C];
        visited = new int[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2)
                    virusLoc.add(new int[] {i, j});
                if (arr[i][j] == 0)
                    land += 1;
            }
        }
        check(0, 0);
        System.out.println(result);
    }

    static void check(int idx, int cnt) {
        if (cnt == 3) {
            result = Math.max(result, bfs());
            visitedCnt += 1;
            return;
        }

        for (int i = idx; i < R * C; i++) {
            int[] loc = change(i);
            int x = loc[0];
            int y = loc[1];

            if (arr[x][y] == 2)
                continue;
            if (arr[x][y] == 1)
                continue;

            arr[x][y] = 1;
            check(i + 1, cnt + 1);
            arr[x][y] = 0;
        }
    }

    static int bfs() {
        Deque<Pointer> q = new ArrayDeque<>();

        for (int[] virus : virusLoc) {
            int x = virus[0];
            int y = virus[1];
            q.add(new Pointer(x, y));
            visited[x][y] = visitedCnt;
        }

        int cnt = 0;

        while (true) {
            if (q.isEmpty())
                break;

            Pointer pointer = q.poll();

            for (int z = 0; z < 4; z++) {
                int nx = pointer.x + dx[z];
                int ny = pointer.y + dy[z];

                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (visited[nx][ny] == visitedCnt)
                    continue;
                if (arr[nx][ny] == 1)
                    continue;
                q.add(new Pointer(nx, ny));
                visited[nx][ny] = visitedCnt;
                cnt += 1;
            }
        }

        return land - cnt - 3;
    }

    static int[] change(int num) {
        int[] loc = new int[2];
        loc[0] = num / C;
        loc[1] = num % C;

        return loc;
    }
}

// 설치할 곳을 정하는 것은 배열을 일렬로 핀 값으로 생각하기
// arr[i][j] -> i x C + j <-> (K / C) => i , (K % C) => j
// land - 얼마큼 감염됬는지 - 3

/*
                정리
        1. 조합을 이용해서 벽 3개 세우기
        1-1. 2차원 배열로 보는 것이 아니라 1차원 배열로 봄
        1-2. 딱히 이유는 없음 => 이게 구현이 더 쉬울 것 같았음

        2. bfs를 통해서 안전한 구역이 몇개인지 확인
        2-1. 방문처리 리스트를 만들 때, bfs안에서 만든 것이 아니라 밖에서 만들어줌
        2-2. 해당 방문처리 리스트는 인트값이 들어가서 현재 수행하고 있는 횟수로 방문처리를 하는 것
        2-3. 이렇게 하면 계속해서 배열을 만들어도 되서 시간과 공간을 모두 절약할 수 있음
        2-4. 8 X 8 이라서 최대 64C3임 => 약 40000

        3. 위의 과정을 모든 벽을 3개 세울 수 있는 경우에서 수행하기

        4. 벽을 다 세운 후, 안전한 구역이 얼마나 있는지 세주기
        4-1. 안전한 지역은 벽을 세우고 바이러스가 다 퍼진 후 다시 배열을 탐색해서 구하지 않음
        4-2. bfs를 수행할 때, 바이러스가 퍼지는 구역을 카운트한 다음 다 퍼지면 전체 구역에서 해당 구역의 값을 빼줌
        4-3. 2차원 배열을 한 번 탐색하는 시간을 절약할 수 있음

 */
