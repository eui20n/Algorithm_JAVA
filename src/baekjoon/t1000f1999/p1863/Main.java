/**
 * 문제 이름 : 스카이라인 쉬운거
 * URL : https://www.acmicpc.net/problem/1863
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1863;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int N;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] changeAltitude;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            changeAltitude = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            stackCheck(stack, changeAltitude[1]);
        }
        stackCheck(stack, 0);
        System.out.println(result);
    }

    static void stackCheck(Stack<Integer> stack, int altitude) {
        if (stack.isEmpty() || stack.peek() < altitude) {
            stack.add(altitude);
        } else if (stack.peek() > altitude) {
            // 고도가 낮아짐
            // 현재 입력으로 들어온 고도와 같거나 낮아질때 까지 빼기
            while (true) {
                if (stack.isEmpty() || stack.peek() < altitude)
                    break;
                if (stack.peek() > altitude) {
                    stack.pop();
                    result += 1;
                } else if (stack.peek() == altitude) {
                    stack.pop();
                }
            }
            stack.add(altitude);
        }
    }
}

/*
        stack에 고도를 기록
        그럼 stack에 마지막에 들어간 고도가 현재 가장 큰 고도게 됨
        여기서 이 고도보다 낮은 고도가 입력으로 들어온다면, 해당 고도에 해당하는 건물이 있음 => 직사각형이라서 가능함
        그럼 stack에서 입력으롣 들어온 고도가 될 때까지 값을 빼주면 됨
        여기서 뺄때마다 결과에 1씩 더해주면됨
        그리고 입력을 다 받으면 마지막으로 0을 고도로 넣어서 stack에 남아 있는 값을 처리해주면 됨

        이 문제에서 그래프로 그려서 처리를 하면 안되는 이유는, 배열의 입력 크기가 너무 큼(1 <= x < 1,000,000, 0 <= y <= 500,000)
        => 입력 받다가 문제가 끝이남
        => 그래서 그래프를 안그리는 방향으로 문제를 풀어야함
 */