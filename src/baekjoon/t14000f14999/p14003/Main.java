/**
 * 문제 이름 : 가장 긴 증가하는 부분 수열5
 * URL : https://www.acmicpc.net/problem/14003
 * 문제 설명 :
 * 가장 긴 증가하는 부분 수열을 구하면 된다.
 * N = 1,000,000이다
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] sequence;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LIS();
    }

    static void LIS() {
        int[] dpTable = new int[N];
        Arrays.fill(dpTable, 1);
        dpTable[0] = 1;

        int[] check = new int[N];
        check[0] = sequence[0];

        System.out.println(Arrays.toString(dpTable));
    }

    static int binarySearch(int start, int end, int target, int[] check) {
        while (true) {
            if (start >= end)
                break;

            int mid = (start + end) / 2;

            if (check[mid] >= target) {
                end = mid;
            } else if (check[mid] < target) {
                start = mid + 1;
            }
        }
        return start;
    }
}

// 나중에 다시 풀기