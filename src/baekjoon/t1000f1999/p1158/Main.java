/**
 * 문제 이름 : 요세푸스 문제
 * URL : https://www.acmicpc.net/problem/1158
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1158;

import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        check();
    }

    static void check() {
        Queue<Integer> q = new ArrayDeque<>();

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        while (true) {
            if (q.isEmpty()) {
                break;
            }

            for (int i = 1; i <= M; i++) {
                int qNum = q.poll();

                if (i == M) {
                    result.add(qNum);
                    continue;
                }
                q.add(qNum);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (Integer num : result){
            sb.append(num);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(">");
        System.out.println(sb);
    }
}

