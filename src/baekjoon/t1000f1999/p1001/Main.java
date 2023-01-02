/**
 * 문제 이름 : A-B
 * URL : https://www.acmicpc.net/problem/1001
 * 문제 설명 : 입력 받은 A-B를 출력하기
 * 시간복잡도 : O(1) -> 빼기 연산 한번
 * 핵심 로직 및 생각 : 입력 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        result = Integer.parseInt(temp[0]) - Integer.parseInt(temp[1]);
        System.out.println(result);
    }

}
