/**
 * 문제 이름 : 최빈수 구하기
 * 문제 설명 : 1000개의 수가 주어질 때, 최빈수를 구하면 됨, 이때, 최빈수가 여러개면 가장 큰 값을 출력하면 됨
 * 시간복잡도 : O(1000) => 딱 한번만 돌면 됨
 * 핵심 로직 및 생각 : 해쉬맵에 저장해서 최빈수와 큰 값을 계속해서 갱신하면 됨
 * 소요 시간 : 1초
 * 제출할때, package 삭제하기
 */


package SWEA.t1000f1999.p1204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    static int T;
    static int N;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            scores = Arrays.stream(br.readLine().split(" ")).
                    mapToInt(Integer::parseInt).toArray();

            System.out.println("#" + N + " " + check());
        }
    }

    public static int check() {
        HashMap<Integer, Integer> scoreCnt = new HashMap<>();
        int result = -1;
        int maxCnt = -1;

        for (int score : scores) {
            if (scoreCnt.containsKey(score)) {
                int newValue = scoreCnt.get(score) + 1;
                scoreCnt.replace(score, newValue);
            } else {
                scoreCnt.put(score, 1);
            }

            int value = scoreCnt.get(score);
            if (value > maxCnt) {
                result = score;
                maxCnt = value;
            } else if (value == maxCnt && result < score) {
                result = score;
            }
        }

        return result;
    }
}
