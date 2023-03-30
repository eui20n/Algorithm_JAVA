/**
 * 문제 이름 : 문자열 교환
 * URL : https://www.acmicpc.net/problem/1522
 * 문제 설명 :
 * 문자열은 끝과 시작이 연결되어 있다. 이 때, a가 연속된 문자열로 바꾸고 싶다.
 * 교환 횟수의 최소값은 몇인가?
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] word;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine().split("");

        System.out.println(check());
    }

    static int check() {
        int cntB = cntB();

        if (cntB == word.length) {
            return 0;
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < word.length; i++) {
            int minValue = 0;
            for (int j = 0; j < cntB; j++) {
                int idx = i + j >= word.length ? i + j - word.length : i + j;
                if (word[idx].equals("a")) {
                    minValue += 1;
                }
            }
            result = Math.min(result, minValue);
        }

        return result;
    }

    static int cntB() {
        int cnt = 0;
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals("b")) {
                cnt += 1;
            }
        }
        return cnt;
    }
}

// B가 한곳에 뭉쳐야함

