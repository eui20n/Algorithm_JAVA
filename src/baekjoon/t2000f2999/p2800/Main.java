/**
 * 문제 이름 : 괄호 제거
 * URL : https://www.acmicpc.net/problem/2800
 * 문제 설명 :
 * 주어지는 수식에서 괄호를 제거해서 나올 수 있는 것들을 사전식으로 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] operator;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        operator = br.readLine().toCharArray();

        check();
    }
    static void check() {

    }
}
// 안쪽에 있는 괄호먼저 없애야함