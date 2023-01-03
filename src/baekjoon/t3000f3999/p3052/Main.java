/**
 * 문제 이름 : 나머지
 * URL : https://www.acmicpc.net/problem/3052
 * 문제 설명 : 10개의 수를 입력받아서 서로 다른 42로 나눈 나머지의 개수를 구해라
 * 시간복잡도 : O(10)
 * 핵심 로직 및 생각 : 입력 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        int num; // 입력값
        int num2; // 나머지
        List<Integer> numList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            num = Integer.parseInt(br.readLine());
            num2 = num % 42;
            numList.add(num2);
        }

        Set<Integer> numSet = new HashSet<>(numList);
        List<Integer> result = new ArrayList<>(numSet);

        System.out.println(result.size());
    }
}
