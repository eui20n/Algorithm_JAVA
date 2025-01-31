/**
 * 문제 이름 : 1의 개수 세기
 * URL : https://www.acmicpc.net/problem/9527
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t9000f9999.p9527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static long[] range;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        range = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        check();
    }

    static void check() {
        long A = range[0];
        long B = range[1];

        char[] binaryA = makeBinaryArr(A);
        char[] binaryB = makeBinaryArr(B);

        long binaryCntA = binaryCnt(binaryA, A);
        long binaryCntB = binaryCnt(binaryB, B);

        long binaryAOneCnt = oneCnt(binaryA);

        System.out.println(binaryCntB - binaryCntA + binaryAOneCnt);
    }

    static long binaryCnt(char[] charArr, long num) {
        int loopSize = charArr.length;
        long cnt = 0;
        long binaryNum = 1;

        for (int i = 0; i < loopSize; i++) {
            long startNum = num - binaryNum + 1;

            long quotient = startNum / (binaryNum * 2); // 몫
            long remainder = startNum % (binaryNum * 2); // 나머지

            cnt += quotient * binaryNum;
            cnt += binaryNum >= remainder ? remainder : binaryNum;

            binaryNum *= 2;
        }
        return cnt;
    }

    static long oneCnt(char[] charArr) {
        long cnt = 0;
        for (int i = 0; i < charArr.length; i++) {
            cnt += charArr[i] == '1' ? 1 : 0;
        }
        return cnt;
    }

    static char[] makeBinaryArr(long num) {
        // 이진수를 구해서 char의 배열로 반환해주는 메소드
        StringBuilder sb = new StringBuilder();

        long binaryNum = 1;
        while (true) {
            if (binaryNum > num)
                break;

            sb.append((num & binaryNum) >= 1 ? 1 : 0);
            binaryNum *= 2;
        }

        return sb.reverse().toString().toCharArray();
    }
}

/*
        간단 로직
    2^0은 2^1마다 2^0개씩 등장함 => 시작이 2^0
    2^1은 2^2마다 2^1개씩 등장함 => 시작이 2^1
    2^2은 2^3마다 2^2개씩 등장함 => 시작이 2^2
    2^3은 2^4마다 2^3개씩 등장함 => 시작이 2^3
    2^4은 2^5마다 2^4개씩 등장함 => 시작이 2^4
    ...
    2^n은 2^(n+1)마다 2^n개씩 등장함 => 시작이 2^n

    시작점은 전체 크기에서 2^n - 1곳에서 하면 됨
    그리고 그 수의 몫과 나머지를 구하기
    그 후, (몫 * 2^n) + k를 하면 되는데
    k는 남은 등장횟수로 나머지를 이용해서 구하는 것임
    예를 들면 2^n에서 n이 4라고 가정하고, 나머지가 12이면 k는 12임
    왜냐하면, 총 16면 등장할 수 있는데, 12번 밖에 등장안했으니...
    그리고 만약 16을 넘은 23 이런 숫자라고 해도 16임... 왜냐하면, 이진수의 등장 주기이기 때문에 처음 16 이후에는 0이 16번 등장함

    위 로직으로 1부터 A와 1 부터 B를 구한 후, 그 두 수를 빼면 됨 => 누적합

 */

/* 1 ~ 32
1 => 000001 => 1
2 => 000010 => 1
3 => 000011 => 2
4 => 000100 => 1
5 => 000101 => 2
6 => 000110 => 2
7 => 000111 => 3
8 => 001000 => 1
9 => 001001 => 2
10 => 001010 => 2
11 => 001011 => 3
12 => 001100 => 2
13 => 001101 => 3
14 => 001110 => 3
15 => 001111 => 4
16 => 010000 => 1
17 => 010001 => 2
18 => 010010 => 2
19 => 010011 => 3
20 => 010100 => 2
21 => 010101 => 3
22 => 010110 => 3
23 => 010111 => 4
24 => 011000 => 2
25 => 011001 => 3
26 => 011010 => 3
27 => 011011 => 4
28 => 011100 => 3
29 => 011101 => 4
30 => 011110 => 4
31 => 011111 => 5
32 => 100000 => 1
 */