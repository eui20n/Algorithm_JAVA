/**
 * 문제 이름 : 0 만들기
 * URL : https://www.acmicpc.net/problem/7490
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t7000f7999.p7490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int T;
    static StringBuilder resultSB = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            check(Integer.parseInt(br.readLine()));
        }
        System.out.println(resultSB);
    }

    static void check(int num) {
        bruteForce(num, 2, new int[num + 1]);
        resultSB.append("\n");
    }

    static void bruteForce(int num, int idx, int[] operatorArr) {
        /*
            num : 입력으로 받은 수
            idx : operatorArr의 현재 인덱스
            operatorArr : 현재 위치가 더하기인지 빼기인지 기록
                1 : 더하기
                2 : 빼기
                3 : 이어붙이기
                0 : 아직 아무 것도 아닌 것
         */
        if (idx > num) {
            if (compute(operatorArr) == 0)
                resultSB.append(printResult(operatorArr)).append("\n");
            return;
        }
        // 이어붙이기
        operatorArr[idx] = 3;
        bruteForce(num, idx + 1, operatorArr);
        // 더하기
        operatorArr[idx] = 1;
        bruteForce(num, idx + 1, operatorArr);
        // 빼기
        operatorArr[idx] = 2;
        bruteForce(num, idx + 1, operatorArr);
    }

    static int compute(int[] operatorArr) {
        /*
         연산을 진행하는 메소드

         연산을 진행하기 전에 어떤 수를 연산할지 연산을 해주기
         */
        Stack<Integer> numList = new Stack<>(); // 숫자가 들어갈 리스트

        int num = 1;
        numList.add(num); // 초기 숫자 1이 들어감
        num += 1;
        for (int operator : operatorArr) {
            if (operator == 0)
                continue;
            else if (operator == 3) {
                // numList에서 가장 위에 있는 값을 빼서 num랑 이어붙여주기
                int popNum = numList.pop();
                if (popNum >= 0) {
                    int addNum = Integer.parseInt(String.valueOf(popNum) + String.valueOf(num));
                    numList.add(addNum);
                } else {
                    int addNum = Integer.parseInt(String.valueOf(popNum * -1) + String.valueOf(num));
                    numList.add(addNum * -1);
                }
            } else if (operator == 1) {
                numList.add(num);
            } else if (operator == 2) {
                numList.add(num * -1);
            }
            num += 1;
        }

        // 진짜 연산하기
        int result = numList.pop();
        while (true) {
            if (numList.isEmpty())
                break;
            int operationNum = numList.pop();
            result += operationNum;
        }
        return result;
    }

    static String printResult(int[] operatorArr) {
        // 입력 배열을 수식으로 바꿔서 반환해주는 메소드
        StringBuilder sb = new StringBuilder();

        int num = 1;
        sb.append(num++);

        for (int operator : operatorArr) {
            if (operator == 0)
                continue;
            if (operator == 1) {
                sb.append("+");
            }
            if (operator == 2) {
                sb.append("-");
            }
            if (operator == 3) {
                sb.append(" ");
            }
            sb.append(num++);
        }
        return sb.toString();
    }
}

/*
        완전 탐색 문제
 */