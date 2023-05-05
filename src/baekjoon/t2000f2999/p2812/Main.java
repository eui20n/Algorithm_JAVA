/**
 * 문제 이름 : 크게 만들기
 * URL : https://www.acmicpc.net/problem/2812
 * 문제 설명 :
 * N자리 숫자가 주어졌을 때, 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int N, K;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);
        arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.empty() || K == 0) {
                stack.add(arr[i]);
                continue;
            }
            if (stack.peek() < arr[i]) {
                while (true) {
                    if (stack.empty() || stack.peek() >= arr[i] || K == 0)
                        break;
                    stack.pop();
                    K -= 1;
                }
            }
            stack.add(arr[i]);
        }

        for (int i = 0; i < K; i++) {
            stack.pop();
        }


        for (int tmp : stack) {
            sb.append(tmp);
        }

        System.out.println(sb);
    }
}
