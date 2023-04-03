/**
 * 문제 이름 : 스택
 * URL : https://www.acmicpc.net/problem/10828
 * 문제 설명 :
 * 5가지의 명령이 있다.
 * push X : 정수 X를 스택에 넣는 연산
 * pop : 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력. 만약 값이 없다면 -1 출력
 * size : 스택에 들어있는 정수의 개수를 출력
 * empty : 스택이 비어있으면 1, 아니면 0을 출력
 * top : 스택의 가장 위에 있는 정수를 출력. 없다면 -1 출력
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t10000f10999.p10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");

            if (command[0].equals("push")) {
                stack.add(command[1]);
            } else if (command[0].equals("top")) {
                System.out.println(stack.isEmpty() ? -1 : stack.peek());
            } else if (command[0].equals("size")) {
                System.out.println(stack.size());
            } else if (command[0].equals("pop")) {
                System.out.println(stack.isEmpty() ? -1 : stack.pop());
            } else if (command[0].equals("empty")) {
                System.out.println(stack.isEmpty() ? "1" : "0");

            }
        }
    }
}
