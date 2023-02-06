/**
 * 문제 이름 : A + B - 6
 * URL : https://www.acmicpc.net/problem/10953
 * 문제 설명 : 입력으로 들어오는 두 정수 A, B의 A + B를 출력해라
 * 시간복잡도 : 문자열의 길이 = k, O(nk) -> 문자열에서 "," 만 찾아서 없애야 하기때문
 * 핵심 로직 및 생각 : 문자열 자르기를 할 수 있는지 확인하는 문제
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t10000f10999.p10953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        for(int i = 0; i < N; i++){
            String tempWord = bf.readLine();
            String[] tempArr = tempWord.split(",");
            int A = Integer.parseInt(tempArr[0]);
            int B = Integer.parseInt(tempArr[1]);

            System.out.println(A + B);
        }
    }
}
