/**
 * 문제 이름 : 숨바꼭질
 * URL : https://www.acmicpc.net/problem/1697
 * 문제 설명 : 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이의 현재 점 N에 있고, 동생은 K에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 * 걷는 경우 현재 위치에서 +1 or -1을 한다. 순간이동 하는 경우 현재 위치 X에서 2 * X의 위치로 이동한다.
 * 수빈이와 동생의 위치가 주어졌을 때, 동생을 찾는 최소 시간을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1697;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        System.out.println(find(N, K));
    }

    static int find(int n, int k) {
        // 최대의 범위가 100000이라서 100001로 설정
        boolean[] visited = new boolean[100001];
        visited[n] = true;
        Queue<int[]> q = new ArrayDeque<>();
        // 현재 위치, 시간
        q.add(new int[]{n, 0});

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int time = tmp[1];
            // 만약에 본인의 위치가 동생의 위치와 같으면 종료
            if (x == k)
                return time;

            // +1로 걷는 경우
            if (0 <= x + 1 && x + 1 < 100001 && !visited[x + 1]) {
                visited[x + 1] = true;
                q.add(new int[]{x + 1, time + 1});
            }
            // -1로 걷는 경우
            if (0 <= x - 1 && x - 1 < 100001 && !visited[x - 1]) {
                visited[x - 1] = true;
                q.add(new int[]{x - 1, time + 1});
            }
            // 순간이동 하는 경우
            if (0 <= x * 2 && x * 2 < 100001 && !visited[x * 2]) {
                visited[x * 2] = true;
                q.add(new int[]{x * 2, time + 1});
            }
        }
        // 정말로 만약에 찾지 못하면 -1 출력
        return -1;
    }
}
