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
    static char[] word;
    static int[] wordCnt = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        word = br.readLine().toCharArray();
        checkCnt(word, wordCnt);

        int result = 0;

        for (int i = 1; i < N; i++) {
            result += check(br.readLine().toCharArray());
        }

        System.out.println(result);
    }

    static int check(char[] inputWord) {
        // 배열에서 각 단어가 몇 번 나오는지 확인하기
        int[] inputCnt = new int[26];
        checkCnt(inputWord, inputCnt);

        // 단어의 요소 비교는 원본 배열에서 입력 배열을 빼서 차이를 비교
        int[] addMinusArr = new int[26]; // 위 결과를 담을 배열

        for (int i = 0; i < 26; i++) {
            addMinusArr[i] = wordCnt[i] - inputCnt[i];
        }

        int cnt1 = 0; // 1인 것의 개수
        int cnt0 = 0; // 0인 것의 개수
        int cnt_1 = 0; // -1인 것의 개수
        // 비슷한 단어인지 확인하기
        for (int addMinus : addMinusArr) {
            // 전부다 0일 경우 => 비슷한 단어
            // 한 개만 -1일 경우 => 비슷한 단어 (단어 한 개 빼면 됨)
            // 한 개만 1일 경우 => 비슷한 단어 (단어 한 개 추가하면 됨)
            // 만약에 -1 보다 작고 1보다 큰 것이 있다면 비슷한 단어가 될 수 없음
            // -1인 것은 1로 대체가 가능함 (딱 한 번)

            if (addMinus == 1) {
                cnt1 += 1;
            } else if (addMinus == 0) {
                cnt0 += 1;
            } else if (addMinus == -1) {
                cnt_1 += 1;
            } else {
                return 0;
            }
        }

        if (cnt0 == 26) {
            // 구성이 같음
            return 1;
        }
        if (cnt1 == 1 && cnt_1 == 1) {
            // 하나를 대체하면 같아짐
            return 1;
        }
        if (cnt1 == 1 && cnt_1 == 0) {
            // 하나를 추가하면 같아짐
            return 1;
        }
        if (cnt1 == 0 && cnt_1 == 1) {
            // 하나를 빼면 같아짐
            return 1;
        }
        return 0;
    }

    static void checkCnt(char[] checkWord, int[] wordCnt) {
        // 각 단어가 몇 번 등장하는지 확인
        for (char charWord : checkWord) {
            wordCnt[charWord - 'A'] += 1;
        }
    }
}