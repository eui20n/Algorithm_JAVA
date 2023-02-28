/**
 * 문제 이름 : Contact
 * 문제 번호 : 1238
 * 문제 설명 : 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때, 가장 나붕에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : BFS
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {
            String[] tmp = br.readLine().split(" ");
            int length = Integer.parseInt(tmp[0]);
            int start = Integer.parseInt(tmp[1]) - 1;

            List<List<Integer>> list = new ArrayList<>();

            for (int j = 0; j < 100; j++) {
                list.add(new ArrayList<>());
            }

            String[] tmp2 = br.readLine().split(" ");
            for (int j = 0; j < length; j += 2) {
                int from = Integer.parseInt(tmp2[j]) - 1;
                int to = Integer.parseInt(tmp2[j + 1]) - 1;
                list.get(from).add(to);
            }

            System.out.printf("#%d %d %n", i, bfs(start, list) + 1);
        }
    }

    /**
     * bfs를 통해서 연결이 되어 있는지 구해주는 메소드
     * int start : bfs의 시작 점
     * List<List<>> list : 연결 정보를 담고 있는 리스트
     * return : 정답
     * */
    static int bfs(int start, List<List<Integer>> list) {
        boolean[] visited = new boolean[100];
        Deque<int[]> q = new ArrayDeque<>();
        // 시작점, 깊이
        q.add(new int[] {start, 0});
        visited[start] = true;

        int[] result = {start, 0};

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();
            int x = tmp[0];
            int depth = tmp[1];

            for (int nx : list.get(x)) {
                if (visited[nx])
                    continue;
                visited[nx] = true;
                q.add(new int[] {nx, depth + 1});

                if (depth + 1 == result[1]) {
                    // 만약 정답의 깊이와 bfs의 깊이가 같으면 둘 중 더 큰 번호를 가지기
                    result[0] = Math.max(nx, result[0]);
                } else if (depth + 1 > result[1]) {
                    // 만약에 정답의 깊이보다 bfs의 깊이가 더 깊으면 그냥 bfs의 값 가지기
                    result[0] = nx;
                    result[1] = depth + 1;
                }
            }
        }

        return result[0];
    }
}
