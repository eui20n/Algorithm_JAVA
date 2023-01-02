/**
 * 문제 이름 : 음계
 * URL : https://www.acmicpc.net/problem/2920
 * 문제 설명 : 음계가 오름차순이면  ascending, 내림차순이면 descending, 둘 다 아니면 mixed
 * 시간복잡도 : O(N) => 문자열의 길이만큼
 * 핵심 로직 및 생각 : 반복문 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int num = numbers[0];

        if (ascending(num)) {
            System.out.println("ascending");
        } else if (descending(num)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }

    public static boolean ascending(int num) {
        for (int number : numbers) {
            if (num > number) {
                return false;
            }
            num = number;
        }
        return true;
    }

    public static boolean descending(int num) {
        for (int number : numbers) {
            if (num < number) {
                return false;
            }
            num = number;
        }
        return true;
    }

}
