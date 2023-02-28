/**
 * 문제 이름 : 경사로
 * URL : https://www.acmicpc.net/problem/14890
 * 문제 설명 : 지도에서 해당 길을 지나갈 수 있는지 없는지를 구할 것이다. 근데 지나갈 수 없다면 경사로를 놓아서 지나가게 만들 수 있다.
 *           경사로의 개수는 매우 많아서 부족할 일은 없다. 경사로는 놓을 수 있는 조건은 아래와 같다.
 *           1. 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
 *           2. 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
 *           3. 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
 *           경사로를 놓을 수 없는 경우
 *           1. 경사로를 놓은 곳에 또 경사로를 놓는 경우
 *           2. 낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
 *           3. 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
 *           4. 경사로를 놓다가 범위를 벗어나는 경우
 *           위와 같을 때, 지나갈 수 있는 길의 개수를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, L;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        L = Integer.parseInt(tmp[1]);

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        rumWay();
    }

    /**
     * 몇개의 길을 지나갈 수 있는지 구해주는 메소드
     * */
    static void rumWay() {
        int result = 0;

        for (int[] road : arr) {
            check(road);
        }

        for (int j = 0; j < N; j++) {
            int[] newArr = new int[N];
            for (int i = 0; i < N; i++) {
                newArr[i] = arr[i][j];
            }

            check(newArr);
        }

    }

    /**
     * 해당 길을 지나갈 수 있는지 확인해주는 메소드
     * */
    static void check(int[] arr) {
    }
}

// 해당 문제에서 "동시에" 라는 말이 없음 => 길 하나만 보고 지나갈 수 있는지 판단하면 됨
// 당연히 "동시에"라는 말이 없기때문에 지나갈 수 있는 길의 최대/최소를 묻는 것이 아님 => 백트래킹이 아님