/**
 * 문제 이름 : 절대값 힙
 * URL : https://www.acmicpc.net/problem/11286
 * 문제 설명 : 절대값 힙은 다음과 같은 연산을 지원하는 자료구조
 * 1. 배열에 정수 x를 넣음
 * 2. 배열에서 절대값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거. 절대값이 가장 작은 값이 여러개면 가장 작은 수를 제거
 * 입력 값이 0이 아니면 배열에 추가, 0이면 배열에서 빼고 출력
 * 배열이 비어있으면 0 출력
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t11000f11999.p11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int num1 = Math.abs(o1);
            int num2 = Math.abs(o2);

            if (num1 == num2) {
                if (o1 >= o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return num1 - num2;
        });

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) {
                pq.add(num);
            } else {
                Integer value = pq.poll();
                sb.append(value == null ? 0 : value).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
