/**
 * 문제 이름 : 최소 힙
 * URL : https://www.acmicpc.net/problem/1927
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            check(Integer.parseInt(br.readLine()));
        }
        System.out.println(sb);
    }

    static void check(int num) {
        if (num == 0) {
            if (minHeap.isEmpty())
                sb.append(0).append("\n");
            else
                sb.append(minHeap.poll()).append("\n");
        }
        else
            minHeap.add(num);
    }
}
