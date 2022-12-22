/**
 * 문제 이름 : A + B
 * URL : https://www.acmicpc.net/problem/1000
 * 문제 설명 : 입력으로 받은 두 정수를 더해주면 됨
 * 시간복잡도 : O(1) -> 더하기 연산 한번
 * 핵심 로직 및 생각 : 입력 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//
//        int num1 = sc.nextInt();
//        int num2 = sc.nextInt();
//
//        System.out.println(num1 + num2);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String temp = bf.readLine();
        String[] tempArr = temp.split(" ");

        int num1 = Integer.parseInt(tempArr[0]);
        int num2 = Integer.parseInt(tempArr[1]);

        System.out.println(num1 + num2);

    }
}
