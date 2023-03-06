/**
 * 문제 이름 : 어항정리
 * URL : https://www.acmicpc.net/problem/23291
 * 문제 설명 :
 * 1차원 배열의 어항의 정보(안에 들어있는 물고기의 정보)가 들어 있을 때, 아래의 연산 순서대로 어항을 정리할 것이다.
 * 연산 순서
 * 1. 물고기의 수가 가장 적은 어항에 물고기를 1마리 넣는다. (그러한 어항이 여러개라면 모든 어항에 다 넣는다.)
 * 2. 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항의 위에 올려 놓는다.
 * 3. 2개 이상 쌓여있는 어항을 모두 공중부양 시킨 다음, 전체를 시계방향으로 90도 회전시킨다.
 * 3-1. 이 과정을 더이상 할 수 없을 때까지 반복한다.
 * 3-2. 할 수 없을 때는 공중부양해서 올린 어항의 아래 아무 어항도 없을 때다(2층에 어항이 있는데, 1층에는 없는 경우)
 * 4. 모든 인접한 두 어항에 대해서 물고기 수의 차이를 구한다. 이 차이를 5로 나눈 몫을 d라고 할때, d가 0보다 크면, 두 어항 중 물고기의 수가
 * 많은 곳에 있는 물고기 d마리를 적은 곳으로 보낸다. 이 과정은 모든 인접한 칸에 대해서 동시에 발생한다.
 * 5. 가장 왼쪽에 있는 어항부터, 그리고 아래에 있는 어항부터 가장 위에 있는 어항까지 순서대로 바닥에 놓는다.
 * 6. 다시 공중부양을 한다. 가운데를 중심으로 왼쪽 N / 2개를 공중부양 시켜 전체를 시계 방향으로 180도 회전 시킨 다음, 오른쪽 N / 2개의 위에 놓는다.
 * 이 작업을 총 2번한다.
 * 7. 다시 4번의 작업을 한다. (물고기 수 조절)
 * 8. 다시 5번의 작업을 한다. (다시 일렬로 놓기)
 * 물고기가 가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수 차이가 K이하가 되려면 어항을 몇 번 정리해야하는지 구해라...;
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 배열 돌리기, 배열 돌리기, 배열 돌리기
 * 소요 시간 : 5분내로
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t23000f23999.p23291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, K;
    static int[][] fishBowl;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> rotateLoc = new ArrayList<>();
    static int[][] spreadFishArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);
        fishBowl = new int[N][N];
        spreadFishArr = new int[N][N];

        fishBowl[N - 1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(cleanFishBowl());
    }

    static int cleanFishBowl() {
        int time = 0;

        while (true) {
            time++;
            // 물고기의 수가 가장 작은 것을 어항에 물고기의 수를 1마리 더해준다.
            addFish(fishBowl[N - 1]);
            // 가장 왼쪽에 있는 어항을 그 옆에 칸에 올린다.
            leftLayUp();
            // 어항이 2개 이상 쌓여 있는 것을 공중부양 해서 올려준다.
            levitation();
            // 인접한 어항의 물고기 수를 구해서 물고기의 수를 조절해주기
            spreadFish();
            // 일렬로 피기
            makeLine();
            // 공중부양2
            levitation2();
            // 물고기 수 조절2
            spreadFish();
            // 일려로 펴기2
            makeLine();
            // 가장 많은 물고기를 가지고 있는 어항과 가장 적은 물고기를 가지고 있는 어항의 차이
            if (K >= end(fishBowl[N - 1])) {
                return time;
            }
        }
    }

    /**
     * 가장 물고기가 많은 어항과 가장 물고기가 적은 어항의 차이를 구해주는 메소드
     * 해당 차이가 문제의 조건에 맞으면 종료, 아니면 계속해서 어항정리를 함
     * int[] arr : 물고기가 있는 어항(fishBowl의 마지막 행)
     * return int : 물고기의 차이
     */
    static int end(int[] arr) {
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;

        for (int num : arr) {
            minNum = Math.min(num, minNum);
            maxNum = Math.max(num, maxNum);
        }

        return maxNum - minNum;
    }

    /**
     * 2번째 공중부양 메소드로, 공주부양이 2번 일어남
     */
    static void levitation2() {
        for (int i = 1; i <= 2; i++) {
            int[][] newArr = checkRotate2(i);
            newArr = rotate180(newArr);
            paste2(newArr, i);
        }
    }

    /**
     * 배열이 들어오면 붙여주는 메소드
     * int[][] arr : 붙일 배열
     * int cnt : 현재 몇번 시행한 것인지 알려주는 매개변수
     */
    static void paste2(int[][] arr, int cnt) {
        int r = arr.length;
        int c = arr[0].length;

        if (cnt == 1) {
            for (int y = N / 2; y < N; y++) {
                fishBowl[N - 2][y] = arr[0][y - N / 2];
            }
        }
        if (cnt == 2) {
            for (int x = N - 2 - r; x < N - 2; x++) {
                for (int y = (N / 2) + (N / 2) / 2; y < N; y++) {
                    fishBowl[x][y] = arr[x - N + 2 + r][y - (N / 2) - ((N / 2) / 2)];
                }
            }
        }
    }

    /**
     * 입력으로 들어오는 배열을 180도 돌려주는 메소드
     * int[][] arr : 돌릴 배열
     * return int[][] : 180도 돌린 배열
     */
    static int[][] rotate180(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;

        int[][] newArr = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newArr[i][j] = arr[r - i - 1][c - j - 1];
            }
        }
        return newArr;
    }

    /**
     * 공중부양할 배열을 알려주는 메소드
     * int cnt : 현재 몇 번째 공주부양인지 알려줌
     * return int[][] : 공중부양할 배열
     */
    static int[][] checkRotate2(int cnt) {
        int[][] newArr = new int[cnt][N / (cnt * 2)];
        if (cnt == 1) {
            for (int y = 0; y < N / 2; y++) {
                newArr[0][y] = fishBowl[N - 1][y];
                fishBowl[N - 1][y] = 0;
            }
        }
        if (cnt == 2) {
            for (int x = N - 2; x < N; x++) {
                for (int y = N / 2; y < N / 2 + (N / 2) / 2; y++) {
                    newArr[x - N + 2][y - N / 2] = fishBowl[x][y];
                    fishBowl[x][y] = 0;
                }
            }
        }
        return newArr;
    }

    /**
     * 일렬로 피는 메소드
     */
    static void makeLine() {
        // 스왑
        int idx = 0;

        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (fishBowl[i][j] == 0)
                    break;
                int num = fishBowl[N - 1][idx];
                fishBowl[N - 1][idx] = fishBowl[i][j];
                fishBowl[i][j] = num;
                idx++;

            }
        }
    }

    /**
     * 물고기 수를 조절해주는 메소드
     */
    static void spreadFish() {
        // 조절
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (fishBowl[x][y] == 0)
                    continue;
                for (int z = 0; z < 4; z++) {
                    int nx = x + dx[z];
                    int ny = y + dy[z];

                    if (0 > nx || nx >= N)
                        continue;
                    if (0 > ny || ny >= N)
                        continue;
                    if (fishBowl[nx][ny] == 0)
                        continue;
                    if (fishBowl[x][y] < fishBowl[nx][ny])
                        // 무조건 본인 위치가 더 크다는 것을 보장 받기 위해서
                        continue;
                    // 내 위치가 더 클 경우 이동하는 것
                    // (내 위치 - 이동할 위치) / 5의 나눈 몫만큼 이동
                    int fishCnt = (fishBowl[x][y] - fishBowl[nx][ny]) / 5;
                    spreadFishArr[x][y] -= fishCnt;
                    spreadFishArr[nx][ny] += fishCnt;
                }
            }
        }
        // 합치기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fishBowl[i][j] += spreadFishArr[i][j];
                spreadFishArr[i][j] = 0;
            }
        }

    }

    /**
     * 어항이 2개 이상 쌓여 있는 것을 공중부양 해서 올려주는 메소드
     */
    static void levitation() {
        // 2개 이상인 것 고르기
        // 정사각형이 아님
        while (true) {
            rotateLoc.clear();
            int[][] newArr = checkRotate();
            // 90도 회전
            int[][] rotateArr = rotate90(newArr);
            // 붙이기
            if (!paste(rotateArr)) { // 이거 리턴 값이 false면 while문 종료하기
                break;
            }
        }
    }

    /**
     * 붙여주는 메소드로 붙이기 전에 붙일 수 있는지 확인함
     * int[][] arr : 기존에 배열에 붙일 배열
     * return : 붙일 수 있으면 붙이고 true 반환, 못 붙이면 안 붙이고 false 반환
     */
    static boolean paste(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;

        int startC = -1;
        int startR = N - 2;
        for (int i = N - 1; i >= 0; i--) {
            if (fishBowl[N - 2][i] == 0) {
                continue;
            }
            startC = i + 1;
            break;
        }

        // 붙일 수 있는지 확인
        if (startC + c > N) {
            return false;
        }

        // 돌린 곳 삭제하기
        for (int[] loc : rotateLoc) {
            int x = loc[0];
            int y = loc[1];
            fishBowl[x][y] = 0;
        }

        // 이어 붙이기
        for (int i = startR - r + 1; i < startR + 1; i++) {
            for (int j = startC; j < startC + c; j++) {
                fishBowl[i][j] = arr[i - (startR - r + 1)][j - (startC)];
            }
        }
        return true;
    }

    /**
     * 파라미터로 받은 배열을 90도 돌려주는 메소드
     * return : 90도 돌린 배열
     */
    static int[][] rotate90(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;

        int[][] newArr = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newArr[j][i] = arr[r - i - 1][j];
            }
        }
        return newArr;
    }

    /**
     * 회전시킬 배열을 알려주는 메소드
     * return : 회전시킬 배열
     */
    static int[][] checkRotate() {
        int[][] newArr;
        int startR = -1, startC = -1;
        int endR = -1, endC = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (startR == -1 && fishBowl[i][j] != 0) {
                    startR = i;
                    startC = j;
                    continue;
                }
                if (startR != -1 && (fishBowl[i][j] == 0 || j == N - 1)) {
                    endR = N - 1;
                    endC = j - 1;
                    break;
                }
            }
            if (startR != -1)
                break;
        }

        newArr = new int[endR - startR + 1][endC - startC + 1];
        for (int i = 0; i < endR - startR + 1; i++) {
            for (int j = 0; j < endC - startC + 1; j++) {
                rotateLoc.add(new int[]{startR + i, startC + j});
                newArr[i][j] = fishBowl[startR + i][startC + j];
            }
        }
        return newArr;
    }

    /**
     * 가장 왼쪽에 있는 어항을 그 옆에 칸에 올려주는 메소드
     */
    static void leftLayUp() {
        // 스왑
        fishBowl[N - 1][0] = fishBowl[N - 1][0] + fishBowl[N - 2][1];
        fishBowl[N - 2][1] = fishBowl[N - 1][0] - fishBowl[N - 2][1];
        fishBowl[N - 1][0] = fishBowl[N - 1][0] - fishBowl[N - 2][1];
    }

    /**
     * 물고기의 수가 가장 작은 것을 어항에 물고기의 수를 1마리 더해주는 메소드
     * int[] arr : 가장 아래 행의 어항
     */
    static void addFish(int[] arr) {
        // 무조건 맨 처음(일렬로 있을 경우)에 하는 것
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            minNum = Math.min(minNum, arr[i]);
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] == minNum) {
                arr[i] += 1;
            }
        }
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

// 배열이 위로만 늘어남 => 절대로 옆으로는 늘어나지 않음, 오히려 줄어듬
// 위로 늘어나게 하기
// 어항의 물고기의 수는 1마리 이상과 무조건 종료가 된다는 것을 보장 받음
