/**
 * 문제 이름 : DFS와 BFS
 * URL : https://www.acmicpc.net/problem/1260
 * 문제 설명 : DFS와 BFS로 탐색한 결과를 출력
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int V, E, start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        V = Integer.parseInt(tmp[0]);
        E = Integer.parseInt(tmp[1]);
        start = Integer.parseInt(tmp[2]);

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            String[] tmp2 = br.readLine().split(" ");
            int vertex1 = Integer.parseInt(tmp2[0]);
            int vertex2 = Integer.parseInt(tmp2[1]);

            list.get(vertex1).add(vertex2);
            list.get(vertex2).add(vertex1);
        }

        dfs(list, start, new boolean[V + 1]);
        System.out.println();
        bfs(list, start, new boolean[V + 1]);

    }

    static void dfs(List<List<Integer>> list, int idx, boolean[] visited) {
        System.out.print(idx + " ");
        visited[idx] = true;
        Collections.sort(list.get(idx));
        for (int vertex : list.get(idx)) {
            if (visited[vertex])
                continue;
            dfs(list, vertex, visited);
        }
    }

    static void bfs(List<List<Integer>> list, int start, boolean[] visited) {
        visited[start] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (true) {
            if (q.isEmpty())
                break;
            int next = q.poll();
            System.out.print(next + " ");
            Collections.sort(list.get(next));

            for (int vertex : list.get(next)) {
                if (visited[vertex])
                    continue;
                q.add(vertex);
                visited[vertex] = true;
            }
        }
    }
}

// 정리
// bfs와 dfs한 결과를 출력해라
// 갈 수 있는 노드가 여러개면 작은 것 먼저 가라 => 정렬을 통해서 작은 것을 먼저 선택하도록 설정