/**
 * 문제 이름 : 화학 실험
 * URL : https://www.acmicpc.net/problem/20311
 * 문제 설명 :
 * 화학 실험을 하던 윤이는 일렬로 나열해 좋은 N개의 시험관에서 재밌는 특징을 발견했다. 그 특징은 모든 이웃한 시험관 쌍에 대해, 두 시험관에
 * 들어 있는 시약의 색깔이 서로 다르다는 점이었다. 흥미롭다고 느낀 윤이는 실험보고서에 이 사실과 함께 각 색깔별 시약의 수를 적었다. 하지만
 * 보고서를 채점하던 조교 원이는 윤이가 색깔별 시약의 수를 제대로 적었는지 의문이 들었다. 윤이의 보고서와 일치하도록 시험관에 배열할 수 있는지 판별해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N, K;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (check()) {
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    static boolean check() {
        // 시약이 하나밖에 없을 경우에도 고려할 것
        // 여기서는 고려하지 말 것
        PriorityQueue<int[]> pq = makePQ();
        int preValue = -1;
        // 확인만 배열로 하고, 나중에는 SB로 바꾸기

        // 가장 많은 것
//        int[] mostAmount = pq.poll();
        int[] reagent;

        for (int i = 0; i < N; i++) {
            if (pq.isEmpty()) // return -1
                return false;
            reagent = pq.poll();
            if (i > 0 && preValue == reagent[0]) {
                if (pq.isEmpty())
                    return false;

                int[] reagent2 = pq.poll();
                pq.add(reagent);
                preValue = reagent2[0];
                sb.append(reagent2[0]).append(" ");
                if (reagent2[1] - 1 != 0) {
                    reagent2[1] -= 1;
                    pq.add(reagent2);
                }
                continue;
            }

            preValue = reagent[0];
            sb.append(reagent[0]).append(" ");
            if (reagent[1] - 1 != 0) {
                reagent[1] -= 1;
                pq.add(reagent);
            }
        }
        return true;
    }

    static PriorityQueue<int[]> makePQ() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            return o2[1] - o1[1];
        }));

        for (int i = 0; i < K; i++) {
            pq.add(new int[]{i + 1, arr[i]});
        }

        return pq;
    }
}

// logN = 19 보다 작은 수 => 넣고 빼는 MAX 연산은 20임
// 총 30만개이고, 최대는 15만개, 나머지는 다 1
// 150000 * 40 + 150000 * 20 = 9000000
// 그냥 그리디 그 이상 그 이하도 아닌 문제

/*
    방법 1. 계속해서 큰 것을 빼기 => 근데 이전꺼와 같으면 그 다음꺼 빼기 ==> 나는 이것으로 풀이 했음
    방법 2. 가장 큰 것을 기준으로 잡고, (가장 큰거, 뺀거, 가장 큰거, 뺀거, ...) 이 순서로 하기
           가장 큰 것이 0이 되면 그 다음으로 큰 것으로 대체
           이 방식이 더 빠를 것으로 예상됨
 */
