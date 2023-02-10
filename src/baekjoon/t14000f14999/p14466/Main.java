/**
 * 문제 이름 : 소가 길을 건너간 이유 6
 * URL : https://www.acmicpc.net/problem/14466
 * 문제 설명 : 길을 건너지 않으면 만날 수 없는 소의 쌍이 몇개인지 구하기
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, K, R;
    static int[][] info;
    static int[][] cows;
    static int[][] farm;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmpInput = br.readLine().split(" ");
        N = Integer.parseInt(tmpInput[0]);
        K = Integer.parseInt(tmpInput[1]);
        R = Integer.parseInt(tmpInput[2]);

        info = new int[R][4];
        cows = new int[K][2];

        for (int i = 0; i < R; i++){
            String[] tmpInput2 = br.readLine().split(" ");
            int x1 = Integer.parseInt(tmpInput2[0]);
            int y1 = Integer.parseInt(tmpInput2[1]);
            int x2 = Integer.parseInt(tmpInput2[2]);
            int y2 = Integer.parseInt(tmpInput2[3]);

            info[i][0] = x1 - 1;
            info[i][1] = y1 - 1;
            info[i][2] = x2 - 1;
            info[i][3] = y2 - 1;

        }

        for (int i = 0; i < K; i++){
            String[] tmpInput3 = br.readLine().split(" ");
            int x = Integer.parseInt(tmpInput3[0]);
            int y = Integer.parseInt(tmpInput3[1]);

            cows[i][0] = x - 1;
            cows[i][1] = y - 1;
        }

    }

    static void check(){

    }

}

// 생각 1. 소가 길이 있는 곳은 못감 => 길이 없는 곳으로만 해서 갈 수 있는지 확인
// 소의 위치를 각각 라벨링을 해서 만나고 못만나고를 체크하는 것이 편할 듯
// 모든 소에 대해서 만날 수 없는 수를 다 구한다음에 2로 나누면 됨 => 고려해볼 것은 2로 나누는 것보다 만날때 마다 체크를 해주는게 더 빠를지 생각
// 생각 2. 길이 없는 곳으로 못가는데, 각각의 길은 서로 독립임 => 길의 여부를 비트 마스킹을 하던, 3차워 배열을 만들어서 하든 해야함 X
// 길이 아닌 곳에서 길로 가는 것이 되는지 확인해야함 => 문제를 읽어보면 문제는 없어보임
// 길인 곳에서 길인 곳으로 가는 것이 안되는 것

// 배열을 그리지 말고 한번에 하기
// 길을 갈 수 있는지만 검증 해주면 됨

// 정리
// (길, 깉) => 갈 수 없음
// (길, 길X) => 갈 수 있음
// (길X, 길) => 갈 수 있음