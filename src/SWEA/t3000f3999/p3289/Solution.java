/**
 * 문제 이름 : 서로소 집합
 * 문제 번호 : 3289
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t3000f3999.p3289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            String[] tmp = br.readLine().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int m = Integer.parseInt(tmp[1]);
            int[] parent = makeParent(n);

            for (int j = 0; j < m; j++) {
                String[] tmp2 = br.readLine().split(" ");
                int command = Integer.parseInt(tmp2[0]);
                int node1 = Integer.parseInt(tmp2[1]);
                int node2 = Integer.parseInt(tmp2[2]);

                if (command == 0) {
                    union(parent, node1, node2);
                } else if (command == 1) {
                    int node1Parent = find(parent, node1);
                    int node2Parent = find(parent, node2);

                    if (node1Parent == node2Parent)
                        sb.append(1);
                    else
                        sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] makeParent(int n) {
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    static int find(int[] parent, int node) {
        if (parent[node] == node) return node;
        int p = find(parent, parent[node]);
        parent[node] = p;
        return p;
    }

    static void union(int[] parent, int node1, int node2) {
        int a = find(parent, node1);
        int b = find(parent, node2);

        if (a > b)
            parent[a] = b;
        else
            parent[b] = a;

    }
}
