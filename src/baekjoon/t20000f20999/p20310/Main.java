/**
 * 문제 이름 : 타노스
 * URL : https://www.acmicpc.net/problem/20310
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] inputArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputArr = br.readLine().toCharArray();
        check();
    }

    static void check() {
        // 없애야 하는 0의 개수와 1의 개수 확인
        int[] numCnt = numCnt();

        // 버려지는 것을 true로 하기 위한 boolean 배열 생성
        boolean[] thanos = new boolean[inputArr.length];

        // 없앨 것을 골라주기
        // 2개의 포인터를 이용할 것
        int pointer0 = inputArr.length - 1; // 0의 위치
        int pointer1 = 0; // 1의 위치

        for (int i = 0; i < inputArr.length; i++) {
            if (numCnt[0] + numCnt[1] == 0)
                break;
            if (numCnt[0] >= 1 && inputArr[pointer0] == '0') {
                thanos[pointer0] = true;
                numCnt[0] -= 1;
            }
            if (numCnt[1] >= 1 && inputArr[pointer1] == '1') {
                thanos[pointer1] = true;
                numCnt[1] -= 1;
            }
            pointer0 -= 1;
            pointer1 += 1;
        }

        // 출력
        for (int i = 0; i < inputArr.length; i++) {
            if (thanos[i])
                continue;
            System.out.print(inputArr[i]);
        }
    }

    static int[] numCnt() {
        int[] numCnt = new int[2]; // 없애야 하는 0의 개수과 1의 개수

        for (char value : inputArr) {
            if (value == '0')
                numCnt[0] += 1;
            if (value == '1')
                numCnt[1] += 1;
        }

        // 과연 0.5씩 더하고 double을 int로 바꾸는 것이 이득일까?
        // 다 더하고 2를 나누는 것이 더 이득일까?
        numCnt[0] /= 2;
        numCnt[1] /= 2;

        return numCnt;
    }
}

/*
    1은 가장 앞에 있는 것을 제거
    0은 가장 뒤에 있는 것을 제거
    => 그럼 사전 순으로 됨
 */