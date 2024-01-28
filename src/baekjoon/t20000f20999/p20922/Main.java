/**
 * 문제 이름 : 겹치는 건 싫어
 * URL : https://www.acmicpc.net/problem/20922
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K;
    static int[] arr;
    static int[] cntNum = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        K = tmpInput[1];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        int startIdx = 0;
        int length = 0;
        int result = 0;

        for (int i = 0; i < N; i++) {
            int num = arr[i];

            if (cntNum[num] >= K) {
                // 더 이상 갈 수 없음
                // 가장 처음 등장한 num까지 포인터를 이동시켜줘야함

                while (true) {
                    if (cntNum[num] < K)
                        break;

                    int startNum = arr[startIdx];
                    cntNum[startNum] -= 1;
                    length -= 1;
                    startIdx += 1;
                }
            }
            cntNum[num] += 1;
            length += 1;
            result = Math.max(length, result);
        }
        System.out.println(result);
    }
}

/*
    투 포인터
 */