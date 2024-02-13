/**
 * 문제 이름 : 카드 게임
 * URL : https://www.acmicpc.net/problem/16566
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16566;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, K;
    static int[] cardNum;
    static int[] cardGame;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        M = tmpInput[1];
        K = tmpInput[2];

        cardNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        cardGame = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        parent = makeParent();

        check();
    }

    static void check() {
        StringBuilder sb = new StringBuilder();
        for (int card : cardGame) {
            int idx = binarySearch(card);
            int putCard = find(cardNum[idx]);
            sb.append(putCard).append("\n");
            if (idx + 1 >= M)
                continue;
            union(putCard, putCard + 1);
        }
        System.out.println(sb);
    }

    static int find(int idx) {
        /*
            find 코드
         */
        if (parent[idx] == idx) {
            // 부모를 찾으면 끝
            return idx;
        }
        int p = find(parent[idx]);
        parent[idx] = p;
        return p;
    }

    static void union(int node_1, int node_2) {
        /*
            union 코드
         */
        int node_1_parent = find(node_1);
        int node_2_parent = find(node_2);

        if (node_1_parent < node_2_parent) {
            parent[node_1_parent] = node_2_parent;
        } else {
            parent[node_2_parent] = node_1_parent;
        }
    }

    static int binarySearch(int num) {
        int start = 0;
        int end = M;

        while (true) {
            if (start >= end)
                break;

            int mid = (start + end) / 2;
            if (checkIdx(mid, num)) {
                // 가능한 경우 해당 위치를 가지고 있어야함
                // 줄여야함
                end = mid;
            } else {
                // 불가능할 경우 해당 위치는 가지고 있을 필요가 없음
                // 늘려야함
                start = mid + 1;
            }
        }
        return start;
    }

    static boolean checkIdx(int idx, int num) {
        /*
                해당 위치가 가능한지 알려주는 메소드
                가능하면 true
                불가능 하면 false

                가능하다
                => 인덱스에 해당하는 수보다 num이 더 작다

                불가능 하다
                => 인덱스에 해당하는 수보다 num이 더 크거나 같다
         */
        if (cardNum[idx] > num)
            return true;
        return false;
    }

    static int[] makeParent() {
        int[] makeArr = new int[N + 1];

        for (int i = 0; i < makeArr.length; i++) {
            makeArr[i] = i;
        }
        return makeArr;
    }
}

/*
    그냥 푸는 방법 => 완전 탐색을 해서 해당 카드보다 더 큰 선택이 안된 카드 고르기, 해당 카드는 중복해서 들어올 수 있음

    이분 탐색을 진행하기 전에 cardNum를 정렬해야함

    유니온 파인드와 이분 탐색을 동시에 사용하는 문제
    => 이분 탐색으로 들어갈 수 있는 위치를 찾기
    => 유니온 파인드로 이분 탐색으로 찾은 위치에서 가장 가깝게 들어갈 수 있는 곳이 어딘지 찾기
    => 유니온 파인드에서 union은 무조건 (자기 인덱스 + 1)와 하기
    => 유니온 파인드는 오름차순 유니온 파인드
 */