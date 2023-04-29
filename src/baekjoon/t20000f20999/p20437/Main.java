/**
 * 문제 이름 : 문자열 게임
 * URL : https://www.acmicpc.net/problem/20437
 * 문제 설명 :
 * 게임 진행 방식
 * 1. 알파벳 소문자로 이루어진 문자열 W가 주어짐
 * 2. 양의 정수 K가 주어짐
 * 3. 어떤 문자를 정확히 K를 포함하는 가장 짧은 연속 문자열의 길이를 구함
 * 4. 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 문자열의 길이를 구함
 * 이 게임의 결과를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int T;
    static char[] words;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            words = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());
            check();
        }
    }

    static void check() {
        // 문자가 등장하는 위치를 기록하는 메소드
        List<List<Integer>> wordCnt = recordWordCnt();
        // 기록한 것을 가지고 슬라이딩 윈도우를 해주는 메소드
        int[] result = slidingWindow();
    }

    static List<List<Integer>> recordWordCnt() {
        List<List<Integer>> wordCnt = makeList(26);
        for (int i = 0; i < words.length; i++) {
            char word = words[i];
            wordCnt.get(word - 'a').add(i);
        }
        return wordCnt;
    }

    static int[] slidingWindow() {
        int[] result = new int[2];
        return result;
    }

    static List<List<Integer>> makeList(int length) {
        List<List<Integer>> tmp = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            tmp.add(new ArrayList<>());
        }
        return tmp;
    }
}

// 먼저 각각의 문자가 등장하는 위치를 기록을 함
// 위에서 기록한 것을 가지고 슬라이딩 윈도우하기 => 크기는 K
