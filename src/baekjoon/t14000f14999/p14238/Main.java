/**
 * 문제 이름 : 출근 기록
 * URL : https://www.acmicpc.net/problem/14238
 * 문제 설명 :
 * A는 매일 출근 가능, B는 하루 쉬고 출근 가능, C는 이틀 쉬고 출근 가능
 * 이 때, 입력으로 들어온 값을 올바르게 출근한 것으로 바꿔라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14238;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String commute;
    static int A = 0;
    static int B = 0;
    static int C = 0;

    static String[] result;
    static String ans = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        commute = sc.next();

        result = new String[commute.length()];

        for (int i = 0; i < commute.length(); i++) {
            if (commute.charAt(i) == 'A') {
                A += 1;
            } else if (commute.charAt(i) == 'B') {
                B += 1;
            } else {
                C += 1;
            }
        }
        check(0);
    }

    static void check(int idx) {
        if (A == 0 && B == 0 && C == 0) {
            // 기저 조건
            System.out.println(Arrays.toString(result));
            return;
        }

        if (A > 0) {
            // A로 들어가기
            A -= 1;
            result[idx] = "A";
            check(idx + 1);
            A += 1;
        }
        if (B > 0 && checkB(idx)) {
            // B로 들어가기 => 로직 체크
            B -= 1;
            result[idx] = "B";
            check(idx + 1);
            B += 1;
        }
        if (C > 0 && checkC(idx)) {
            // C로 들어가기 => 로직 체크
            C -= 1;
            result[idx] = "C";
            check(idx + 1);
            C += 1;
        }
    }

    static boolean checkB(int idx) {
        if (idx == 0)
            return true;
        if (result[idx - 1].equals("B"))
            return false;
        return true;
    }

    static boolean checkC(int idx) {
        if (idx == 0 || idx == 1)
            return true;
        if (result[idx - 1].equals("C") || result[idx - 2].equals("C"))
            return false;
        return true;
    }
}

// 백트래킹
// 각각을 나눈 후 하기