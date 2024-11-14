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
    static int[] distanceTop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        topArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[N];

        closeTop = new int[N];
        distanceTop = new int[N];
        for (int i = 0; i < N; i++) {
            distanceTop[i] = 1000001;

        }

        check();
    }

    static void check() {
        right();
        left();

        for (int i = 0; i < N; i++) {
            if (result[i] == 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(result[i]).append(" ").append(closeTop[i] + 1).append("\n");
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
                    if (Math.abs(i - j) < distanceTop[i]) {
                        // 지금 보는 탑이 더 가까운 경우
                        distanceTop[i] = Math.abs(i - j);
                        closeTop[i] = j;
                    } else if (Math.abs(i - j) == distanceTop[i]) {
                        closeTop[i] = Math.min(j, closeTop[i]);
                    }
                    break;
                }
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            int idx = lookTop[i] - 1;

            if (idx < 0) {
                continue;
            }

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
                    if (Math.abs(i - j) < distanceTop[i]) {
                        // 지금 보는 탑이 더 가까운 경우
                        distanceTop[i] = Math.abs(i - j);
                        closeTop[i] = j;
                    } else if (Math.abs(i - j) == distanceTop[i]) {
                        closeTop[i] = Math.min(j, closeTop[i]);
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            int idx = lookTop[i] - 1;

            if (idx < 0) {
                continue;
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
 */