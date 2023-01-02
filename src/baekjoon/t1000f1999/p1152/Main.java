/**
 * 문제 이름 : 단어의 개수
 * URL : https://www.acmicpc.net/problem/1152
 * 문제 설명 : 입력 받은 문자열의 문자의 개수를 출력해라
 * 시간복잡도 : O(N) -> 문자열의 길이만큼
 * 핵심 로직 및 생각 : 입력 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] sentence = bf.readLine().split(" ");
        for(String word : sentence){
            if (!(word.length() == 0)){
                result += 1;
            }
        }
        System.out.println(result);
    }
}
