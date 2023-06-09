/**
 * 문제 이름 : 팔
 * URL : https://www.acmicpc.net/problem/1105
 * 문제 설명 :
 * 자연수 L과 R이 주어질 때, L <= K <= R 사이의 K중 8이 가장 적에 들어 있는 자연수에 있는 8의 개수를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String L, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        L = tmp[0];
        R = tmp[1];

        check();
    }

    static void check() {
        if (L.length() != R.length())
            System.out.println("0");
        else
            System.out.println(findEight());

    }

    static int findEight() {
        char[] LArr = L.toCharArray();
        char[] RArr = R.toCharArray();
        int result = 0;

        for (int i = 0; i < RArr.length; i++) {
            if (RArr[i] == '8' && LArr[i] == '8')
                result += 1;
            else if (RArr[i] == LArr[i])
                continue;
            else
                return result;
        }
        return result;
    }
}