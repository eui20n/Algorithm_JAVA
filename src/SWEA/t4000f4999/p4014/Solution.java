/**
 * 문제 이름 : 활주로 건설
 * 문제 번호 : 4014
 * 문제 설명 :
 * 활주로를 건설하려고 하는데, 높이의 차이가 1인 땅이 있다면 경사로를 설치하여 연결할 수 있다.
 * 이렇게 경사로를 설치해서 만들 수 있는 활주로의 개수를 출력해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t4000f4999.p4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    static int T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");

            int N = Integer.parseInt(tmp[0]);
            int L = Integer.parseInt(tmp[1]);

            int[][] arr = new int[N][N];
            for (int j = 0; j < N; j++) {
                arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            sb.append("#").append(i).append(" ").append(runway(arr, N, L)).append("\n");
        }
        System.out.println(sb);
    }

    static int runway(int[][] arr, int N, int L) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            result += check(arr[i], N, L);

            int[] newArr = new int[N];
            for (int j = 0; j < N; j++) {
                newArr[j] = arr[j][i]; // 해당 배열이 정사각형이라서 가능
            }
            result += check(newArr, N, L);
        }
        return result;
    }

    static int check(int[] arr, int N, int L) {
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
            if (arr[i] - arr[i - 1] != 1) {
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
