/**
 * 문제 이름 : 오큰수
 * URL : https://www.acmicpc.net/problem/17298
 * 문제 설명 :
 * 오큰수란 현재 위치에서 값보다 큰 오른쪽에 있는 가장 왼쪽에 있는 수를 의미한다. 배열의 각 자리에 대해서 오큰수가 있다면 오큰수를 출력하고
 * 없다면 -1을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ohBigNumber();
    }

    static void ohBigNumber() {
        // 오큰수 메소드
        int[] result = new int[N];

        Stack<int[]> stack = new Stack<>(); // 값, 인덱스

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.add(new int[]{arr[i], i});
                continue;
            }
            if (stack.peek()[0] >= arr[i]) {
                stack.add(new int[]{arr[i], i});
                continue;
            }
            if (stack.peek()[0] < arr[i]) {
                while (true) {
                    if (stack.isEmpty() || stack.peek()[0] >= arr[i]) {
                        stack.add(new int[]{arr[i], i});
                        break;
                    }

                    int[] stackValue = stack.pop();
                    int value = stackValue[0];
                    int idx = stackValue[1];

                    result[idx] = arr[i];
                }
            }
        }
        print(result);
    }

    static void print(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num == 0 ? -1 : num).append(" ");
        }
        System.out.println(sb);
    }
}

/*
[4, 8, 2, 1, 3, 7]

스택 - 항상 제일 뒤를 확인해줌
0. 항상 제일 뒤를 확인해줌
0-1. 제일 뒤 < 현재값 => 스택에서 빼주고, 제일 뒤에 있는 값의 오큰수는 현재 값이 됨
0-2. 제일 뒤 >= 현재값 => 스택에 그냥 넣어줌

1. 0번째 인덱스일 경우 => 아무것도 없기때문에 그냥 넣어주면 됨
오큰수 : [0, 0, 0, 0, 0, 0]
스택 : [[4, 0]]

2. 1번째 인덱스일 경우
오큰수 : [8, 0, 0, 0, 0, 0]
스택 : [[8, 1]]

3. 2번째 인덱스일 경우
오큰수 : [8, 0, 0, 0, 0, 0]
스택 : [[8, 1], [2, 2]]

4. 3번째 인덱스일 경우
오큰수 : [8, 0, 0, 0, 0, 0]
스택 : [[8, 1], [2, 2], [1, 3]]

5. 4번째 인덱스일 경우
오큰수 : [8, 0, 3, 3, 0, 0]
스택 : [[8, 1], [4, 3]]

6. 5번째 인덱스일 경우
오큰수 : [8, 0, 3, 3, 7, 0]
스택 : [[8, 1], [7, 5]]

7. 남은 스택을 확인하여, 남아있는 곳의 오큰수는 -1
오큰수 : [8, -1, 3, 3, 7, -1]
스택 : []

 */