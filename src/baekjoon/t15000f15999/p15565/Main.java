/**
 * 문제 이름 : 귀여운 라이언
 * URL : https://www.acmicpc.net/problem/15565
 * 문제 설명 :
 * 라이언 인형이 K개 이상 있는 가장 작은 연속된 인형들의 집합의 크기를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t15000f15999.p15565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        check();
    }

    static void check() {
        List<Integer> slideList = new ArrayList<>();
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1)
                slideList.add(i);
        }
        if (K <= slideList.size()) {
            for (int i = 0; i < slideList.size() - K + 1; i++) {
                int start = slideList.get(i);
                int end = slideList.get(i + K - 1);

                result = Math.min(end - start + 1, result);
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1:result);
    }

}

// 덱 처럼 해도 될 듯
// 1의 위치를 다 세준다음에, K만큼 슬라이딩 윈도우