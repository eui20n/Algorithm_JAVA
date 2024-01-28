/**
 * 문제 이름 : 1, 2, 3 더하기 4
 * URL : https://www.acmicpc.net/problem/15989
 * 문제 설명 :
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 4가지 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 * 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 친다.
 * 1 + 1 + 1 + 1
 * 2 + 1 + 1 = 1 + 1 + 2 = 1 + 2 + 1
 * 2 + 2
 * 1 + 3 = 3 + 1
 * 정수 n이 주어졌을 때, n을 1, 2 ,3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t15000f15999.p15989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    static int[] arr = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(arr[n]);
        }
    }

    static int makeDpTable() {
        return 1;
    }
}

/*
        DP로 예상
        일단은 완전탐색을 통해서 답을 구하기
        그 후, 가지를 치면서 불필요한 부분 없애기

        => 이거 풀려면 그림을 그려야 될듯...


 */