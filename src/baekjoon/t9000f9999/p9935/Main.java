/**
 * 문제 이름 : 문자열 폭발
 * URL : https://www.acmicpc.net/problem/9935
 * 문제 설명 :
 * 원래 문자열이 주어지고, 폭발이 일어나는 문자열이 주어진다. 원래 문자열에서 폭발이 일어아는 문자열이 폭발을 할 때, 폭발 후의 문자열을 출력해라
 * 만약에 다 폭발해서 문자열이 사라지면 FRULA를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t9000f9999.p9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] str;
    static char[] explosionStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        explosionStr = br.readLine().toCharArray();

        explosion();
    }

    static void explosion() {
        Stack<Character> stack = new Stack<>();
        int explosionLength = explosionStr.length;
        char check = explosionStr[explosionLength - 1];

        // 그냥 무조건 넣고 처리하는 것이 좋은 것 같음

        for (int i = 0; i < str.length; i++) {
            stack.add(str[i]);
//            System.out.println(stack);

            if (stack.size() < explosionLength || stack.peek() != check)
                continue;

            Stack<Character> tmp = new Stack<>();
            for (int j = explosionLength - 1; j >= 0; j--) {
                // 같은지 확인
                char tmpStr = stack.pop();
                tmp.add(tmpStr);
                if (tmpStr != explosionStr[j]) {
                    while (true) {
                        // 만약에 다르면 다시 추가해주기
                        // 추가가 이상하게 되는 듯
                        if (tmp.isEmpty())
                            break;
                        stack.add(tmp.pop());
                    }
                }
            }
        }
        resultPrint(stack);
    }

    static void resultPrint(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        for (char word : stack)
            sb.append(word);
        System.out.println(stack.size() == 0 ? "FRULA" : sb);
    }
}

// 만약에 시간이 초과가 난다면, 폭발 문자열이 아니라면 빼지않은 식으로 바꿔보기
// 1. 문자열의 길이가 폭발하는 문자열의 길이보다 작거나 stack이 비어있으면 stack에 넣기
// 2. 스택의 마지막 문자가 폭발하는 문자열의 마지막 문자와 같다면 stack을 검사
// 3. 딱 폭발하는 문자열의 길이만큼 검사하기 => 폭발하는 문자면 스택에서 그대로 제외되고, 아니면 다시 넣기