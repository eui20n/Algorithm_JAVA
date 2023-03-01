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

        System.out.println(runway());
    }

    static int runway() {
        int result = 0;

        for (int i = 0; i < N; i++) {
            result += check(arr[i]);

            int[] newArr = new int[N];
            for (int j = 0; j < N; j++) {
                newArr[j] = arr[j][i]; // 해당 배열이 정사각형이라서 가능
            }
            result += check(newArr);
        }
        return result;
    }

    static int check(int[] arr) {
        int[] runwayArr = new int[N];

        for (int i = 0; i < N - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) >= 2)
                return 0;
            if (arr[i] - arr[i + 1] != 1)
                continue;

            // 경사로를 설치할 수 있는지 확인하기
            for (int j = i + 1; j < i + 1 + L; j++) {
                if (j >= N)
                    // 경사로의 길이가 배열을 넘은 경우
                    return 0;
                if (arr[i + 1] != arr[j])
                    // 연속된 길이가 다른 경우
                    return 0;
                runwayArr[j] = 1;
            }
        }

        for (int i = N - 1; i > 0; i--) {
            // 더이상 차이가 2 이상인 것은 없어
            if (arr[i] - arr[i - 1] != 1){
                continue;
            }
            // 경사로를 설치할 수 있는지 확인하기
            for (int j = i - 1; j > i - 1 - L; j--) {
                if (j < 0)
                    // 경사로의 길이가 배열을 넘은 경우
                    return 0;
                if (runwayArr[j] == 1)
                    // 경사로가 있는 곳에는 경사로를 설치 못함
                    return 0;
                if (arr[i - 1] != arr[j])
                    // 연속된 길이가 다른 경우
                    return 0;
                runwayArr[j] = 1;
            }
        }
        return 1;
    }
}

// 해당 문제에서 "동시에" 라는 말이 없음 => 길 하나만 보고 지나갈 수 있는지 판단하면 됨
// 당연히 "동시에"라는 말이 없기때문에 지나갈 수 있는 길의 최대/최소를 묻는 것이 아님 => 백트래킹이 아님
// 양 옆에서 갈 것 => for문 한번에 갈 것, 만약에 어려우면 왼쪽 한번 가고, 오른쪽 한번 가기
// => 가능하면 정답 + 1해주기
// 전과의 높이가 1이 차이나면 L만큼 앞을 보기