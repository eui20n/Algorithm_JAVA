/**
 * 문제 이름 : 최소 스패닝 트리
 * 문제 번호 : 3124
 * 문제 설명 : 그래프가 주어졌을 때, 최소 스패닝 트리를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t3000f3999.p3124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int V, E;

            String[] tmp = br.readLine().split(" ");
            V = Integer.parseInt(tmp[0]);
            E = Integer.parseInt(tmp[1]);

            int[][] edgeArr = new int[E][3];
            int[] parent = new int[V];
            for (int j = 1; j < V; j++) {
                parent[j] = j;
            }

            for (int j = 0; j < E; j++) {
                String[] tmp2 = br.readLine().split(" ");
                int node1 = Integer.parseInt(tmp2[0]);
                int node2 = Integer.parseInt(tmp2[1]);
                int cost = Integer.parseInt(tmp2[2]);

                edgeArr[j][0] = node1 - 1;
                edgeArr[j][1] = node2 - 1;
                edgeArr[j][2] = cost;
            }
            System.out.printf("#%d %d %n", i, MST(parent, edgeArr, V));
        }
    }

    static int find(int[] parent, int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent, parent[node]);
    }

    static boolean union(int[] parent, int node1, int node2) {
        int node1Parent = find(parent, node1);
        int node2Parent = find(parent, node2);

        if (node1Parent == node2Parent)
            return false;

        if (node1Parent > node2Parent) {
            parent[node1Parent] = node2Parent;
        } else {
            parent[node2Parent] = node1Parent;
        }
        return true;
    }

    static long MST(int[] parent, int[][] edgeArr, int V) {
        Arrays.sort(edgeArr, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        long result = 0;
        int vCnt = 0;

        for (int[] edge : edgeArr) {
            if (vCnt == V - 1)
                return result;

            int node1 = edge[0];
            int node2 = edge[1];
            int cost = edge[2];

            if (union(parent, node1, node2)) {
                result += cost;
                vCnt += 1;
            }
        }
        return result;
    }
}
