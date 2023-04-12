/**
 * 문제 이름 : 가르침
 * URL : https://www.acmicpc.net/problem/1062
 * 문제 설명 :
 * K개의 문자를 학습해서 알 수 있는 단어의 최대값을 구해라
 * 단어의 시작과 끝은 "anta"와 "tica"이다
 * 여기서 a, n, t, i, c는 이미 학습이 된것으로 K개에 포함이 된다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K;
    static char[][] words;
    static boolean[] visited = new boolean[26];
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        words = new char[N][];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine().toCharArray();
        }

        // 5개는 이미 방문을 처리해줘서 시간을 최대한 줄일 것
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        if (K - 5 < 0) {
            ans = 0;
        } else {
            teach(K - 5, 1);
        }
        System.out.println(ans);
    }

    static void teach(int cnt, int idx) {
        if (cnt == 0) {
            // 읽을 수 있는 글자가 몇개인지 확인해주기
            int result = canRead();
            ans = Math.max(result, ans);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            teach(cnt - 1, i);
            visited[i] = false;
        }
    }

    static int canRead() {
        int cnt = N;

        for (char[] word : words) {
            for (char wordOne : word) {
                if (!visited[wordOne - 'a']) {
                    cnt -= 1;
                    break;
                }
            }
        }
        return cnt;
    }
}

// 단어의 시작과 끝은 "anta"와 "tica"이다 이거 체크해주기
// 이거 아님 => 문제에서 남극의 모든 언어는 해당 문자로 시작과 끝을 이룬다고 되어있음
// 어이없는 실수를 했구만 => 처음에 주어지는 a, n, t, i, c는 무조건 읽을 수 있다고 생각함 => K - 5 < 0 이면 읽을 수 있는 단어가 없음