/**
 * 문제 이름 : 제로
 * URL : https://www.acmicpc.net/problem/10773
 * 문제 설명 :
 * 잘못된 수를 부를 때마다 0을 외쳐서, 들어간 가장 최근에 들어간 값을 없앨때, 남아 있는 값의 합을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t10000f10999.p10773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            if (k != 0) {
                stack.add(k);
                result += k;
            } else {
                result -= stack.pop();
            }
        }
        System.out.println(result);
    }
}
