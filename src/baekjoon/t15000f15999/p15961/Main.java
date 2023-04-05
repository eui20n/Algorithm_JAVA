/**
 * 문제 이름 : 회전 초밥
 * URL : https://www.acmicpc.net/problem/15961
 * 문제 설명 :
 * 회전 초밥집에서 가장 다양하게 초밥을 먹으면 몇 종류를 먹을 수 있는지 구해라
 * 초밥은 연속되어야 한다. 한 종류에 대해서 추가로 주문할 수 있다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t15000f15999.p15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int N, D, K; // 벨트에 놓인 접시, 초밥의 가짓수, 연속해서 먹는 접시 수
    static boolean[] coupon = new boolean[3001];
    static int couponCnt = 0;
    static int[] sushi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = tmp[0];
        D = tmp[1];
        K = tmp[2];

        for (int i = 3; i < tmp.length; i++) {
            coupon[tmp[i]] = true;
            couponCnt += 1;
        }

        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(check());
    }

    static int check() {
        int result = 0;
        int answer = 0;
        boolean[] visited = new boolean[3001];

        // 처음 덱
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int idx = sushi[i];

            if (!visited[idx] && !coupon[idx])
                result += 1;

            deque.add(idx);
            visited[idx] = true;
        }
        result += couponCnt;

        for (int i = 1; i < N; i++) {
            int startIdx = i - 1;
            int endIdx = i + K;

            int delete = deque.pollFirst();
            if (!coupon[delete])
                result -= 1;

            if (!visited[sushi[endIdx]])
                result += 1;
            deque.addLast(sushi[endIdx]);
            answer = Math.max(answer, result);
        }
        return answer;
    }

    static Deque<Integer> makeDeque() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : sushi) {
            deque.add(num);
        }
        return deque;
    }
}
// coupon배열을 boolean으로 해서 관리할 지 생각하기
// 해당 쿠폰이 있는지 없는지 1의 복잡도로 관리 가능하고, 쿠폰이 몇개인지만 체크해서 먹을 수 있는 초밥에서 쿠폰을 사용한 만큼을 제외하고
// 추가 시키면 될 듯
// 슬라이딩 윈도우를 이용하면 됨
// 처음에만 처음부터 K까지 봐주고, 나머지는 앞에서 하나 빼고 뒤에서 하나 넣는 식으로 해서 확인해주면 될 듯

/*
        구현 순서
    1. 덱의 초기 상태는 제일 처음 회전 초밥의 경우
    2. 1부터 가면서 앞에서 빼고 뒤에서 넣기
    3. 위 과정을 하면서 카운트 해주기

        카운트 생각
    1. 현재 쿠폰이 있는 종류의 초밥은 넣어도 먹을 수 있는 종류는 증가시키지 않음
    2. 이미 넣은 것에 대해서는 넣어도 종류는 증가시키지 않음
    3. 나머지 경우는 증가 시킴
    4. 다 증가 시킨 후 쿠폰의 개수 만큼 더해주기
 */


// 다듬기