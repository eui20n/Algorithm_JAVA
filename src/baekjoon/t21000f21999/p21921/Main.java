/**
 * 문제 이름 : 블로그
 * URL : https://www.acmicpc.net/problem/21921
 * 문제 설명 :
 * 블로그에 연속적인 K일 동안 가장 많이 방문한 사람의 수와, 그러한 날이 몇개인지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        int maxValue = Integer.MIN_VALUE;
        int cnt = 0;

        int visitedCnt = 0;
        for (int i = 0; i < K; i++) {
            visitedCnt += arr[i];
        }

        maxValue = visitedCnt;
        cnt = 1;

        for (int i = 0; i < N - K; i++) {
            visitedCnt -= arr[i];
            visitedCnt += arr[i + K];

            if (maxValue < visitedCnt) {
                maxValue = visitedCnt;
                cnt = 1;
            } else if (maxValue == visitedCnt) {
                cnt += 1;
            }
        }
        if (maxValue != 0) {
            System.out.println(maxValue);
            System.out.println(cnt);
        } else {
            System.out.println("SAD");
        }

    }
}
