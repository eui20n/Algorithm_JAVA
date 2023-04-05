/**
 * 문제 이름 : 쇠막대기
 * URL : https://www.acmicpc.net/problem/10799
 * 문제 설명 :
 * 막대기와 레이저의 배치는 아래를 만족한다.
 * 1. 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓을 수 있다. - 쇠막대기를 다른 쇠막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝점은 겹치지 않도록 놓는다.
 * 2. 각 쇠막대기를 자르는 레이저는 적어도 하나 존재한다.
 * 3. 레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않는다.
 * 레이저와 쇠막대기의 배치는 다음과 같이 괄호를 이용하여 왼쪽부터 순서대로 표현할 수 있다.
 * 1. 레이저는 여는 괄호와 닫는 괄호의 인접한 쌍 '()' 으로 표현된다. 모든 '()'는 반드시 레이저를 표현한다.
 * 2. 쇠막대기의 왼쪽 끝은 여는 괄호 '('로, 오른쪽 끝은 닫힌 괄호 ')'로 표현된다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t10000f10999.p10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] laser;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        laser = br.readLine().toCharArray();

        check();
    }

    static void check() {
        Stack<Character> stack = new Stack<>();

        int result = 0;
        int bar = 0;
        int idx = 0;

        while (true) {
            if (idx >= laser.length - 2)
                break;
            char word = laser[idx];
            char nextWord = laser[idx + 1];

            if (word == '(' && nextWord == ')') {
                // 레이저라는 의미
                result += stack.size();
                stack.add(word);
            } else if (word == '(') {
                // 막대라는 의미
                stack.add(word);
                bar += 1;
            } else if (word == ')') {
                stack.pop();
            }
            idx++;
        }
        System.out.println(result + bar);
    }
}
// 레이저의 위치에서 현재 몇개의 막대기가 있는지 개수를 세줌
// 다 세면 다 더하고 막대기의 수 만큼 더해줌
// 틀리면, 잘려지는 것이 없다면 막대기의 수 만큼 더하지 말기