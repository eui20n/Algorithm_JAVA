/**
 * 문제 이름 : 후위 표기식
 * URL : https://www.acmicpc.net/problem/1918
 * 문제 설명 :
 * 중위 표기식이 주어졌을 때, 후위 표기식으로 바꿔라
 * 후위 표기식 : 연산자가 피연산자 뒤에 위치하는 것
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[] operator;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        operator = br.readLine().toCharArray();

        System.out.println(Arrays.toString(operator));
    }
}
