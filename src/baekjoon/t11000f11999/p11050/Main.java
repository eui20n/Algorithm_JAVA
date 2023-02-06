package baekjoon.t11000f11999.p11050;

import java.util.Scanner;

public class Main {
    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        int result = factorial(N) / (factorial(K) * factorial(N - K));

        System.out.println(result);

    }

    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
