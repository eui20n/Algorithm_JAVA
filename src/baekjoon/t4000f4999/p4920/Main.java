/**
 * 문제 이름 : 테트리스 게임
 * URL : https://www.acmicpc.net/problem/4920
 * 문제 설명 :
 * 보드판이 주어지고, 블록의 모양이 주어진다.
 * 보드판에는 점수가 있다. 블록으로 얻을 수 있는 점수의 최대값을 구해라.
 * 블록은 90도 회전할 수 있다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t4000f4999.p4920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            check();
        }
    }

    static void check() {

    }
}
