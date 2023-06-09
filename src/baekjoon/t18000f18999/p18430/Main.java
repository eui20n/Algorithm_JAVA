/**
 * 문제 이름 : 무기공학
 * URL : https://www.acmicpc.net/problem/18430
 * 문제 설명 :
 * 만들 수 있는 부메랑들의 강도 합의 최댓값을 구해라 => 부메랑의 개수는 상관없음, 1개가 강도의 최대값이면 그 값을 출력하면 됨
 * 만약 부메랑을 만들 수 없다면 0을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t18000f18999.p18430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}

// 입력되는 값의 크기가 굉장히 작기 때문에, 그냥 백트래킹을 하면 될듯