/**
 * 문제 이름 : 탑 보기
 * URL : https://www.acmicpc.net/problem/22866
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t22000f22999.p22866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] topArr;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    static int[] closeTop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        topArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[N];
        closeTop = new int[N];
        check();
    }

    static void check() {
        right();
        left();

        for (int i = 0; i < N; i++) {
            if (result[i] == 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(result[i]).append(" ").append(closeTop[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    static void right() {
        int[] lookTop = new int[N]; // 내가 보고 있는 가장 가까이에 있는 탑 번호를 기록(인덱스 + 1)
        int[] countArr = new int[N];

        for (int i = 0; i < N; i++) {
            int standard = topArr[i];

            for (int j = i; j < N; j++) {
                int comparison = topArr[j];

                if (standard < comparison) {
                    lookTop[i] = j + 1;
                    break;
                }
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            int idx = lookTop[i] - 1;

            if (idx < 0) {
                continue;
            }

            closeTop[i] = lookTop[i];
            countArr[i] = countArr[idx] + 1;
        }

        sumResult(countArr);
    }
    static void left() {
        int[] lookTop = new int[N]; // 내가 보고 있는 가장 가까이에 있는 탑 번호를 기록(인덱스 + 1)
        int[] countArr = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            int standard = topArr[i];

            for (int j = i; j >= 0; j--) {
                int comparison = topArr[j];

                if (standard < comparison) {
                    lookTop[i] = j + 1;
                    break;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            int idx = lookTop[i] - 1;

            if (idx < 0) {
                continue;
            }

            if (Math.abs(closeTop[i] - i - 1) > Math.abs(lookTop[i] - i - 1)) {
                closeTop[i] = lookTop[i];
            } else if (Math.abs(closeTop[i] - i - 1) == Math.abs(lookTop[i] - i - 1)) {
                closeTop[i] = Math.min(closeTop[i], lookTop[i]);
            }
            countArr[i] = countArr[idx] + 1;
        }

        sumResult(countArr);
    }

    static void sumResult(int[] arr) {
        for (int i = 0; i < N; i++) {
            result[i] += arr[i];
        }
    }
}

/*
    생각의 흐름
    1. 이전에 것을 인덱스로 저장해서 사용하자(리스트 사용) => 시간초과
    2. 오른쪽, 왼쪽 총 2번의 연산을 한 번에 하자! => 시간초과... 한 번에 하니까 O(N^2)인 경우가 생김
    3. 1번에서 인덱스를 리스트에 넣는 것이 아니라, 가장 처음 보는 탑의 인덱스만 저장해서 이를 활용해서 갯수를 세자! => 정답

    리팩토링
    - 기존에 가장 가까운 탑을 구하는 로직이 내가 바라보는 탑을 구할 때, 같이 수행했음
    -- 이 부분을 따로 수행하고, 여러개의 배열로 하던 것을 1개의 배열로 처리함
    --- 이 결과는 메모리와 시간 복잡도가 조금 줄어들었고, 코드의 가독성이 올라감
 */