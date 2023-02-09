/**
 * 문제 이름 : 소가 길을 건너간 이유 6
 * URL : https://www.acmicpc.net/problem/14466
 * 문제 설명 : 길을 건너지 않으면 만날 수 없는 소의 쌍이 몇개인지 구하기
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t14000f14999.p14466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K, R;
    static int[][] info;
    static int[][] cows;
    static int[][] road;
    static int[][] farm;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmpInput = br.readLine().split(" ");
        N = Integer.parseInt(tmpInput[0]);
        K = Integer.parseInt(tmpInput[1]);
        R = Integer.parseInt(tmpInput[2]);

        info = new int[R][4];
        cows = new int[K][2];
        road = new int[N][N];
        farm = new int[N][N];

        for (int i = 0; i < R; i++){
            info[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < info[i].length; j++){
                int x1, x2, y1, y2;
                x1 = info[i][0] - 1;
                y1 = info[i][1] - 1;
                x2 = info[i][2] - 1;
                y2 = info[i][3] - 1;

                road[x1][y1] = 1;
                road[x2][y2] = 1;
            }
        }
        showArr(road);
        int cnt = 1;
        for (int i = 0; i < K; i++){
            cows[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int x1, y1;

            x1 = cows[i][0] - 1;
            y1 = cows[i][1] - 1;
            farm[x1][y1] = cnt++;
        }
    }

    static void check(){
        // 소의 위치 별로 bfs 함수 돌리기
    }

    static void bfs(int x, int y){
        // 소의 위치별로 달라지니 visited도 계속 바껴야함
    }

    static void showArr(int[][] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 생각 1. 소가 길이 있는 곳은 못감 => 길이 없는 곳으로만 해서 갈 수 있는지 확인
// 소의 위치를 각각 라벨링을 해서 만나고 못만나고를 체크하는 것이 편할 듯
// 모든 소에 대해서 만날 수 없는 수를 다 구한다음에 2로 나누면 됨 => 고려해볼 것은 2로 나누는 것보다 만날때 마다 체크를 해주는게 더 빠를지 생각
// 생각 2. 길이 없는 곳으로 못가는데, 각각의 길은 서로 독립임 => 길의 여부를 비트 마스킹을 하던, 3차워 배열을 만들어서 하든 해야함 X
// 길이 아닌 곳에서 길로 가는 것이 되는지 확인해야함 => 문제를 읽어보면 문제는 없어보임
// 길인 곳에서 길인 곳으로 가는 것이 안되는 것