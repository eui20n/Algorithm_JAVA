/**
 * 문제 이름 : 비슷한 단어
 * URL : https://www.acmicpc.net/problem/2607
 * 문제 설명 :
 * 첫 번째로 등장하는 단어와 비슷한 단어의 수를 출력하면 된다.
 * 같은 구성 조건
 * 1. 두 개의 단어가 같은 종류의 문자로 이루어져 있다.
 * 2. 같은 문자는 같은 개수 만큼 있다.
 * 비슷한 단어 조건
 * 1. 두 개의 단어가 같은 구성을 갖는 경우, 한 단어에서 한 문자를 더하거나, 빼거나, 하나의 문자를 다른 문자로 바꾸어 나머지 한 단어와 같은
 * 구성을 갖게 되는 경우 비슷한 단어라고 함
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int containWord;
    static int[] cntWord = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] tmp = br.readLine().toCharArray();
        makeStandard(tmp);
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            char[] word = br.readLine().toCharArray();
            if (check(word))
                result += 1;
        }
        System.out.println(result);

    }

    static boolean check(char[] word) {
        int wordNum = 0;
        int[] wordCnt = new int[26];

        // 구성이 같은지 보기
        for (char checkWord : word) {
            wordNum |= 1 << (checkWord - 'A');
            wordCnt[checkWord - 'A'] += 1;
        }

        if (wordNum == containWord)
            return false;

        // 하나를 빼거나 더하거나 하나의 문자를 다른 문자로 대체해서 같은지 확인하기 => 결국에 차이가 3이상이면 안됨
        // 빼기 전에 같은지 확인하기
        int tmpCnt = 0;
        for (int i = 0; i < 26; i++) {
            if (wordCnt[i] == cntWord[i])
                tmpCnt += 1;
        }
        if (tmpCnt == 26)
            return true;

        // 하나 빼기
        for (int i = 0; i < 26; i++) {
            if (wordCnt[i] == 0)
                continue;
            wordCnt[i] -= 1;
            boolean tmpResult = sameCheck(wordCnt);
            wordCnt[i] += 1;
            if (tmpResult)
                return true;
        }
        // 하나 더하기
        for (int i = 0; i < 26; i++) {
            wordCnt[i] += 1;
            boolean tmpResult = sameCheck(wordCnt);
            wordCnt[i] -= 1;
            if (tmpResult)
                return true;
        }
        // 대체하기
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j)
                    continue;
                if (wordCnt[i] == 0)
                    continue;
                wordCnt[i] -= 1;
                wordCnt[j] += 1;
                boolean tmpResult = sameCheck(wordCnt);
                if (tmpResult)
                    return true;
                wordCnt[i] += 1;
                wordCnt[j] -= 1;
            }
        }
        return false;
    }

    static boolean sameCheck(int[] wordCnt) {
        int tmpCnt = 0;
        for (int i = 0; i < 26; i++) {
            if (wordCnt[i] == cntWord[i])
                tmpCnt += 1;
        }
        if (tmpCnt == 26)
            return true;
        return false;
    }

    static void makeStandard(char[] tmp) {
        for (char word : tmp) {
            containWord |= 1 << (word - 'A');
            cntWord[word - 'A'] += 1;
        }
    }
}