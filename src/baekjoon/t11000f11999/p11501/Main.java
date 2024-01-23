/**
 * 문제 이름 : 주식
 * URL : https://www.acmicpc.net/problem/11501
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t11000f11999.p11501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            check(N, arr);
        }
    }

    static void check(int N, int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int standardNum = arr[N - 1]; // 역순으로 계산을 할 것
        long result = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] < standardNum) {
                // 최대 값이 더 크면 그냥 넣어주기
                stack.add(arr[i]);
            } else if (arr[i] > standardNum) {
                // 최대 값보다 더 큰 값이 들어왔다면 stack에 있는 것 빼고, 최대값 갱신해주기
                while (true) {
                    if (stack.isEmpty())
                        break;
                    result += standardNum - stack.pop();
                }
                standardNum = arr[i];
            }
        }
        // stack에 남은 것이 있다면 다 비워주기
        for (int num : stack) {
            result += standardNum - num;
        }
        System.out.println(result);
    }
}

/*
        greedy + stack go
        계산할 때, stack에 남은 것은 결과에서 다 더해주면 됨
 */