/**
 * 문제 이름 : IF문 좀 대신 써줘
 * URL : https://www.acmicpc.net/problem/19637
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t19000f19999.p19637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    // 칭호에 맞는 전투력을 담은 배열
    static int[] titlePowerArr;
    // 전투력에 맞는 칭호를 담은 배열
    static String[] titleArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        M = tmpInput[1];

        titlePowerArr = new int[N];
        titleArr = new String[N];
        for (int i = 0; i < N; i++) {
            // 같은 전투력의 칭호인지 확인할 필요가 없음
            // 문제에서 가장 먼저 나온것을 쓴다고 했음... 그럼 인덱스가 가장 작은 것임
            // 이분탐색을 할 때, 하한을 구하면 자연스럽게 가장 작은 것을 구해지게 됨
            String[] tmpInput2 = br.readLine().split(" ");
            titlePowerArr[i] = Integer.parseInt(tmpInput2[1]);
            titleArr[i] = tmpInput2[0];
        }

        for (int i = 0; i < M; i++) {
            check(Integer.parseInt(br.readLine()));
        }
        System.out.println(sb);
    }

    static void check(int power) {
        int titleIdx = binarySearch(power);
        sb.append(titleArr[titleIdx]).append("\n");
    }

    static int binarySearch(int power) {
        int start = 0;
        int end = N;

        while (true) {
            if (start >= end)
                break;

            int mid = (start + end) / 2;
            if (checkTitle(mid, power)) {
                // 현재 위치에서 칭호를 가질 수 있음 => 더 작을 때 가질 수 있는지 확인하기
                end = mid;
            } else {
                // 현재 위치에서 칭호를 가질 수 없음 => 입력으로 받은 전투력이 더 높으니 위치를 더 높여야함
                start = mid + 1;
            }
        }
        return start;
    }

    static boolean checkTitle(int mid, int power) {
        // 현재 위치에서 칭호를 가질 수 있으면 true
        // 없다면 false
        if (power <= titlePowerArr[mid]) {
            return true;
        }
        return false;
    }
}

/*
        완전 탐색으로 하면 무조건 시간 초과
        => O(N) * O(N) => 100억임 (N의 최대값 10만)

        핵심은 분기가 바로 되어야함
        => 값을 어떤 함수에 넣으면 해당 값의 칭호가 바로 나와야함

        음...! 이분 탐색(매개변수 탐색)ㄱㄱ
        => 시간초과 뜸...!

        로직에 문제는 없고, 입출력에 문제가 있는 것 같음... StringBuilder 사용
 */