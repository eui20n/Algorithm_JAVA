/**
 * 문제 이름 : 마법사 상어와 복제
 * URL : https://www.acmicpc.net/problem/23290
 * 문제 설명 :
 * 마법사 상어가 4 x 4 크기의 격자에 마법을 연습하려고 한다. 해당 격자에는 물고기가 M마리가 있다.
 * 격자에는 마법사 상어도 들어갈 수 있다. 각 물고기는 칸 하나에 들어가 있으며, 8방향의 이동방향중 하나를 가지고 있다.
 * 마법사 상어가 맙버 연습을 하면 다음과 같은 작업이 순차적으로 이루어진다.
 * 1. 상어가 모든 물고기에게 복제 마법을 시전한다. 복제 마법은 시간이 조금 걸리기 때문에 아래 5번에서 물고기가 복제되어 칸에 나타난다.
 * 2. 모든 물고기가 한 칸 이동한다. 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
 * 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약에 이동할 수 있는 칸이 없으면
 * 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다.
 * 3. 상어가 연속해서 3칸 이동한다. 상어는 현재 칸에서 상하좌우로 인접한 칸으로 이동할 수 있다. 연속해서 이동하는 칸 중에 격자의 범위를
 * 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다. 연속해서 이동하는 중에 상어가 물고기가 있는 같은 칸으로 이동하게 된다면
 * 그 칸에 있는 모든 물고기는 격자에서 제외되며, 제외되는 모든 물고기는 물고기 냄새를 남긴다. 가능한 이동 방법 중에서 제외되는 물고기의
 * 수가 가장 많은 방법으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용한다.
 * 4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
 * 5. 1번에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖는다.
 * 마법을 S번 했을 때, 격자에 있는 물고기 수를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t23000f23999.p23290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, S;
    static List<List<List<Integer>>> fishInfo;
    static int[] shark = new int[2];
    static int[] fishDx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] fishDy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sharkDx = {-1, 0, 1, 0};
    static int[] sharkDy = {0, -1, 0, 1};
    static int[][] smellArr = new int[4][4]; // 냄새를 담을 배열로 숫자가 들어갈 예정 -> 냄새배열의 값을 1씩 빼주면 알아서 냄새가 없어지게 가능
    static int[][] sizeArr;
    static List<List<List<Integer>>> newList;
    static PriorityQueue<int[]> select = new PriorityQueue<>((o1, o2) -> {
        if (o1[3] == o2[3]) {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o2[2] - o1[2];
                }
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        }
        return o1[3] - o2[3];
    });
    // 우선순위 큐 생성해주기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        M = Integer.parseInt(tmp[0]);
        S = Integer.parseInt(tmp[1]);

        fishInfo = makeList();

        for (int i = 0; i < M; i++) {
            int[] tmp2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int fishX = tmp2[0] - 1;
            int fishY = tmp2[1] - 1;
            tmp2[2] -= 1;

            fishInfo.get(fishX).get(fishY).add(tmp2[2]); // 사실 물고기 방향만 있어도 상관없음
        }
        int[] tmp3 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        shark[0] = tmp3[0] - 1;
        shark[1] = tmp3[1] - 1;
        System.out.println(wizard());
    }

    static int wizard() {
        int time = 0;
        while (true) {
            if (time++ == S) {
                break;
            }
            // 1. 상어가 복제 마법을 시전한다. => 5번에서 복제됨
            // 2. 모든 물고기가 이동을 한다. 상어, 냄새, 벽 밖으로는 이동 못함 => 8방향
            newList = movingFish();
            // 3. 상어가 물고기를 가장 많이 잡을 수 있는 곳으로 3칸 이동 => 4방향, 상어를 잡으면 냄새가 생김
            sizeArr = makeCnt();
            movingCheck(shark[0], shark[1], 0, new int[4]);
            movingShark(select.poll());
            // 4. 냄새의 유효기간이 끝난 것들이 사라짐 => 2턴
            deleteSmell();
            // 5. 물고기가 복제됨
            fishInfo = cloneFish();
        }
        int amount = fishCnt();
        return amount;
    }

    static int fishCnt() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cnt += fishInfo.get(i).get(j).size();
            }
        }
        return cnt;
    }

    static List<List<List<Integer>>> cloneFish() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int fish : fishInfo.get(i).get(j)) {
                    newList.get(i).get(j).add(fish);
                }
            }
        }
        return newList;
    }

    static void deleteSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smellArr[i][j] > 0)
                    smellArr[i][j] -= 1;
            }
        }
    }

    static void movingShark(int[] dir) {
        for (int i = 0; i < 3; i++) {
            shark[0] = shark[0] + sharkDx[dir[i] - 1];
            shark[1] = shark[1] + sharkDy[dir[i] - 1];

            if (newList.get(shark[0]).get(shark[1]).size() >= 1) {
                newList.get(shark[0]).get(shark[1]).clear();
                smellArr[shark[0]][shark[1]] = 3;
            }
        }
    }

    static void movingCheck(int x, int y, int cnt, int[] dir) {
        // 3칸 백트랙킹
        // 최악의 경우 64번을 함
        // 정렬까지 한다면 좀 더 시간이 소요가 되는데, 해당 작업을 최대 100함
        // 10000번이면 그냥 할만함
        if (cnt == 3) {
            // 이 부분 우선순위 큐로 구현해보기
            // 처음 방향, 두번째 방향, 세번째 방향, 상어가 격리시킨 물고기의 수
            select.add(new int[] {dir[0], dir[1], dir[2], dir[3]});
            if (select.size() >= 2) {
                // 우선순위 큐를 조건의 반대로 생성해서, 가장 앞에 있는 것을 빼면 됨
                // 무조건 2개면 빼기때문에 가장 앞에 있는 것은 조건에 가장 최적화된 값이 남게됨
                select.poll();
            }
            return;
        }

        for (int z = 0; z < 4; z++) {
            int nx = x + sharkDx[z];
            int ny = y + sharkDy[z];

            if (0 > nx || nx >= 4)
                continue;
            if (0 > ny || ny >= 4)
                continue;
            int size = sizeArr[nx][ny];

            dir[cnt] = z + 1;
            dir[3] += size;
            sizeArr[nx][ny] = 0;
            movingCheck(nx, ny, cnt + 1, dir);
            dir[cnt] = 0;
            dir[3] -= size;
            sizeArr[nx][ny] = size;
        }
    }

    static List<List<List<Integer>>> movingFish() {
        List<List<List<Integer>>> newList = makeList();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                List<Integer> fishList = fishInfo.get(i).get(j);
                for (int fish : fishList) {
                    int x = i;
                    int y = j;
                    int z = fish;

                    for (int d = 0; d < 8; d++) {
                        int newZ = z - d < 0 ? z - d + 8 : z - d;
                        int nx = x + fishDx[newZ];
                        int ny = y + fishDy[newZ];

                        if (0 > nx || nx >= 4 || 0 > ny || ny >= 4) {
                            // 벗어나는 경우
                            continue;
                        }
                        if (nx == shark[0] && ny == shark[1]) {
                            // 상어가 있는 경우
                            continue;
                        }
                        if (smellArr[nx][ny] != 0) {
                            // 냄새가 있는 경우
                            continue;
                        }
                        x = nx;
                        y = ny;
                        z = newZ;
                        break;
                    }
                    newList.get(x).get(y).add(z);
                }
            }
        }
        return newList;
    }

    static List<List<List<Integer>>> makeList() {
        List<List<List<Integer>>> newList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            newList.add(new ArrayList<>());

            for (int j = 0; j < 4; j++) {
                newList.get(i).add(new ArrayList<>());
            }
        }
        return newList;
    }

    static int[][] makeCnt() {
        int[][] newArr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[i][j] += newList.get(i).get(j).size();
            }
        }
        return newArr;
    }

    static void print(int[][] arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 메모리가 차고 넘치기 때문에 막 사용할 예정 => 문제를 풀이하면 줄일 수 있는 부분 줄이기
// 코드가 너무너무 마음에 안든다.