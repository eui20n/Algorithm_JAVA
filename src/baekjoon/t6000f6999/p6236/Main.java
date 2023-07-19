/**
 * 문제 이름 : 용돈 관리
 * URL : https://www.acmicpc.net/problem/6236
 * 문제 설명 :
 * 돈을 효율적으로 활용하기 위해 N일 동안 사용할 금액을 계산함
 * 돈은 M번만 통장에서 빼서 쓰기로함
 * 통장에서 K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어 넣고 다시 K원을 인출함
 * 돈을 정확히 M번 빼기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어 넣고 다시 K원을 인출할 때,
 * 필요한 금액 K의 최소값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t6000f6999.p6236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        check();
    }

    static void check() {
        binarySearch(0, 1000000001);
    }

    static void binarySearch(int start, int end) {
        while (true) {
            if (start >= end)
                break;

            int mid = (start + end) / 2;
            boolean available = valid(mid);

            if (available) {
                // 가능하면 범위를 줄이기
                end = mid;
            } else {
                // 불가능 하면 범위를 늘리기
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    static boolean valid(int num) {
        // 가능하지 체크하면 됨
        int idx = 0;
        int cnt = 0;
        int money = num;

        while (true) {
            if (idx == N) {
                if (cnt >= M)
                    return false;
                return true;
            }
            if (arr[idx] > num)
                return false;
            if (cnt >= M)
                return false;

            // 돈을 인출해야 하는 경우
            // 현재 가진 돈으로 생활을 할 수 없을 때
            if (arr[idx] > money) {
                money = num;
                cnt += 1;
            }
            money -= arr[idx];
            idx += 1;
        }
    }
}

// 파라메틱 서치 말고는 떠오르는게 없군....