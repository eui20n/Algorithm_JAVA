/**
 * 문제 이름 : 단어 공부
 * URL : https://www.acmicpc.net/problem/1157
 * 문제 설명 : 알파벳 대소문자로 된 단어가 주어지면, 이 단어에서 가장 많이 사용된 알파벳이 무엇인지 출력해라.
 * 단, 대소문자는 구분하지 않는다.
 * 시간복잡도 : 단어의 길이만큼 O(N)
 * 핵심 로직 및 생각 : 각각의 단어를 구분해서 저장을 해야함
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static java.lang.Math.max;

public class Main {
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        words = br.readLine().toUpperCase().split("");
        wordStudy();
    }

    public static void wordStudy() {
        HashMap<String, Integer> wordCnt = new HashMap<String, Integer>();
        String resultWord = "";
        int cnt = 0;

        for (String word : words) {
            if (!(wordCnt.containsKey(word))) {
                wordCnt.put(word, 1);
                continue;
            }
            wordCnt.replace(word, wordCnt.get(word) + 1);
        }

        for (String test : wordCnt.keySet()) {
            int wordNumber = wordCnt.get(test);
            if (cnt < wordNumber) {
                cnt = wordNumber;
                resultWord = test;
            } else if (cnt == wordNumber) {
                resultWord = "?";
            }
        }

        System.out.println(resultWord);
    }
}
