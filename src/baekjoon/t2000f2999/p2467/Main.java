/**
 * 문제 이름 : 용액
 * URL : https://www.acmicpc.net/problem/2467
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] liquid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liquid = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        int[] result = {0, 0, Integer.MAX_VALUE};
        for (int i = 0; i < N; i++) {
            int[] returnArr = binarySearch(liquid[i], i + 1, N);

            if (result[2] > returnArr[2]) {
                result = returnArr;
            }
        }
        System.out.println(result[0] + " " + result[1]);
    }

    static int[] binarySearch(int num, int start, int end) {
        int absMinNum = Integer.MAX_VALUE;
        int leftNum = 0;
        int rightNum = 0;

        while (true) {
            if (start >= end)
                break;

            int mid = (start + end) / 2;
            int midIdxNum = liquid[mid];

            int mixedNum = midIdxNum + num;
            if (absMinNum > Math.abs(mixedNum)) {
                // 최솟값 갱신
                absMinNum = Math.abs(mixedNum);
                leftNum = num;
                rightNum = midIdxNum;
            }

            // 원하는 것은 mixedNum가 0에 가까운 것임
            // 음수면 양수 방향으로 가야함(커지는 방향)
            // 양수면 음수 방향으로 가야함(작아지는 방향)
            if (mixedNum >= 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return new int[] {leftNum, rightNum, absMinNum};
    }
}

/*
        [이분 탐색]
        1. 기준을 잡는다.
        2. 기준 + 1 위치부터 끝 까지를 범위로 잡고 이분 탐색을 돌린다.
        3. 이분 탐색의 목표는 기준 위치에 있는 숫자와 더 했을 때, 가장 0에 가까운 수를 찾는 것 => 0에 가까운 수는 절대값으로 판별
        4. 위 과정을 liquid가 0 부터 N - 1까지 해줌
        5. 해당 과정을 통해서 나온 값을 저장하면서, 최솟값을 계속 갱신해줌
        6. 최솟값 출력
 */