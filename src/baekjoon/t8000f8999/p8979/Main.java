/**
 * 문제 이름 : 올림픽
 * URL : https://www.acmicpc.net/problem/8979
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t8000f8999.p8979;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N, K;
    static int[][] rankArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmpInput1 = br.readLine().split(" ");
        N = Integer.parseInt(tmpInput1[0]);
        K = Integer.parseInt(tmpInput1[1]);

        rankArr = new int[N][4];

        for (int i = 0; i < N; i++) {
            rankArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();
    }

    static void check() {
        int[] result = new int[N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                // 금메달이 같은 경우
                if (o1[2] == o2[2]) {
                    // 은메달이 같은 경우
                    return o2[3] - o1[3];
                }
                return o2[2] - o1[2];
            }
            return o2[1] - o1[1];
        });

        for (int[] rank : rankArr) {
            pq.add(rank);
        }

        int loopSize = pq.size();
        int ranking = 0;
        int diff = 1;
        int gold = -1;
        int silver = -1;
        int bronze = -1;

        for (int i = 0; i < loopSize; i++) {
            int[] pollArr = pq.poll();
            int index = pollArr[0] - 1;

            if (gold == pollArr[1] && silver == pollArr[2] && bronze == pollArr[3]) {
                diff += 1;
                result[index] = ranking;
            } else {
                ranking += diff;
                result[index] = ranking;
                diff = 1;

                gold = pollArr[1];
                silver = pollArr[2];
                bronze = pollArr[3];
            }
        }
        System.out.println(result[K - 1]);
//        System.out.println(Arrays.toString(result));
    }
}

/*
        로직
    1. 우선 순위 큐로 순위를 정함
    2. 그럼 공통된 것은 알 수 없기 때문에, 우선 순위 큐로 정해진 것을 다시 한번 더 풀어서 배열에 값을 저장
    3. 배열에 저장된 값을 출력
 */