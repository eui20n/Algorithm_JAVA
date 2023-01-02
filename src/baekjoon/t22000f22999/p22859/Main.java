/**
 * 문제 이름 : HTML 파싱
 * URL : https://www.acmicpc.net/problem/22859
 * 문제 설명 : HTML을 가공하는 프로그램을 만들기
 * 시간복잡도 : O(N * M) => 입력받는 문자열의 길이만큼(N) * 연산횟수(M)
 * 핵심 로직 및 생각 : 파싱을 잘해야 하는 문제
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t22000f22999.p22859;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] HTML;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HTML = br.readLine().split("(<main>)|(</div>)|(</p>)|(</main>)");
        for(String tmp : HTML){
            System.out.println(tmp);
        }
    }

    public static void HTMLParsing(){
    }
}