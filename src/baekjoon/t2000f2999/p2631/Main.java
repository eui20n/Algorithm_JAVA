/**
 * 문제 이름 : 줄세우기
 * URL : https://www.acmicpc.net/problem/2631
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        input();
        check();
    }

    static void check() {
        dynamicProgramming(0, 0, 0, null);
    }

    static int dynamicProgramming(int start, int end, int result, int[] inputArr) {
        // 완전 탐색 구현
        if (checkAns(inputArr)) {
            // 기저 조건
            return result;
        }
        return 1;


    }

    static int[] deepCopy(int[] inputArr) {
        int[] deepCopyArr = new int[N];
        for (int i = 0; i < N; i++) {
            deepCopyArr[i] = inputArr[i];
        }
        return deepCopyArr;
    }

    static int findIdx(int num) {
        for (int i = 0; i < N; i++) {
            if (num == arr[i])
                return i;
        }
        return -1;
    }

    static boolean checkAns(int[] inputArr) {
        for (int i = 0; i < N; i++) {
            if (inputArr[i] != i)
                return false;
        }
        return true;
    }


    static void move(int start, int end) {
        /*
            start => 시작 위치
            end => 끝 위치
            즉, 시작 위치에 있는 값을 끝 위치로 옮겨주는 메소드
         */
        int checkNum = 1; // 부호를 결정해주는 값
        if (start > end) {
            checkNum = -1;
        }
        while (true) {
            if (start == end)
                break;

            arr[start] = arr[start] + arr[start + checkNum];
            arr[start + checkNum] = arr[start] - arr[start + checkNum];
            arr[start] = arr[start] - arr[start + checkNum];
            start += checkNum;

        }
    }





    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }
    }
}

/*
        각 자리 위치 바꾸기는 아래의 식 사용하기
        두 변수 a, b가 있다고 가정
        a = a + b
        b = a - b
        a = a - b

        DP라...
 */