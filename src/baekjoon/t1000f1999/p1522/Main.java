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

        check();
    }

    static void check() {
        int cntB = cntB();
        int cluster;
    }

    static int cntB() {
        int cnt = 0;
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals("B")) {
                cnt += 1;
            }
        }
        return cnt;
    }
}

// 결국에는 b를 한곳에 뭉치고 싶은 것
// bfs로 바꾸면 될거 같음
// 못가는 곳은 옆에 b가 없는 곳
// 옆에 b가 있는 곳으로 가게하기
// 불끄기??? 비트마스킹으로 한번만 생각해보기 => a = 0, b = 1
