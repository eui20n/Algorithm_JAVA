/**
 * 문제 이름 : 단어 공부
 * URL : https://www.acmicpc.net/problem/1157
 * 문제 설명 : 영어로된 문자가 주어질 때, 가장 많이 사용된 단어를 출력해라, 만약에 2개라면 ?를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int[] cnt = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] charArr = br.readLine().toCharArray();

        check(charArr);
    }

    static void check(char[] charArr) {
        for (int i = 0; i < charArr.length; i++) {
            int idx = -1;
            char charValue = charArr[i];

            if (charValue >= 'a') {
                idx = charValue - 'a';
            } else if (charValue >= 'A') {
                idx = charValue - 'A';
            }
            cnt[idx] += 1;
        }

        int result = -1;
        int resultCnt = 0;
        int resultIdx = 0;

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > result) {
                resultIdx = i;
                result = cnt[i];
                resultCnt = 1;
            } else if (cnt[i] == result) {
                resultCnt += 1;
            }
        }

        if (resultCnt >= 2) {
            System.out.println("?");
        } else {
            System.out.println((char) (resultIdx + 'A'));
        }
    }
}
