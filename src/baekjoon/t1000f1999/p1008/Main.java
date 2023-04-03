/**
 * 문제 이름 : A/B
 * URL : https://www.acmicpc.net/problem/1008
 * 문제 설명 : 입력받은 두 변수 A와 B를 나누면 됨
 * 시간복잡도 : O(1) -> 나누기 연산 한번
 * 핵심 로직 및 생각 : 입력 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static double result;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bf.readLine().split(" ");
        result = (double) Integer.parseInt(temp[0]) / Integer.parseInt(temp[1]);
        System.out.println(result);
    }
}
