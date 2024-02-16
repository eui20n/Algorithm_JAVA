/**
 * 문제 이름 : 빌런 호석
 * URL : https://www.acmicpc.net/problem/22251
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t22000f22999.p22251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, K, P, X;
    // 각 수를 이진수로 표현한 것
    static String[] binaryArr = {
            "1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0]; // 쉽게 생각하면, 최고 층
        K = tmpInput[1]; // 엘리베이터의 자릿수
        P = tmpInput[2]; // 최대 반전 수
        X = tmpInput[3]; // 현재 층

        check();
    }

    static void check() {
        // 먼저 각각의 수에서 반전을 얼만큼 해야하는지 기록
        int[][] reversalArr = init();

        // 현재 층을 int의 배열로 바꾸기
        int[] XArr = numToArr(X);

        // 정답을 담을 변수
        int result = 0;

        // 1 ~ N 까지 반전이 가능한 수 찾기
        for (int i = 1; i <= N; i++) {
            if (X == i)
                continue;

            // 최대 반전 수 안에 반전을 시킬 수 있는지 확인
            int tmp = canReversal(reversalArr, XArr, i);
            if (P >= tmp) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    static int canReversal(int[][] reversalArr, int[] XArr, int num) {
        int[] numArr = numToArr(num); // 입력 받은 num을 배열로 만들기
        int reversalCnt = 0;

        for (int i = 0; i < numArr.length; i++) {
            reversalCnt += reversalArr[XArr[i]][numArr[i]];
        }

        return reversalCnt;
    }

    static int[] numToArr(int num) {
        int[] returnNum = new int[K];
        String[] numToStringArr = String.valueOf(num).split("");

        for (int i = 0; i < numToStringArr.length; i++) {
            returnNum[i + (K - numToStringArr.length)] = Integer.parseInt(numToStringArr[i]);
        }
        return returnNum;
    }

    static int[][] init() {
        int[][] reversalArr = new int[10][10];

        for (int i = 0; i < reversalArr.length; i++) {
            for (int j = 0; j < reversalArr[i].length; j++) {
                if (i == j)
                    continue;
                if (reversalArr[j][i] != 0) {
                    reversalArr[i][j] = reversalArr[j][i];
                    continue;
                }
                reversalArr[i][j] = reversal(binaryArr[i].toCharArray(), binaryArr[j].toCharArray());
            }
        }
        return reversalArr;
    }

    static int reversal(char[] biggerNum, char[] smallerNum) {
        int cnt = 0;
        for (int i = 0; i < biggerNum.length; i++) {
            if (biggerNum[i] != smallerNum[i])
                cnt += 1;
        }

        return cnt;

    }
}

/*
    가장 위 => 첫 번째
    위 왼쪽 => 두 번째
    위 오른쪽 => 세 번째
    가운데 => 네 번째
    아래 왼쪽 => 다섯 번째
    아래 오른쪽 => 여섯 번째
    가장 아래 => 일곱 번째

    0 => 1110111
    1 => 0010010
    2 => 1011101
    3 => 1011011
    4 => 0111010
    5 => 1101011
    6 => 1101111
    7 => 1010010
    8 => 1111111
    9 => 1111011
 */