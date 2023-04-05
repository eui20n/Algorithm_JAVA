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

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

