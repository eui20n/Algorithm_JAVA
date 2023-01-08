/**
 * 문제 이름 : 카드2
 * URL : https://www.acmicpc.net/problem/2164
 * 문제 설명 : N장의 카드가 있을 때, 가장 마지막에 남는 카드가 뭔지 출력하기
 * 제일 위의 카드를 뺀 후, 그 다음 제일 위에 있는 카드를 제일 아래로 옮긴다
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 큐 연습
 * 소요 시간 : 1초
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        check();
    }

    static void check() {
        Queue<Integer> card = new LinkedList<Integer>();
        int newCard;

        for (int i = 1; i <= N; i++) {
            card.add(i);
        }
        while (card.size() != 1){
            card.poll();
            newCard = card.poll();
            card.add(newCard);
        }

        System.out.println(card.peek());
    }
}
