/**
 * 문제 이름 : 비밀번호 발음하기
 * URL : https://www.acmicpc.net/problem/4659
 * 문제 설명 :
 * 1. 모음(a, e, i, o, u)가 반드시 하나 포함
 * 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안됨
 * 3. 같은 글자가 연속적으로 두번 오면 안됨.. 단, ee와 oo는 허용
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t4000f4999.p4659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final String IS_ACCEPTABLE = " is acceptable.";
    static final String IS_NOT_ACCEPTABLE = " is not acceptable.";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String initialWord = br.readLine();
            char[] wordArr = initialWord.toCharArray();

            // 종료 조건
            if (wordArr.length == 3 && wordArr[0] == 'e' && wordArr[1] == 'n' && wordArr[2] == 'd') {
                break;
            }

            char pastWord = 'A';
            int totalVowelCnt = 0;
            int consonantCnt = 0; // 자음
            int vowelCnt = 0; // 모음
            boolean flag = true;
            StringBuilder sb = new StringBuilder();

            for (char word : wordArr) {
                if (word == 'a' || word == 'e' || word == 'i' || word == 'o' || word == 'u') {
                    totalVowelCnt += 1;
                }

                if (pastWord == word) {
                    if (!((pastWord == 'e' && word == 'e') || (pastWord == 'o' && word == 'o'))) {
                        flag = false;
                        break;
                    }
                }

                // 자음인지 모음인지 판단하는 메소드
                boolean consonant = checkConsonant(word);

                if (consonant) {
                    // 자음이면
                    if (consonantCnt >= 2) {
                        flag = false;
                        break;
                    }
                    vowelCnt = 0;
                    consonantCnt += 1;
                } else {
                    // 모음이면
                    if (vowelCnt >= 2) {
                        flag = false;
                        break;
                    }
                    consonantCnt = 0;
                    vowelCnt += 1;
                }

                pastWord = word;
            }

            if (flag && totalVowelCnt >= 1) {
                // 문제가 없는 경우
                sb.append("<").append(initialWord).append(">").append(IS_ACCEPTABLE);
            } else if (!flag || totalVowelCnt < 1) {
                // 문제가 있는 경우
                sb.append("<").append(initialWord).append(">").append(IS_NOT_ACCEPTABLE);
            }
            System.out.println(sb.toString());
        }
    }

    static boolean checkConsonant(char word) {
        // 자음이면 true
        // 모음이면 false
        if (word == 'a' || word == 'e' || word == 'o' || word == 'i' || word == 'u')
            return false;
        return true;
    }
}
