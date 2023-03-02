/**
 * 문제 이름 : 최단경로
 * URL : https://www.acmicpc.net/problem/1753
 * 문제 설명 : 방향그래프가 주어질때, 최단 경로를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int V, E;
    static int start;
    static List<List<int[]>> graph = new ArrayList<>();
    static int[] distance;
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        V = Integer.parseInt(tmp[0]);
        E = Integer.parseInt(tmp[1]);
        start = Integer.parseInt(br.readLine()) - 1;

        distance = new int[V];

        for (int i = 0; i < V; i++) {
            distance[i] = INF;
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            String[] tmp2 = br.readLine().split(" ");
            int node1 = Integer.parseInt(tmp2[0]) - 1;
            int node2 = Integer.parseInt(tmp2[1]) - 1;
            int cost = Integer.parseInt(tmp2[2]);

            graph.get(node1).add(new int[]{node2, cost});
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int dist : distance) {
            sb.append(dist == INF ? "INF" : dist).append("\n");
        }
        System.out.println(sb);
    }

    static void dijkstra() {
        // 가중치를 기준으로 우선 순위 큐 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        // 초기에는 시작 점과 가중치 0을 넣어줌
        pq.add(new int[] {start, 0});

        distance[start] = 0;
        boolean[] visited = new boolean[V];

        while (true) {
            if (pq.isEmpty())
                break;
            int[] tmp = pq.poll();
            int node = tmp[0];
            int cost = tmp[1];

            if (visited[node]) // 해당 노드에 방문했으면 안해줌
                continue;
            visited[node] = true;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int nextCost = next[1];

                if (visited[nextNode])
                    continue;
                if (distance[nextNode] <= nextCost + cost)
                    // 가중치가 경유해서 가는 경우보다 작으면 넘어감
                    continue;
                // 가중치가 경유해서 가는 경우보다 더 크기때문에 경유해서 가는 것으로 변경해줌
                distance[nextNode] = nextCost + cost;
                pq.add(new int[] {nextNode, distance[nextNode]});
            }
        }
    }
}
