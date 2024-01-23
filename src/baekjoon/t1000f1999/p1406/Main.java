/**
 * 문제 이름 : 에디터
 * URL : https://www.acmicpc.net/problem/1406
 * 문제 설명 :
 * 편집기에서 지원하는 명령어는 아래와 같다
 * 1. L => 커서를 왼쪽으로 한 칸 옮김(커서가 문장의 맨 앞이면 무시됨)
 * 2. D => 커서를 오른쪽으로 한 칸 옮김(커서가 문장의 맨 뒤이면 무시됨)
 * 3. B => 커서 왼쪽에 있는 문자를 삭제함(커서가 문장의 맨 앞이면 무시됨), 삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만
 * 실제로 커서의 오른쪽에 있던 문자는 그대로임
 * 4. P$ => $라는 문잘르 커서 왼쪽에 추가함
 * 초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어
 * 있는 문자열을 구하는 프로그램을 만들어라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] words;
    static Stack<Character> leftStack = new Stack<>(); // 커서 기준으로 왼쪽
    static Stack<Character> rightStack = new Stack<>(); // 커서 기준으로 오른쪽
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        words = br.readLine().toCharArray();
        int commandCnt = Integer.parseInt(br.readLine());

        for (char word : words) {
            leftStack.add(word);
        }

        for (int i = 0; i < commandCnt; i++){
            char[] command = br.readLine().toCharArray();
            command(command);
        }

        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftStack.size(); i++) {
            sb.append(leftStack.get(i));
        }
        for (int i = rightStack.size() - 1; i >= 0; i--) {
            sb.append(rightStack.get(i));
        }

        System.out.println(sb);
    }

    static void command(char[] command) {
        if (command[0] == 'P') {
            char addWord = command[2];
            leftStack.add(addWord);
        }
        if (command[0] == 'L') {
            if (leftStack.size() != 0) {
                rightStack.add(leftStack.pop());
            }
        }
        if (command[0] == 'D') {
            if (rightStack.size() != 0) {
                leftStack.add(rightStack.pop());
            }
        }
        if (command[0] == 'B') {
            if (leftStack.size() != 0) {
                leftStack.pop();
            }
        }
    }
}

// 초기에 커서는 제일 뒤에 위치해 있다.
// 왼쪽 스택과 오른쪽 스택을 만들어서 관리하면 됨