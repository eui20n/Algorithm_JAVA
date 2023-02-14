/**
 * 문제 이름 : 암호 생성기
 * 문제 번호 : 1225
 * 문제 설명 : 암호를 생성해서 출력하면 됨
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */
package SWEA.t1000f1999.p1225;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution {
    static Deque<Integer> password;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 10; i++) {
            sc.nextInt();

            password = new ArrayDeque<>();
            for (int j = 0; j < 8; j++) {
                password.add(sc.nextInt());
            }
            makePassword(i);
        }
    }

    static void makePassword(int t) {
        int cnt = 1;
        while (true) {
            if (cnt >= 6) {
                cnt = 1;
            }
            int first = password.pollFirst() - cnt++;

            if (first < 0) {
                first = 0;
            }
            password.add(first);
            if (first == 0) {
                break;
            }
        }
        System.out.printf("#%d ", t);
        for (int data : password){
            System.out.print(data + " ");
        }
        System.out.println();

    }
}