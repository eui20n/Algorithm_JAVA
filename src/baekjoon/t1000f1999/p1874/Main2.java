/**
 * 문제 이름 : 스택 수열
 * URL : https://www.acmicpc.net/problem/1874
 * 문제 설명 :
 * 1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다. 이때, 스택에 push하는 순서는 반드시 오름차순을
 * 지키도록 한다고 하자. 임의의 수열이 주어졌얼 때, 스택을 이용해 그 수열을 만들 수 있는지 없는지, 있다면 어떤 순서로 push와 pop 연산을
 * 수행해야 하는지 알아낼 수 있다. 이를 계산하는 프로그램을 작성해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        stackSequence();
    }

    static void stackSequence() {
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            stack.add(i);
            sb.append("+").append("\n");

            while (true) {
                if (stack.isEmpty())
                    break;
                if (stack.peek() != arr[idx])
                    break;
                idx++;
                stack.pop();
                sb.append("-").append("\n");
            }
        }
        if (stack.isEmpty())
            System.out.println(sb);
        else
            System.out.println("NO");
    }
}
