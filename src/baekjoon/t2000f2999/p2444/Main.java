package baekjoon.t2000f2999.p2444;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        printStar(N);
    }

    static void printStar(int n) {
        int loopNum = 2 * n - 1;
        int blankSpace = n - 1;
        boolean blank = true;

        for (int i = 0; i < loopNum; i++) {
            int starLoopNum = 2 * (n - blankSpace - 1) + 1;

            for (int j = 0; j < blankSpace; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < starLoopNum; j++) {
                System.out.print("*");
            }
            System.out.println();

            if (blankSpace == 0) {
                blank = false;
            }

            if (blank) {
                blankSpace--;
            } else {
                blankSpace++;
            }
        }
    }
}
