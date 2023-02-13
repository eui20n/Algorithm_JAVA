/**
 * 문제 이름 : 탑
 * URL : https://www.acmicpc.net/problem/2493
 * 문제 설명 : 레이저가 수평으로 갈 떄, 각각의 송신기는 어느 송신기로 가는지 출력
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 제일 뒤에서 부터 스택으로 구현하면 될 듯
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t2000f2999.p2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        Stack<int[]> stack = new Stack<>();
        int[] result = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            if (stack.isEmpty() || stack.peek()[0] >= arr[i]) {
                stack.add(new int[]{arr[i], i});
                continue;
            }
            while (true) {
                if (stack.isEmpty()) {
                    stack.add(new int[]{arr[i], i});
                    break;
                }
                if (stack.peek()[0] >= arr[i]) {
                    stack.add(new int[]{arr[i], i});
                    break;
                }

                int[] stackPop = stack.pop();
                result[stackPop[1]] = i + 1;
            }
        }

        print(result);
    }

    static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
