/**
 * 문제 이름 : 두 수 비교하기
 * URL : https://www.acmicpc.net/problem/1330
 * 문제 설명 : 두 수 비교하기
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (numbers[0] > numbers[1]) {
            System.out.println(">");
        } else if (numbers[0] < numbers[1]) {
            System.out.println("<");
        } else {
            System.out.println("==");
        }
    }
}
