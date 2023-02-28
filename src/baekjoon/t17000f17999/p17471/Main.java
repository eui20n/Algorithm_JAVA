/**
 * 문제 이름 : 게리맨더링
 * URL : https://www.acmicpc.net/problem/17471
 * 문제 설명 : 백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은
 * 두 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다.
 * 이 선거구를 나눌 때, 공평해야해서 선거구에 포함된 인원의 차이가 적어야 한다. 이 때 인원의 차이의 최소값을 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 부분집합
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] land;
    static List<List<Integer>> nearLand = new ArrayList<>();
    static boolean[] selected;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        land = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < N; i++) {
            nearLand.add(new ArrayList<>());

            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 1; j < tmp.length; j++) {
                nearLand.get(i).add(tmp[j] - 1);
            }
        }

        selected = new boolean[N];

        for (int i = 1; i < N; i++) {
            selectLand(0, 0, i);
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
    /**
     * 팀을 정해주는 함수로, 부분집합(조합)이용
     * */
    static void selectLand(int idx, int cnt, int end) {
        if (end == cnt) {
            checkLand();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (selected[i])
                continue;

            selected[i] = true;
            selectLand(i + 1, cnt + 1, end);
            selected[i] = false;
        }
    }

    /**
     * 해당 팀들의 인원 차이를 구해주는 메소드
     * */
    static void checkLand() {
        List<Integer> ward1 = new ArrayList<>(); // 1번 팀
        List<Integer> ward2 = new ArrayList<>(); // 2번 팀

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                // 부분집합으로 구한 부분이 true면 1팀
                ward1.add(i);
            } else {
                // 부분집합으로 구한 부분이 false면 2팀
                ward2.add(i);
            }
        }
        boolean[] visited = new boolean[N];
        int population1 = check(ward1, true, visited);
        int population2 = check(ward2, false, visited);

        if (population1 == -1 || population2 == -1)
            return;
        result = Math.min(result, Math.abs(population1 - population2));
    }

    /**
     * 연결되어 있는지 확인해 주는 메소드
     * List<Integer> list : 갈 수 있는 도시의 번호
     * boolean check : 팀, true : 1팀, false : 2팀
     * boolean[] visited : 방문처리 배열
     * return : 해당 팀의 인원 수
     * */
    static int check(List<Integer> list, boolean check, boolean[] visited) {
        int cnt = 1;
        int population = 0;

        Deque<Integer> q = new ArrayDeque<>();
        q.add(list.get(0));
        visited[list.get(0)] = true;

        while (!q.isEmpty()) {
            int x = q.poll();
            population += land[x];

            for (int nx : nearLand.get(x)) {
                if (visited[nx])
                    continue;
                if (selected[nx] != check)
                    continue;
                q.add(nx);
                cnt += 1;
                visited[nx] = true;
            }
        }

        return cnt == list.size() ? population : -1;
    }
}

// 고전했다!