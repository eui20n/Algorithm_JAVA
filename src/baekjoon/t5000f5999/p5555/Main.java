/**
 * 문제 이름 : 반지
 * URL : https://www.acmicpc.net/problem/5555
 * 문제 설명 : 반지의 문자가 있을 때, 원하는 문자가 몇개인지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 슬라이딩 윈도우
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */
package baekjoon.t5000f5999.p5555;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] findStr;
    static int N;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        findStr = br.readLine().split("");
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] ring = br.readLine().split("");
            result += find(ring);
        }

        System.out.println(result);
    }

    static int find(String[] ring) {
        String[] newString = new String[20];
        for (int i = 0; i < 10; i++) {
            newString[i] = ring[i];
            newString[i + 10] = ring[i];
        }

        int window = findStr.length;

        for (int i = 0; i < 20 - window; i++) {
            int cnt = 0;
            for (int s = 0; s < window; s++) {
                if (newString[i + s].equals(findStr[s])) {
                    cnt += 1;
                } else {
                    break;
                }
            }
            if (cnt == window)
                return 1;
        }

        return 0;
    }
}
