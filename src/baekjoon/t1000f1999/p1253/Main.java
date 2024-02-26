/**
 * 문제 이름 : 좋다
 * URL : https://www.acmicpc.net/problem/1253
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        check();
    }

    static void check() {
        /*
            두 수를 더하고, 그 값이 배열에 있는지 확인하면 됨
            배열에 있는지 확인은 이분탐색을 통해서 할 것
         */
        boolean[] checkNum = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int num = arr[i] + arr[j];
                int binaryIdx = binarySearch(num); // 이 수가 i와 j가 아닌지 확인해야함

                if (binaryIdx != i && binaryIdx != j && arr[binaryIdx] == num && !checkNum[binaryIdx]) {
                    checkNum[binaryIdx] = true;
                }
            }
        }
        System.out.println(sameNumCheck(checkNum));
    }

    static int sameNumCheck(boolean[] checkNum) {
        int result = 0;
        int num = Integer.MAX_VALUE;
        for (int i = 0; i < checkNum.length; i++) {
            if (checkNum[i]) {
                // true면 최종값에 1씩 더해줌
                num = arr[i];
                result += 1;
            } else if (!checkNum[i] && arr[i] == num) {
                // false지만 바로 전에 해당 값일 경우 1씩 더해줌
                result += 1;
            }
        }
        return result;
    }

    static int binarySearch(int num) {
        // num이 arr에 있는지 확인하기
        int start = 0;
        int end = N - 1;

        while (true) {
            if (start >= end)
                return start;

            int mid = (start + end) / 2;
            int midNum = arr[mid];

            if (midNum < num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
    }
}

/*
        이분탐색
        1. 반복문을 통해서 두 개의 수를 더함
        2. 더한 수를 만들 수 있는지 이분탐색을 통해서 찾아봄
        3. 이분탐색을 통해서 찾은 위치가 해당 수와 같다면 checkNum 해당 자리를 true
        4. 반복을 전부 한 후, 본인 수가 true로 되어 있다면, 그와 같은 다른 수들도 true로 해주기
        5. checkNum의 true의 갯수 출력
 */
