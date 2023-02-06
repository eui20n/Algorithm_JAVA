/**
 * 문제 이름 : 바이러스
 * URL : https://www.acmicpc.net/problem/2606
 * 문제 설명 : 1번 컴퓨터에서 감염이 됬을 때, 총 몇개의 컴퓨터가 감염이 되는지 출력을 해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N;
    static int pair;
    static List<List<Integer>> computer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pair = Integer.parseInt(br.readLine());

        for (int i = 0; i < N + 1; i++) {
            computer.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < pair; i++) {
            String[] tmp = br.readLine().split(" ");
            int com1 = Integer.parseInt(tmp[0]);
            int com2 = Integer.parseInt(tmp[1]);

            computer.get(com1).add(com2);
            computer.get(com2).add(com1);

        }

        System.out.println(virus());
    }

    static int virus() {
        Queue<List<Integer>> q = new LinkedList<>();

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        q.add(computer.get(1));
        int result = 0;

        while (true) {
            if (q.isEmpty()) {
                break;
            }
            List<Integer> p = q.poll();

            for (Integer comNum : p) {
                if (visited[comNum])
                    continue;
                visited[comNum] = true;
                q.add(computer.get(comNum));
                result++;
            }

        }
        return result;

    }
}
