/**
 * 문제 이름 : A와 B
 * URL : https://www.acmicpc.net/problem/12904
 * 문제 설명 : 문자열 S가 T가 될 수 있는지 출력하기
 * 연산은 아래 2가지 밖에 못한다.
 * 1. A를 추가한다.
 * 2. 뒤집고 B를 추가한다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 문자열을 잘 조작할 줄 알아야함, 연산을 반대로해서, T가 S가 될 수 있는지 보면 됨
 * 소요 시간 : 1시간
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t12000f12999.p12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S;
    static StringBuilder T = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // reverse 있음
        S = br.readLine();
        T = T.append(br.readLine());
        result();
    }

    public static void AandB() {
        int lastWordIsA = 0;
        int lastWordIsB = 0;
        while (S.length() != T.length()) {

            lastWordIsA = (lastWordIsA == -1) ? lastWordIsA : T.lastIndexOf("A");
            lastWordIsB = (lastWordIsB == -1) ? lastWordIsB : T.lastIndexOf("B");

            if (lastWordIsA > lastWordIsB) {
                // A가 제일 뒤에 있는 문자
                T.deleteCharAt(lastWordIsA);
            } else if (lastWordIsA < lastWordIsB) {
                // B가 제일 뒤에 있는 문자
                T.deleteCharAt(lastWordIsB);
                T.reverse();
            }
        }
    }

    public static void result() {
        AandB();
        String resultT = T.toString();
        if (S.equals(resultT)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}