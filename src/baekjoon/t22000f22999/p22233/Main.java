/**
 * 문제 이름 : 가희와 키워드
 * URL : https://www.acmicpc.net/problem/22233
 * 문제 설명 :
 * 키워드가 몇개 남는지 출력하면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t22000f22999.p22233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int N, M;
    static int size;
    static HashMap<String, Boolean> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = size = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            hashMap.put(word, false);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String[] keyword = br.readLine().split(",");
            check(keyword);
            sb.append(size).append("\n");
        }
        System.out.println(sb);
    }

    static void check(String[] keyword) {
        for (String word : keyword) {
            if (hashMap.containsKey(word) && !hashMap.get(word)) {
                size -= 1;
                hashMap.replace(word, true);
            }
        }
    }

}
