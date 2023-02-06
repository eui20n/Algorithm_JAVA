package baekjoon.t2000f2999.p2440;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        printStar(N);
    }

    static void printStar(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                System.out.print("*");
            }
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//			}
            System.out.println();
        }
    }
}