/**
 * 문제 이름 : List of unique Numbers
 * URL : https://www.acmicpc.net/problem/13144
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t13000f13999.p13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        check();
    }

    static void check() {
        boolean[] visited = new boolean[100_000 + 1];
        long result = 0;
        Deque<int[]> q = new ArrayDeque<>(); // 현재 인덱스 값, 그 값의 위치

        for (int i = 0; i < N; i++) {
            int idx = arr[i];

            if (visited[idx]) {
                // 이미 해당 수가 있어서, 해당 수가 나올때까지 빼야함
                while (true) {
                    if (q.isEmpty() || !visited[idx])
                        break;
                    int[] pollValue = q.pollFirst();
                    visited[pollValue[0]] = false;
                    result += i - pollValue[1];
                }
            }

            // 뺏다면 현재 값을 넣어야함
            q.add(new int[]{idx, i});
            visited[idx] = true;
        }

        while (true) {
            // 위 반복이 끝났다면, Q 비워주기
            if (q.isEmpty())
                break;
            int[] pollValue = q.pollFirst();
            result += N - pollValue[1];
        }

        System.out.println(result);
    }
}

/*
        큐를 이용한 풀이
        1. 먼저 큐에다가 값을 넣어주고, 방문처리를 해줌
        2. 방문 처리가 된 것이 들어온다면, 그 수가 나올때 까지 빼줌
        3. 뺄 때, 그 수가 들어갔던 위치에서 현재 위치를 빼줌 => 이 만큼이 그 위치에서 만들 수 있는 수의 최대 개수임
        4. 위 과정을 반복문이 끝날 때까지 반복
        5. 위 과정이 끝나면 큐에 남아 있는 것이 존재하는데, 이는 마지막으로 3번 과정을 해주면 됨
 */