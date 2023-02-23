/**
 * 문제 이름 : 캐슬 디펜스
 * URL : https://www.acmicpc.net/problem/17135
 * 문제 설명 : 게임이 진행되는 곳은 N x M인 격자판으로 나타낼 수 있다. 격자판은 1 x 1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는
 * 최대 하나이다. 격자판의 N번행의 바로 아래(N + 1번 행)의 모든 칸에 성이 있다.
 * 성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다.
 * 모든 궁수는 동시에 거리가 D이하인 적 중 가장 가까운 적에게 공격한다. 그러한 적이 여럿일 경우 가장 왼쪽에 있는 적을 공격한다.
 * 공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면 적은 아래로 이동한다. 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
 * 모든 적이 격자판에서 제외되면 게임은 끝난다.
 * 격자판이 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.
 * 거리는 맨하튼 거리이다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static List<Integer> archer = new ArrayList<>();
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        D = Integer.parseInt(tmp[2]);

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check(arr, 0, 0);
        System.out.println(result);
    }

    /**
     * 정답을 구해주는 메소드
     * */
    static void check(int[][] arr, int cnt, int idx) {
        if (cnt == 3) {
            // 배열이 바뀌기 때문에 딥 카피를 이용해서 참조값이 다른 배열을 생성
            int[][] newArr = deepCopy(arr);
            shoot(newArr, 0, false);
            return;
        }
        // 조합을 이용해서 궁수 3명을 추출
        for (int i = idx; i < M; i++) {
            archer.add(i);
            check(arr, cnt + 1, i + 1);
            archer.remove(archer.size() - 1);
        }
    }

    /**
     * 딥 카피를 해주는 메소드
     * */
    static int[][] deepCopy(int[][] arr) {
        int[][] newArr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, newArr[i].length);
        }
        return newArr;
    }
    /**
     * 궁수가 활을 쏘는 메소드
     * */
    static void shoot(int[][] arr, int cnt, boolean check) {
        int[][] newArr = makeArr(arr, check);
        int r = newArr.length; // 배열이 바뀜에 따라서 행의 값이 바뀜
        List<int[]> enemies = new ArrayList<>(); // 적들의 위치를 담을 리스트로, 중복되는 값이 있기 때문에 담고 나서 한번에 처리

        for (int archerNum : archer) {
            // 적의 위치를 구해주는 중
            enemies.add(bfs(r, archerNum, newArr));
        }
        // 적의 위치에 쏴서 그 곳을 0으로 바꿈
        for (int[] enemy : enemies) {
            if (enemy == null)
                continue;

            int x = enemy[0];
            int y = enemy[1];
            // 이미 적이 없는 곳이면 바로 다음꺼
            if (newArr[x][y] == 0)
                continue;

            newArr[x][y] = 0;
            cnt += 1;
        }
        // 계속해서 최대값을 구해줌
        if (newArr.length == 1) {
            result = Math.max(cnt, result);
            return;
        }
        shoot(newArr, cnt, true);
    }

    /**
     * 적의 위치를 구해서 누굴 쏴야할지 알려주는 함수
     * */
    static int[] bfs(int r, int c, int[][] arr) {
        Queue<int[]> q = new ArrayDeque<>();
        // 현재 위치, 이전 위치, 현재 거리로 사정거리와 같아지면 끝
        // 따로 방문처리를 하지 않기 위해서 이전에 온 방향을 저장해줌 => 사이클이 없기때문에 이전에 온 방향만 저장을 하면 방문처리를 안해줘도 됨
        q.add(new int[]{r, c, -1, -1, 0});
        List<int[]> enemyLoc = new ArrayList<>();

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int preX = tmp[2];
            int preY = tmp[3];
            int range = tmp[4];

            // 쏠 수 있는 사거리를 벗어나거나
            if (range >= D)
                break;

            for (int z = 0; z < 3; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= arr.length)
                    continue;
                if (0 > ny || ny >= arr[0].length)
                    continue;
                if (nx == preX && ny == preY)
                    continue;
                if (arr[nx][ny] == 1) {
                    // 쏠 적 저장
                    // 왼쪽을 먼저 탐색을 해서 가장 먼저 만나는 적이 가장 왼쪽에 있는 적
                    return new int[] {nx, ny};
                }
                q.add(new int[]{nx, ny, x, y, range + 1});
            }
        }
        return null;
    }
    /**
     * 바뀌는 배열을 만들어 주는 함수
     * */
    static int[][] makeArr(int[][] arr, boolean check) {
        int num = check ? -1 : 0;

        int[][] newArr = new int[arr.length + num][M];

        for (int i = 0; i < arr.length + num; i++) {
            // 배열 만들기
            newArr[i] = arr[i];
        }

        return newArr;
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 정리
// 배열을 새로 만드는 연산을 큐를 이용해서 만들 수 있음
// 가장 앞에 있는 것을 제외하고
// 가장 뒤에 해당 배열의 열 크기만큼의 [0] 배열을 넣어주면 됨
// 그럼 복잡하게 연산을 안해도 됨
// 난 이렇게 못함ㅠ

// 방문처리는 안해도됨
// 정렬도 안하고 가장 먼저 왼쪽을 탐색하게 하면 됨