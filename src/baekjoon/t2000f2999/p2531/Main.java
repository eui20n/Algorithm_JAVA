/**
 * 문제 이름 : 회전 초밥
 * URL : https://www.acmicpc.net/problem/2531
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] sushiCnt = new int[3001];
    static int N, D, K, C; // 회전 초밥의 수, 초밥의 종류, 연속해서 먹는 초밥 수, 쿠폰 번호
    static int[] cycleSushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        D = tmpInput[1];
        K = tmpInput[2];
        C = tmpInput[3];

        cycleSushi = new int[N * 2];
        for (int i = 0; i < N; i++) {
            int inputNum = Integer.parseInt(br.readLine());
            cycleSushi[i] = inputNum;
            cycleSushi[i + N] = inputNum;
        }

        check();
    }

    static void check() {
        int sushi = 0;
        int result = 0;

        for (int i = 0; i < N + K - 1; i++) {
            int sushiNum = cycleSushi[i];
            if (i < K) {
                // 처음 상태
                if (sushiCnt[sushiNum] == 0)
                    sushi += 1;
                sushiCnt[sushiNum] += 1;
            } else {
                // 회전 초밥
                if (sushiCnt[sushiNum] == 0) {
                    // 종류가 하나 늘어야 함
                    sushi += 1;
                }

                sushiCnt[cycleSushi[i - K]] -= 1;
                sushiCnt[sushiNum] += 1;

                if (sushiCnt[cycleSushi[i - K]] == 0) {
                    // 종류가 하나 줄어야 함
                    sushi -= 1;
                }
            }
            result = Math.max(result, sushiCnt[C] == 0 ? sushi + 1 : sushi);
        }
        System.out.println(result);
    }
}


/*
    슬라이딩 윈도우
    덱을 쓰냐... 그냥 배열을 쓰냐
    개인적으로 배열을 쓰는 것을 더 좋아함
*/