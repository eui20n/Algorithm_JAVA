/**
 * 문제 이름 : 다리 만들기 2
 * URL : https://www.acmicpc.net/problem/17472
 * 문제 설명 : 섬으로 이루어진 나라를 다리로 연결하려고 함
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 각각의 섬을 노드라고 생각하고, 거리를 구한 후 MST를 하면 됨
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] land;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        land = new int[R][C];

        for (int i = 0; i < R; i++) {
            land[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        bridge();
    }

    static void bridge() {
        int[][] searchLand = searchLand();
        List<int[]> findNode = findNode(searchLand);
        int result = MST(findNode);
        System.out.println(result);
    }

    static int MST(List<int[]> nodeInfoes) {
        int result = 0;
        for (int[] nodeInfo : nodeInfoes){
            int cost = nodeInfo[0];
            int node1 = nodeInfo[1];
            int node2 = nodeInfo[2];

            if (find(node1) == find(node2))
                continue;

            union(node1, node2);
            result += cost;
        }

        int check = find(parent[1]);
        for (int i = 2; i < parent.length; i++){
            if (check != find(parent[i]))
                return -1;
        }
        return result;
    }

    static List<int[]> findNode(int[][] arr) {
        List<int[]> nodeInfoes = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 0)
                    continue;

                for (int z = 0; z < 4; z++) {
                    int[] nodeInfo = dist(arr, i, j, z, 0, arr[i][j]);
                    // null 체크 해서 리스트에 넣기
                    if (nodeInfo == null)
                        continue;
                    if (nodeInfo[0] < 2)
                        continue;
                    nodeInfoes.add(nodeInfo);
                }
            }
        }

        Collections.sort(nodeInfoes, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        return nodeInfoes;
    }

    static int[] dist(int[][] arr, int x, int y, int z, int dist, int node) {
        int nx = x + dx[z];
        int ny = y + dy[z];

        if (0 > nx || nx >= R)
            // 범위를 벗어나는 경우
            return null;
        if (0 > ny || ny >= C)
            // 범위를 벗어나는 경우
            return null;
        if (node == arr[nx][ny])
            // 현재 노드와 같은 경우
            return null;
        if (arr[nx][ny] == 0)
            // 바다인 경우
            return dist(arr, nx, ny, z, dist + 1, node);
        if (node != arr[nx][ny])
            // 다른 섬을 만난 경우
            return new int[]{dist, node, arr[nx][ny]};
        return null;
    }

    static int[][] searchLand() {
        boolean[][] visited = new boolean[R][C]; // 재활용이 가능한지 생각해보기, 재활용 할꺼면 전역으로 빼기
        int[][] newArr = new int[R][C];
        int mark = 1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j])
                    continue;
                if (land[i][j] == 0)
                    continue;
                search(newArr, visited, i, j, mark++);
            }
        }

        parent = new int[mark];
        for (int i = 1; i < mark; i++) {
            parent[i] = i;
        }

        return newArr;
    }

    static void search(int[][] arr, boolean[][] visited, int x, int y, int mark) {
        visited[x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        arr[x][y] = mark;

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            x = tmp[0];
            y = tmp[1];

            for (int z = 0; z < 4; z++) {
                int nx = x + dx[z];
                int ny = y + dy[z];
                if (0 > nx || nx >= R)
                    continue;
                if (0 > ny || ny >= C)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (land[nx][ny] == 0)
                    continue;
                arr[nx][ny] = mark;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }

    static int find(int node) {
        if (parent[node] == node) return node;
        int p = find(parent[node]);
        parent[node] = p;
        return p;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b)
            parent[a] = parent[b];
        else
            parent[b] = parent[a];
    }

    static void print(int[][] arr) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}