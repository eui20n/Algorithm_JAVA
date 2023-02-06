/**
 * 문제 이름 : 스택 수열
 * URL : https://www.acmicpc.net/problem/1874
 * 문제 설명 : 입력으로 숫자 n이 주어질 때, 입력으로 주어지는 배열을 스택을 이용해서 만들려고함
 * 스택의 push는 오름차순으로만 된다고 할때, 해당 배열을 만들기 위해서 필요한 연산을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t1000f1999.p1874;

import java.util.*;

public class Main {
    static int N;
    static final String PUSH = "+";
    static final String POP = "-";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] wantArr = new int[N];
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            wantArr[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        int idx = 0;

        for (int i = 1; i <= N; i++) {
            stack.push(i);
            list.add(PUSH);

            while (true) {
                if (stack.size() == 0)
                    break;
                if (stack.peek() != wantArr[idx])
                    break;
                result[idx++] = stack.pop();
                list.add(POP);
            }
        }

        if (result[result.length - 1] == 0) {
            System.out.println("NO");
        } else {
            for (String data : list) {
                System.out.println(data);
            }
        }
    }
}

// 1. 계속해서 push
// 2. push후 가장 뒤 요소를 확인
// 3. 해당 요소가 내가 만들고 싶은 배열의 요소와 같으면 pop
// 3-1. 뺀 후 3번 반복
// 4. 요소와 같지않으면 계속 push