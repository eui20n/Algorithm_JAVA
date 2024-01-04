/**
 * 문제 이름 : 삼각형과 세 변
 * URL : https://www.acmicpc.net/problem/5073
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t5000f5999.p5073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int[] checkArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (checkArr[0] + checkArr[1] + checkArr[2] == 0) {
                break;
            }

            check(checkArr);
        }
    }

    static void check(int[] arr) {
        Arrays.sort(arr);

        int a = arr[0];
        int b = arr[1];
        int c = arr[2];

        if (a + b <= c) {
            System.out.println("Invalid");
        } else if (a == b && b == c) {
            System.out.println("Equilateral");
        } else if ((a == b && b != c) || (a != b && b == c)) {
            System.out.println("Isosceles");
        } else if (a != b && b != c) {
            System.out.println("Scalene");
        }
    }
}
