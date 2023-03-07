/**
 * 문제 이름 : 보물상자 비밀번호
 * 문제 번호 : 5658
 * 문제 설명 : 16진수 숫자가 적혀있는 보물상자가 있다. 이 보물상자의 각변에 동일한 개수의 숫자가 있다.
 * 비밀번호를 돌려서 각 변의 숫자 배열이 바뀔 수 있는데, 이 때 k번째로 큰 수가 무엇인지 10진수로 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : Sliding Window
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t5000f5999.p5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");
            int N = Integer.parseInt(tmp[0]);
            int K = Integer.parseInt(tmp[1]);

            String tmp2 = br.readLine();
            char[] arr = tmp2.toCharArray();
            sb.append("#").append(i).append(" ").append(password(arr, N, K)).append("\n");
        }
        System.out.println(sb.toString());
    }


    static int password(char[] arr, int N, int K) {
        List<Integer> password = new ArrayList<>();
        int window = N / 4; // 슬라이딩 윈도를 하기 위해서 창문 크기 설정
        for (int i = 0; i < N; i++) {
            // 창문
            char[] tmp = new char[window];

            for (int j = i; j < window + i; j++) {
                // 창문 열기
                int idx = j >= N ? j - N : j;
                int idx2 = j - i >= N ? j - i - N : j - i;
                tmp[idx2] = arr[idx];
            }

            Integer num = Integer.parseInt(String.valueOf(tmp), 16); // 신기방기, 뒤에 진수를 쓰면 해당 문자열을 해당 진수로 변환해줌
            if (password.contains(num))
                continue;
            password.add(num);
        }
        // 크기순으로 정렬
        Collections.sort(password, (o1, o2) -> {
            return o2 - o1;
        });
        return password.get(K - 1);
    }
}

// 슬라이딩 윈도우를 활용해서 풀이하는 문제라고 생각