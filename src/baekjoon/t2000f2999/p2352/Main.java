/**
 * 문제 이름 : 반도체 설계
 * URL : https://www.acmicpc.net/problem/2352
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 가장 긴 증가하는 부분수열 문제 + 시간을 줄이기
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t2000f2999.p2352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < n; i++) {
            arr[i + 1] = tmp[i];
        }
        System.out.println(Arrays.toString(arr));

    }

    static void LIS() {
        int[] dpTable = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j > 0; j--) {

            }
        }

    }

}
// 가지치기를 해야함
// 연결되는 것을 연결이라기 보다는 높이라고 생각하면 편함
// 1 -> 2 이면 1번의 높이를 2라고 생각하면 됨
