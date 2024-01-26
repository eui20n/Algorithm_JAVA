/**
 * 문제 이름 : 한 줄로 서기
 * URL : https://www.acmicpc.net/problem/1138
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    static int[] result;
    static int[] resultIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[N];
        resultIdx = new int[N];

        check();
    }

    static void check() {
        backTracking(1);
    }

    static void backTracking(int height) {
        if (height >= N + 1) {
            // 모든 사람이 다 줄은 선 경우

            // 조건 확인하기
            for (int i = 1; i < N + 1; i++) {
                if (!canStand(i, resultIdx[i - 1]))
                    return;
            }

            print(result);
            return;
        }
        // 입력은 키 => 1이면 키가 1인 사람
        // i는 내 왼쪽에 있는 사람 후
        for (int i = arr[height - 1]; i < N; i++) {
            if (result[i] != 0)
                // 이미 여기에 사람이 있음
                continue;

            result[i] = height;
            resultIdx[height - 1] = i;
            backTracking(height + 1);
            result[i] = 0;
            resultIdx[height - 1] = 0;
        }
    }

    static boolean canStand(int height, int idx) {
        // 현재 위치에 설 수 있는지 확인하기
        int leftPeopleCnt = arr[height - 1]; // 왼쪽에 더 큰 사람의 수
        int cnt = 0; // 현재 위치에 서게 될 경우 왼쪽에 더 큰 사람의 수

        // 왼쪽에 큰 사람이 몇 명인지 확인
        for (int i = 0; i < idx; i++) {
            if (height < result[i])
                cnt++;
        }

        if (leftPeopleCnt != cnt)
            return false;
        return true;
    }

    static void print(int[] printArr) {
        for (int num : printArr) {
            System.out.print(num + " ");
        }
    }
}

/*
        N의 크기가 크지 않은 것을 보니 완전 탐색을 해도 됨

        무작정 줄 세우고 마지막에 확인하기
        대신, 줄을 세울 때 입력으로 받은 조건은 확인하기 (내 앞에 2명이 나보다 크면 나는 적어도 2칸 뒤에 위치해야함)
 */