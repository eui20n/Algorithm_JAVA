/**
 * 문제 이름 : 암호 만들기
 * URL : https://www.acmicpc.net/problem/1759
 * 문제 설명 : 암호는 서로다른 L개의 알파벳 소문자들로 구성되며, 최소 한 개의 모음(a, e, i, o, u)과 최소 두개의 자음으로 구성되어 있다고 알려져 있다.
 *           문자와 암호의 길이가 주어졌을 때, 만들 수 있는 암호를 오름차순으로 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int L, C;
    static String[] arr;
    static List<String> vowel = new ArrayList<>();
    static String[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        L = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);

        arr = br.readLine().split(" ");
        Arrays.sort(arr);

        // 모름을 미리 넣어줌
        result = new String[L];
        vowel.add("a");
        vowel.add("e");
        vowel.add("i");
        vowel.add("o");
        vowel.add("u");

        check(0, 0, 0, 0, new boolean[C]);
        System.out.println(sb);
    }

    /**
     * 정답을 구해주는 함수
     * start : 시작 값
     * idx : 암호의 인덱스 번호를 알려주는 값으로 종료 조건으로도 쓰임
     * cntVowel : 모음의 개수
     * cnt : 자음의 개수
     * visited : 방문처리 배열
     * */
    static void check(int start, int idx, int cntVowel, int cnt, boolean[] visited) {
        // 종료조건
        if (idx == L) {
            // 만약에 모음의 개수가 1개 이상, 자음의 개수가 2개 이상이면 출력
            if (cntVowel >= 1 && cnt >= 2) {
                for (String word : result) {
                    sb.append(word);
                }
                sb.append("\n");
            }
            return;
        }

        // 구해주는 부분
        for (int i = start; i < C; i++) {
            // 이미 해당 문자가 있으면 건너띔
            if (visited[i])
                continue;

            // 없으면 방문
            visited[i] = true;
            // 값 넣어줌
            result[idx] = arr[i];
            // 모음이면 모음개수에 1 더해주고 백트랙킹
            if (vowel.contains(arr[i])) {
                cntVowel++;
                check(i + 1, idx + 1, cntVowel, cnt, visited);
                cntVowel--;
            } else {
                // 자음이면 자음개수에 1 더해주고 백트래킹
                cnt++;
                check(i + 1, idx + 1, cntVowel, cnt, visited);
                cnt--;
            }
            // 돌아와서 원래대로 해주기
            visited[i] = false;
            result[idx] = null;
        }
    }
}
