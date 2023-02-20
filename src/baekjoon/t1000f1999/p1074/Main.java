/**
 * 문제 이름 : Z
 * URL : https://www.acmicpc.net/problem/1074
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1074;

import java.util.Scanner;

public class Main {
    static int N, r, c;
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

//        Z(0, 0, (int) Math.pow(2, N), 0);
        System.out.println(result);
    }

//    static int Z(int x, int y, int size, int cnt) {
//    }
}

// 범위를 체크 하고 해당 범위에 있으면 분할정복
