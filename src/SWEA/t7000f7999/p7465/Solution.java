/**
 * 문제 이름 : 창용 마을 무리의 개수
 * 문제 번호 : 7465
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t7000f7999.p7465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");
            int N = Integer.parseInt(tmp[0]);
            int M = Integer.parseInt(tmp[1]);

            Integer[] parent = makeParent(N);

            for (int j = 0; j < M; j++) {
                String[] tmp2 = br.readLine().split(" ");
                int node1 = Integer.parseInt(tmp2[0]);
                int node2 = Integer.parseInt(tmp2[1]);

                union(parent, node1, node2);
            }

            for (int j = 1; j < parent.length; j++) {
                find(parent, j);
            }

            Set<Integer> set = new HashSet<>(Arrays.asList(parent));
            sb.append("#").append(i).append(" ").append(set.size() - 1).append("\n");
        }
        System.out.println(sb);
    }

    static Integer[] makeParent(int n) {
        Integer[] arr = new Integer[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    static int find(Integer[] parent, int node) {
        if (parent[node] == node) return node;
        int p = find(parent, parent[node]);
        parent[node] = p;
        return p;
    }

    static void union(Integer[] parent, int node1, int node2) {
        int a = find(parent, node1);
        int b = find(parent, node2);

        if (a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }
}
