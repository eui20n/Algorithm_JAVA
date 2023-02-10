/**
 * 문제 이름 : 핑크 플로이드
 * URL : https://www.acmicpc.net/problem/6091
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t6000f6999.p6091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static int[][] arr;
    static int[] parent;
    static Edge[] edgeList;
    static List<List<Integer>> result;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        int e = 0;
        List<int[]> info = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            int[] inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < inputArr.length; j++) {
                info.add(new int[] { i, i + j + 1, inputArr[j] });
                e += 1;
            }
        }

        edgeList = new Edge[e];
        parent = new int[N + 1];
        initParent();

        for (int i = 0; i < e; i++) {
            int[] tmp = info.get(i);
            int x = tmp[0];
            int y = tmp[1];
            int cost = tmp[2];

            edgeList[i] = new Edge(x, y, cost);
        }

        Arrays.sort(edgeList);

        result = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            result.add(new ArrayList<>());
        }

        MST();

        StringBuilder sb = new StringBuilder();

        for (List<Integer> data1 : result) {
            int dataSize = data1.size();
            if (dataSize == 0)
                continue;

            Collections.sort(data1);
            sb.append(data1.size());
            sb.append(" ");

            for (int node : data1) {
                sb.append(node);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int find(int x) {
        if (parent[x] == x)
            return x;
        int p = find(parent[x]);
        parent[p] = p;
        return p;
    }

    static void union(int node1, int node2) {
        int a = find(node1);
        int b = find(node2);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

    }

    static void MST() {
        for (int i = 0; i < edgeList.length; i++) {
            Edge info = edgeList[i];
            int node1 = info.to;
            int node2 = info.from;
            int cost = info.weight;

            if (find(node1) != find(node2)) {
                result.get(node1).add(node2);
                result.get(node2).add(node1);
                union(node1, node2);
            }
        }
    }

    static void initParent() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

}
