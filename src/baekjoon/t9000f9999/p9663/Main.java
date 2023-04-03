package baekjoon.t9000f9999.p9663;

import java.util.Scanner;

public class Main {

    static int N, answer;
    static int[] col;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        col = new int[N + 1];
        setQueen(1);
        System.out.println(answer);
    }

    static void setQueen(int rowNo) { // rowNo: 놓으려고 하는 퀸의 행번호
        if (!isAvailable(rowNo - 1))
            return;

        if (rowNo > N) {
            answer += 1;
            return;
        }

        for (int c = 1; c <= N; c++) {
            col[rowNo] = c;
            setQueen(rowNo + 1);
        }
    }

    static boolean isAvailable(int rowNo) {
        for (int k = 1; k < rowNo; k++) { // k : 비교대상 queen의 행
            if (col[k] == col[rowNo])
                return false;
            if (Math.abs(rowNo - k) == Math.abs(col[k] - col[rowNo]))
                return false;
        }
        return true;
    }
}