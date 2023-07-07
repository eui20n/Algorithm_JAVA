/**
 * 문제 이름 : 구슬 찾기
 * URL : https://www.acmicpc.net/problem/2617
 * 문제 설명 :
 * 홀수개의 서로 다른 무게의 구슬 N개가 있다. 구슬을 무게 순으로 정렬 할 때, 구슬의 가운데(N(N - 1)/2)에 올 수 없는 구슬의 수가 몇 개인지 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2617;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        arr = new int[M][2];

        for (int i = 0; i < M; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}

/*
완전 탐색이 될까 생각해보기
 */