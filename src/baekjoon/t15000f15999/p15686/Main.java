/**
 * 문제 이름 : 치킨 배달
 * URL : https://www.acmicpc.net/problem/15686
 * 문제 설명 : 치킨 거리가 존재한다. 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
 * 길이를 구하는 공식은 맨하튼 거리이다.
 * 도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야할때, 어떻게 고르면 도시의 치킨 거리가 가장 작게 되는지 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t15000f15999.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static List<int[]> homeLoc = new ArrayList<>();
    static List<int[]> chickenLoc = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        find();
        chicken(0, 0, new ArrayList<>());
        System.out.println(result);
    }

    static void find() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    homeLoc.add(new int[]{i, j});
                } else if (arr[i][j] == 2) {
                    chickenLoc.add(new int[]{i, j});
                }
            }
        }
    }

    static void chicken(int cnt, int idx, List<int[]> select) {
        if (cnt == M) {
            int num = minCityDist(select);
            result = Math.min(result, num);
            return;
        }
        int x, y;

        for (int i = idx; i < chickenLoc.size(); i++) {
            int[] chickenArr = chickenLoc.get(i);
            x = chickenArr[0];
            y = chickenArr[1];

            int[] addArr = {x, y};

            select.add(addArr);
            chicken(cnt + 1, i + 1, select);
            select.remove(select.size() - 1);
        }
    }

    static int minCityDist(List<int[]> select) {
        int result = 0;

        for (int[] home : homeLoc){
            int tmp = Integer.MAX_VALUE;
            int homeX = home[0];
            int homeY = home[1];

            for (int[] chicken : select){
                int chickenX = chicken[0];
                int chickenY = chicken[1];

                int dist = Math.abs(chickenX - homeX) + Math.abs(chickenY - homeY);
                tmp = Math.min(dist, tmp);
            }
            result += tmp;
        }
        return result;
    }
}
