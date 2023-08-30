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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        int[] arr = makeDpTable();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(arr[n]);
        }
    }

    static int[] makeDpTable() {
        int[] dpTable = new int[10001];
        dpTable[1] = 1;
        dpTable[2] = 2;
        dpTable[3] = 3;

        return dpTable;
    }
}

// dp의 향기가 난다.
// 배낭 문제처럼 풀어야하나??
// 처음에 그냥 다 만들고 답을 계속 찍어내면 되나?