/**
 * 문제 이름 : 나무 재테크
 * URL : https://www.acmicpc.net/problem/16235
 * 문제 설명 : 나무는 사계절을 보내며 아래와 같은 과정을 반복한다.
 * 봄 : 나무가 자신의 나이만큼 양분을 먹고, 나이가 1증가한다. 하나의 칸에 여러 나무가 있다면 나이가 어린 나무부터 양분을 먹는다.
 * 만약 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽는다.
 * 여름 : 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점은 버린다.
 * 가을 : 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
 * 겨울 : 로봇이 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분은 입력으로 주어진다.
 * K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구해라
 * 처음에 모든 칸에 양분이 5만큼 들어있다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] addFood;
    static int[][] arr;
    static Deque<int[]> treeInfoes = new LinkedList<>();
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(tmp[2]);

        // 현재 양분 배열
        arr = makeArr(N);

        // 양분이 얼마나 추가될지 알려주는 배열
        addFood = new int[N][N];

        for (int i = 0; i < N; i++) {
            addFood[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // x좌표, y좌표, 나무의 나이
        int[][] tmp2 = new int[M][3];
        for (int i = 0; i < M; i++) {
            int[] tmp3 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tmp2[i][0] = tmp3[0] - 1;
            tmp2[i][1] = tmp3[1] - 1;
            tmp2[i][2] = tmp3[2];
        }

        Arrays.sort(tmp2, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        treeInfoes.addAll(Arrays.asList(tmp2));

        season();
    }

    static void season() {
        int time = 0;

        while (true) {
            if (time == K)
                break;
            if (treeInfoes.size() == 0)
                break;
            List[] tree = spring();
            List<int[]> deathTree = tree[0];
            summer(deathTree);
            List<int[]> birthTree = tree[1];
            autumn(birthTree);
            winter();
            time++;
        }
        System.out.println(treeInfoes == null ? 0 : treeInfoes.size());
    }

    static List[] spring() {
        // 자신의 나이만큼 양분을 먹고 나이가 1증가
        // 양분을 못먹으면 나무는 즉시 죽음 => 죽은 나무는 여름에 쓰일 예정으로 반환값으로 줄 것

        List[] tree = new ArrayList[2];
        List<int[]> deathTree = new ArrayList<>();
        List<int[]> birthTree = new ArrayList<>();

        tree[0] = deathTree;
        tree[1] = birthTree;

        int cnt = treeInfoes.size();
        int end = 0;

        while (true) {
            // 나무의 수만큼 반복을 했으면 종료
            if (end == cnt)
                break;
            // 나무가 더 없으면 그만해야함
            if (treeInfoes.isEmpty())
                break;

            int[] treeInfo = treeInfoes.poll();
            int x = treeInfo[0];
            int y = treeInfo[1];
            int age = treeInfo[2];
            end++;

            if (arr[x][y] < age) {
                // 만약에 해당 나무에 양분을 못주면 죽을 나무에 추가하고 덱에 안넣음
                deathTree.add(new int[]{x, y, age});
                continue;
            }

            arr[x][y] -= age++;
            // 덱의 맨 뒤에 넣기 => 이렇게 순서대로 넣으면 처음에 넣은 것이 결국 다시 처음으로 오게됨
            treeInfoes.add(new int[]{x, y, age});

            if (age % 5 == 0) {
                // 만약 나이가 5의 배수인 나무일 때, 번식할 나무에 추가
                birthTree.add(new int[]{x, y, age});
            }
        }
        return tree;
    }

    static void summer(List<int[]> deathTree) {
        // 죽은 나무가 있던 위치에 나이를 2로 나눈 값만큼 양분 추가
        // 소수점 버림
        for (int[] tree : deathTree) {
            int x = tree[0];
            int y = tree[1];
            int age = tree[2];

            arr[x][y] += (int) age / 2;
        }
    }

    static void autumn(List<int[]> birthTree) {
        // 나이가 5의 배수인 나무들이 8방향으로 나이가 1인 나무 생성
        // 앞에 추가하기
        for (int[] tree : birthTree) {
            int x = tree[0];
            int y = tree[1];

            for (int z = 0; z < 8; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];

                if (0 > nx || nx >= N)
                    continue;
                if (0 > ny || ny >= N)
                    continue;
                // 나무를 생성하고 나이가 1이기 때문에 앞에다가 넣어줌
                treeInfoes.addFirst(new int[]{nx, ny, 1});

            }
        }
    }

    static void winter() {
        // 로봇이 나무에 양분을 추가해줌
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] += addFood[i][j];
            }
        }
    }

    static int[][] makeArr(int n) {
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = 5;
            }
        }
        return arr;
    }
}

// 처음에 모든 칸에 5만큼 양분이 들어있다.
// 덱으로 관리해서 새로추가되는 나무는 나이가 1이니까 앞에다 추가
// 나머지는 순서대로 계속 빼면 됨
// 즉, 덱에는 나무의 나이의 순서대로 들어가 있음
