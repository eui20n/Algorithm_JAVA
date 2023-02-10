/**
 * 문제 이름 : 괄호 짝짓기
 * 문제 설명 : 괄호들의 짝이 모두 맞는지 출력하기
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {
            N = Integer.parseInt(br.readLine());
            String[] words = br.readLine().split("");
            int result = stack(words);
            System.out.printf("#%d %d %n", i, result);
        }

    }

    static int stack(String[] words) {
        Stack<String> check = new Stack<>();

        for (String word : words) {
            if (check.size() == 0) {
                check.add(word);
            } else if (check.peek().equals("(") && word.equals(")")) {
                check.pop();
            } else if (check.peek().equals("[") && word.equals("]")) {
                check.pop();
            } else if (check.peek().equals("{") && word.equals("}")) {
                check.pop();
            } else if (check.peek().equals("<") && word.equals(">")) {
                check.pop();
            } else {
                check.add(word);
            }
        }

        if (check.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
