/**
 * 문제 이름 : 임스와 함께하는 미니게임
 * URL : https://www.acmicpc.net/problem/25757
 * 문제 설명 :
 * 윷놀이 Y, 같은 그림 찾기 F, 원카드 O, 각각 2, 3, 4명이 필요하다. 인원수가 부족하면 게임을 하지 않는다.
 * 이 때, 임스가 총 해야하는 게임의 수를 구해라. 단, 같은 사람과는 게임을 다시 하지 않는다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 해시셋
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t25000f25999.p25757;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static int N;
    static String K;
    static HashSet<String> hashSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputTmp = br.readLine().split(" ");

        N = Integer.parseInt(inputTmp[0]);
        K = inputTmp[1];
        hashSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            hashSet.add(br.readLine());
        }

        check();
    }

    static void check() {
        int gamePopulation = population();
        System.out.println(hashSet.size() / (gamePopulation - 1));
    }

    static int population() {
        switch (K) {
            case "Y" : return 2;
            case "F" : return 3;
            case "O" : return 4;
            default : return 0;
        }
    }
}
