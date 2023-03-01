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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println(LIS());

    }

    static int LIS() {
        int[] dpTable = new int[n + 1];
        dpTable[1] = 1;

        int result = dpTable[1];

        List<Integer> check = new ArrayList<>();
        check.add(arr[1]);

        for (int i = 2; i <= n; i++) {
            boolean change = false;

            for (int j = 0; j < check.size(); j++) {
                if (check.get(j) > arr[i]) {
                    check.set(j, arr[i]);
                    dpTable[i] = j + 1;
                    change = true;
                    break;
                }
            }

            if (!change) {
                check.add(arr[i]);
                dpTable[i] = check.size();
            }
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }
}

// 가지치기를 해야함
// 연결되는 것을 연결이라기 보다는 높이라고 생각하면 편함
// 1 -> 2 이면 1번의 높이를 2라고 생각하면 됨
