/**
 * 문제 이름 : 괄호
 * URL : https://www.acmicpc.net/problem/9012
 * 문제 설명 :
 * 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열이라고 부른다. 괄호가 입력으로 주어질 때 올바른 문자열이면 YES 아니면 NO을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t9000f9999.p9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            check(br.readLine().split(""));
        }
    }

    static void check(String[] arr) {
        Stack<String> stack = new Stack<>();

        for (String word : arr) {
            if (stack.isEmpty() || word.equals("(")) {
                stack.add(word);
                continue;
            }
            if (word.equals(")")) {
                String newWord = stack.pop();
                if (newWord.equals(")")) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        if (stack.size() == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
