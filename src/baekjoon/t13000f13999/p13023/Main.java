/**
 * 문제 이름 : ABCDE
 * URL : https://www.acmicpc.net/problem/13023
 * 문제 설명 : A는 B와 친구이다
 * B는 C와 친구이다.
 * C는 D와 친구이다.
 * D는 E와 친구이다.
 * 이 관계가 있으면 1, 없으면 0을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 깊이가 4인 것이 있는지 확인하면 됨, 또한 방향이 없음
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t13000f13999.p13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N, M;
    // 친구의 관계를 리스트를 이용해서 관리
    static List<List<Integer>> relationship = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        for (int i = 0; i < N; i++) {
            relationship.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] tmp2 = br.readLine().split(" ");
            int friend1 = Integer.parseInt(tmp2[0]);
            int friend2 = Integer.parseInt(tmp2[1]);

            relationship.get(friend1).add(friend2);
            relationship.get(friend2).add(friend1);
        }

        check();
        System.out.println(result);
    }

    /**
     * 정답을 구해주는 메소드
     * */
    static void check() {
        for (int i = 0; i < N; i++) {
            // 문제에서 원하는 조건이 있으면 반복 종료
            if (result == 1)
                break;
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, visited, 0);
        }
    }

    /**
     * 백트래킹을 할 메소드
     * idx : 현재의 방문하고 있는 노드(정점)
     * visited : 방문처리 배열
     * depth : 깊이
     * */
    static void dfs(int idx, boolean[] visited, int depth) {
        // 깊이가 4라는 의미는 문제에서 원하는 조건이라는 의미
        if (depth == 4) {
            result = 1;
            return;
        }

        for (int friend : relationship.get(idx)) {
            if (visited[friend])
                continue;
            // 그냥 백트래킹
            visited[friend] = true;
            dfs(friend, visited, depth + 1);
            visited[friend] = false;
        }
    }
}