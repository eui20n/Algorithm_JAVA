/**
 * 문제 이름 : 보물상자 비밀번호
 * 문제 번호 : 5658
 * 문제 설명 : 16진수 숫자가 적혀있는 보물상자가 있다. 이 보물상자의 각변에 동일한 개수의 숫자가 있다.
 *           비밀번호를 돌려서 각 변의 숫자 배열이 바뀔 수 있는데, 이 때 k번째로 큰 수가 무엇인지 10진수로 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t5000f5999.p5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");
            int N = Integer.parseInt(tmp[0]);
            int K = Integer.parseInt(tmp[1]);

            String tmp2 = br.readLine();
            char[] arr = tmp2.toCharArray();
            password(arr, N, K);
        }
    }

    static void password(char[] arr, int N, int K) {
        String[] password = new String[N];
        int pointer = N / 4;
    }
}

// 자료구조를 생각해서 하기 => 그냥 배열로 할지, 덱으로 할지
// 구현은 덱이 쉬운데, 메모리나 시간 소요는 배열이 더 적음
