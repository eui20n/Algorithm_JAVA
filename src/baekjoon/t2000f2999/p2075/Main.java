/**
 * 문제 이름 : N번째 큰 수
 * URL : https://www.acmicpc.net/problem/2075
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < N; j++) {
                arr[idx++] = tmpInput[j];
            }
        }

        check();
    }
    static void check() {
        Arrays.sort(arr);
        System.out.println(arr[N * (N - 1)]);
    }
}

/*
    N번째 큰 수를 찾는 문제....
    DP라고 생각했지만, 아닌 것 같음 => 2차원 배열을 입력 딱 한번 받을 수 있음(메모리를 딱 그만큼만 줌)

    가장 큰 수는 반드시 가장 아래에 있음
    문제의 조건 때문에(현재 위치의 수는 한 칸 위의 수보다 크다)

    [방법 1.] => 이 방법은 성공하는지 모름
    2차원 배열의 각 행을 입력이 받자마자 내림차순으로 정렬 시킴
    그럼 각 행은 정렬이 되어 있고, 거기서 N번째 큰 수를 찾으면 됨
    N번째 큰 수를 찾을 때는 아래서 부터 탐색을 진행하면 됨

    [방법 2.] => 이 방법 사용... 정답
    그냥 처음부터 N^2 크기의 2차원 배열을 만들어서 입력을 받음
    입력을 다 받으면 그냥 정렬 하고, N번째 위치의 값을 출력하면 됨
 */