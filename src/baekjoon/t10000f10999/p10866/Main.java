package baekjoon.t10000f10999.p10866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static Deque<Integer> deque = new ArrayDeque<Integer>();

    static String pushFront = "push_front"; // 정수 X를 젝의 앞에 넣기
    static String pushBack = "push_back"; // 정수 X를 덱의 뒤에 넣기
    static String popFront = "pop_front"; // 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력, 없다면 -1 출력
    static String popBack = "pop_back"; // 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력, 없다면 -1 출력
    static String size = "size"; // 덱에 들어있는 정수의 개수를 출력
    static String empty = "empty"; // 덱이 비어있으면 1, 아니면 0 출력
    static String front = "front"; // 덱의 가장 앞 정수 출력, 없다면 -1
    static String back = "back"; // 덱의 가장 뒤 정수 출력, 없다면 -1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] tmp = br.readLine().split(" ");

            int dequeSize = deque.size();

            if (tmp[0].equals(pushFront)) {
                deque.addFirst(Integer.parseInt(tmp[1]));
                continue;
            }
            if (tmp[0].equals(pushBack)) {
                deque.addLast(Integer.parseInt(tmp[1]));
                continue;
            }
            if (tmp[0].equals(popFront)) {
                if (dequeSize == 0) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(deque.pollFirst());
            }
            if (tmp[0].equals(popBack)) {
                if (dequeSize == 0) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(deque.pollLast());
                continue;
            }
            if (tmp[0].equals(size)) {
                System.out.println(dequeSize);
                continue;
            }
            if (tmp[0].equals(empty)) {
                if (dequeSize == 0) {
                    System.out.println(1);
                    continue;
                }
                System.out.println(0);
                continue;
            }
            if (tmp[0].equals(front)) {
                if (dequeSize == 0) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(deque.peekFirst());
                continue;
            }
            if (tmp[0].equals(back)) {
                if (dequeSize == 0) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(deque.peekLast());
                continue;
            }
        }
    }
}
