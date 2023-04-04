/**
 * 문제 이름 : 균형잡힌 세상
 * URL : https://www.acmicpc.net/problem/4949
 * 문제 설명 :
 * 1. 모든 소괄호는 짝("("와 ")")을 이뤄야 한다.
 * 2. 모든 대괄호는 짝을("["와 "]") 이뤄야 한다.
 * 3. 모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.
 * 4. 모든 괄호들의 짝은 1:1 매칭만 가능하다. 즉, 괄호 하나가 둘 이상의 괄호와 짝지어지지 않는다.
 * 5. 짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다.
 * 위 조건을 만족하는 것이 균형잡힌 문자열일 때, 입력으로 들어오는 문자열이 균형잡힌 문자열인지 아닌지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t4000f4999.p4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] sentence = br.readLine().split(" ");
            if (sentence[0].equals(".") && sentence.length == 1)
                break;

            check(sentence);

        }
    }

    static void check(String[] sentence) {
        Stack<String> stack = new Stack<>();

        String result = "yes";

        for (String word : sentence) {
            if (result.equals("no"))
                break;
            if (word.equals("(") || word.equals("[")) {
                stack.add(word);
            } else if (word.equals(")")) {
                if (stack.size() == 0 || !stack.peek().equals("(")) {
                    result = "no";
                } else {
                    stack.pop();
                }
            } else if (word.equals("]")) {
                if (stack.size() == 0 || !stack.peek().equals("[")) {
                    result = "no";
                } else {
                    stack.pop();
                }
            }
        }
        if (stack.size() != 0)
            result = "no";
        System.out.println(result);
    }
}
