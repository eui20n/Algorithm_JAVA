/**
 * 문제 이름 : 잃어버린 괄호
 * URL : https://www.acmicpc.net/problem/1541
 * 문제 설명 :
 * 양수와 +, -, 그리고 괄호를 가지고 식을 만들었는데, 괄호를 모두 지웠다.
 * 이 때 적절히 괄호를 쳐서 최소가 되게 만들어보자.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();

        // 정규 표현식으로 숫자와 연산자를 나눔
        String[] operator = tmp.split("[0-9]+");
        int[] num = Arrays.stream(tmp.split("[-+]")).mapToInt(Integer::parseInt).toArray();

        check(operator, num);
    }

    static void check(String[] operator, int[] num) {
        int minusCnt = 0;
        int result = 0;

        for (int i = 0; i < num.length; i++) {
            if (i == 0) {
                result += num[i];
                continue;
            }
            if (operator[i].equals("-") && minusCnt == 0) {
                // 처음 빼기가 나옴
                minusCnt = 1;
            } else if (operator[i].equals("-") && minusCnt == 0) {
                // 그 다음 빼기가 나옴
                minusCnt = 0;
            }
            if (minusCnt == 1) {
                // 그 다음 빼기 나올 때까지 계속 빼주기
                result -= num[i];
            } else {
                // 이 외는 계속 더해주기
                result += num[i];
            }
        }
        System.out.println(result);
    }
}

// 빼기가 나오면 그 다음 빼기가 나올 때까지 계속 빼주면 됨