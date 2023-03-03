/**
 * 문제 이름 : 아기상어
 * URL : https://www.acmicpc.net/problem/16236
 * 문제 설명 :
 * N x N 크기의 공간에 물고기 M 마리와 아기 상어 1마리가 있다. 칸에는 물고기가 최대 한마리 존재할 수 있다. 아기상어와 물고기는 모두 크기를 갖고 있다.
 * 처음 아기상어의 크기는 2이고, 아기상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다. 아기상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고
 * 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
 * 아기상어가 어디로 갈지 결정하는 방법
 * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
 * 2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 3. 먹을 수 있는 물고기가 1마리 보다 많다면 거리가 가장 가까운 물고기를 먹으러 간다.
 *  - 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 *  - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 아기 상어가 더 이상 먹을 물고기가 없다면 종료한다. 이 때, 아기상어는 최소 몇초동안 물고기를 먹을 수 있는지 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Shark {
        int x, y, size, fishCnt;
        Shark(int x, int y, int size, int fishCnt) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.fishCnt = fishCnt;
        }
    }

    static class Pointer {
        int x, y, depth;
        public Pointer(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static int N;
    static int[][] arr;
    static Shark shark; // 상어의 정보를 담을 리스트
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited; // 반복시행을 몇번하는지 기록을 해서 방문처리를 해줄 것 => 20 x 20 이라서 최대 400번 정도 반복시행 할 것이라고 생각

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < N; j++) {
                // 상어 위치를 입력받으면 해당 위치를 0으로 해주고, 상어 위치를 저장
                if (arr[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                    arr[i][j] = 0;
                }
            }
        }

        visited = new int[N][N];
        System.out.println(babyShark());
    }

    /**
     * 정답을 구해주는 메소드로 아기상어가 최소 몇 초 동안 물고기를 먹을 수 있는지 알려주는 메소드
     * return : 물고기를 먹을 수 있는 최소 시간
     * */
    static int babyShark() {
        int result = 0;
        int cnt = 1; // 반복횟수
        while (true) {
            int time = selectFish(cnt++);
            if (time == 0)
                // 0이라는 소리는 먹을 물고기가 없다는 소리 => 더 이상 못먹어서 while문 종료
                break;
            result += time;
        }
        return result;
    }

    /**
     * 먹을 물고기를 선택해서 해당 물고기를 먹는데 걸렸던 시간을 알려주는 메소드
     * BFS로 구현
     * int cnt : 현재 몇번 반복을 했는지 알려주는 파라미터로, 방문처리할 때 활용됨
     * return int : 물고기를 먹는데 걸리는 최소시간
     * */
    static int selectFish(int cnt) {
        visited[shark.x][shark.y] = cnt;

        Deque<Pointer> q = new ArrayDeque<>();
        q.add(new Pointer(shark.x, shark.y, 0));
        // 우선순위 큐를 활용하여 먹을 물고기를 뽑아줌
        PriorityQueue<int[]> fish = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        // 몇 초가 걸리는지 알려주는 변수로 처음에 아주 큰 값으로 설정
        // 처음으로 먹을 수 있는 물고기를 만나면 해당 물고기를 만날때 까지 걸렸던 시간으로 바꿔줌
        int time = Integer.MAX_VALUE;

        while (true) {
            if (q.isEmpty()) {
                break;
            }
            Pointer p = q.poll();

            if (time < p.depth)
                // time보다 p.depth가 더 크다는 것은 최소 시간이 아니기 때문에 while문을 종료시켜줌
                break;

            for (int z = 0; z < 4; z++) {
                int nx = p.x + dx[z];
                int ny = p.y + dy[z];

                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= N)
                    continue;
                if (visited[nx][ny] >= cnt) // 큰 경우는 없을 것 같음
                    continue;
                if (shark.size < arr[nx][ny])
                    // 자기보다 큰 물고기가 있는 곳으로 못감;; 강약약강
                    continue;
                if (arr[nx][ny] != 0 && shark.size > arr[nx][ny]) {
                    // 먹을 수 있음
                    fish.add(new int[] {nx, ny});
                    time = p.depth;
                }
                q.add(new Pointer(nx, ny, p.depth + 1));
                visited[nx][ny] = cnt;
            }
        }
        return eatFish(fish, time); // 상어의 정보를 수정한 후, 최소시간을 반환해줌
    }

    /**
     * 물고기를 먹고, 상어의 정보를 반환 후, 시간을 반환해 주는 메소드
     * 먹을 물고기가 없다면 0을 반환
     * PriorityQueue<int[]> fish : 먹힐 수 있는 물고기의 정보
     * int time : 해당 물고기를 먹는데 걸리는 최소시간
     * return : 해당 물고기를 먹는데 걸리는 최소시간
     * */
    static int eatFish(PriorityQueue<int[]> fish, int time) {
        if (fish.size() == 0)
            return 0;

        int[] eat = fish.poll();
        arr[eat[0]][eat[1]] = 0;
        shark.x = eat[0];
        shark.y = eat[1];
        shark.fishCnt += 1;

        if (shark.fishCnt == shark.size) {
            // 만약에 먹은 물고기의 수가 크기와 같다면 먹은 물고기의 수를 0으로 해주고, 크기를 1 늘려줌
            shark.fishCnt = 0;
            shark.size += 1;
        }
        return time + 1;
    }
}

/*
        정리
    1. 먹을 수 있는 물고기가 많은 경우 => 정렬을 이용해서 구해주기
    2. 문제에서 주어진 대로 구현을 하면 됨
 */