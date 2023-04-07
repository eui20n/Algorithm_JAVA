/**
 * 문제 이름 : 점심 식사시간
 * 문제 번호 : 2383
 * 문제 설명 :
 * 점심 시간에 밥을 먹기 위해서 최대한 빠른 시간안에 내려가야한다.
 * 계단 입구까지 이동시간은 맨하튼 거리를 이용한다.
 * 계단을 내려가는 시간은 계단의 길이만큼 걸린다.
 * 계단에는 최대 3명의 사람이 한번에 내려갈 수 있다. => 이미 3명이 내려가고 있다면 다음 사람들은 대기를 해야한다.
 * 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우를 찾아라
 * 계단은 반드시 2개이다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t2000f2999.p2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            // 계단 위치
            int[][] stairs = new int[2][2];
            // 사람 위치
            List<int[]> peopleLoc = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                for (int k = 0; k < arr[j].length; k++) {
                    if (arr[j][k] == 1) {
                        peopleLoc.add(new int[]{j, k});
                    }
                }
            }

            check(N, arr, stairs, peopleLoc);
        }
    }

    static void check(int N, int[][] arr, int[][] stairs, List<int[]> peopleLoc) {
        int peopleCnt = peopleLoc.size();

        boolean[][][] visited = new boolean[peopleCnt][N][N];
        boolean[] down = new boolean[peopleCnt]; // 내려간 사람은 true로
        int[][] stairPeopleCnt = new int[N][N]; // 현재 계단에 있는 사람의 수

        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < peopleCnt; i++) {
            int[] tmp = peopleLoc.get(i);
            q.add(new int[] {tmp[0], tmp[1], i, 0, 0}); // x, y, 사람의 번호, 걸린 시간, 계단 위에 있는 시간
            visited[i][tmp[0]][tmp[1]] = true;
        }

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int peopleNum = tmp[2];
            int time = tmp[3];
            int onStair = tmp[4];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= N)
                    continue;
                if (visited[peopleNum][nx][nx])
                    continue;
                if (nx == stairs[0][0] && ny == stairs[0][1]) {
                    // 현재 계단 위에 있음 => 현재 계단에 3명 있는지 확인해주기
                }
                q.add(new int[] {nx, ny, peopleNum, time + 1, onStair});

            }
        }
    }
}

// 그리디로 하면 되는지 생각좀 해보기
// 가장 가까운 계단으로 가는 것이 무조건 좋은 것은 아님
// 해당 계단에 사람이 내려 갈때랑 안내려 갈때를 모두 봐야하나?
// 어차피 bfs라서 먼저 내려갈 수 있다면 장땡임

// 아래 있는거 아님
// 각각의 사람이 1번 계단을 내려가는 경우, 2번 계단을 내려가는 경우를 다 구해서
// 그 중에서 가장 빠르게 내려갈 수 있는 경우를 보면 됨

// 계단에서 시작을 하면 될 듯

// 아니면 그냥 각 계단에서 얼마만에 도착할 수 잇는지 구한 후, 나중에 계산하기