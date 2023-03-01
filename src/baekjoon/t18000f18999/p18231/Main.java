/**
 * 문제 이름 : 파괴된 도시
 * URL : https://www.acmicpc.net/problem/18231
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t18000f18999.p18231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int K;
    static List<List<Integer>> cityInfo = new ArrayList<>();
    static boolean[] destroyCity;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        destroyCity = new boolean[N];
        for (int i = 0; i < N; i++) {
            cityInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] tmp2 = br.readLine().split(" ");
            int city1 = Integer.parseInt(tmp2[0]) - 1;
            int city2 = Integer.parseInt(tmp2[1]) - 1;

            cityInfo.get(city1).add(city2);
            cityInfo.get(city2).add(city1);
        }

        K = Integer.parseInt(br.readLine());
        int[] tmp2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int idx : tmp2) {
            destroyCity[idx - 1] = true;
        }
        checkCity();
    }

    static void checkCity() {
        List<Integer> notDestroyCity = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (destroyCity[i])
                destroyCity[i] = false;
            else {
                destroyCity[i] = true;
                notDestroyCity.add(i);
            }
        }
        int[] depth = checkDepth(notDestroyCity);
        System.out.println(Arrays.toString(depth));
    }

    static int[] checkDepth(List<Integer> list) {
        int[] newArr = new int[N];
        Deque<int[]> q = new ArrayDeque<>();
        for (int city : list) {
            q.add(new int[]{city, 0});
            newArr[city] = -1;
        }

        while (true) {
            if (q.isEmpty())
                break;
            int[] tmp = q.poll();
            int city = tmp[0];
            int depth = tmp[1];

            for (int cityIdx : cityInfo.get(city)) {
                if (newArr[cityIdx] != 0)
                    continue;
                newArr[cityIdx] = depth + 1;
                q.add(new int[]{cityIdx, depth + 1});
            }
        }
        return newArr;
    }
}

// 파괴되지 않은 도시와 깊이가 2이상 차이나는 것중에서 가장 많은 도시와 인접해 있는 것을 고르면 됨
// 절대 안되는 경우 = 깊이가 1인데 다른 곳이랑 연결이 안되어 있는 곳
// 전부다 방문처리가 되어 있는 않으면 안되는 것

// 연산 순서
// 1. 파괴되지 않은 도시 구하기
// 2. 파괴되지 않은 도시에서 시작해서 모든 도시에 대해서 깊이 구하주기
// 3. 파괴된 도시에서 파괴되지 않은 도시 정보 빼기