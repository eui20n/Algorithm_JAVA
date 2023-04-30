/**
 * 문제 이름 : 물통
 * URL : https://www.acmicpc.net/problem/2251
 * 문제 설명 :
 * A, B, C 물통이 있다. C의 물통에만 물이 가득 차있고, 이 물은 각각의 물통으로 옮길 수 있다. 이때, A에 물통에 물이 없을 때, C물통에
 * 물이 있는 경우, C물통의 물의 양을 오름차순으로 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] standard = new int[3];
    static HashSet<String> visited = new HashSet<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        standard = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        check();
        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d ", result.get(i));
        }
    }

    static void check() {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, standard[2]});
        String visitedValue = "0" + "0" + Integer.toString(standard[2]);
        visited.add(visitedValue);
        result.add(standard[2]);

        while (true) {
            if (q.isEmpty())
                break;

            int[] tmp = q.poll();

            for (int i = 0; i < 3; i++) {
                // i는 기준이 되는 물로, i물통에 있는 물이 이동을 하는 것
                for (int j = 0; j < 3; j++) {
                    // i번 물통에 있는 물이 j로 가는 것
                    int[] totalAmount = new int[]{tmp[0], tmp[1], tmp[2]};
                    if (totalAmount[i] == 0)
                        continue;
                    if (i == j)
                        continue;

                    int moveWater = totalAmount[i];
                    int thisWater = totalAmount[j];
                    int afterWater = moveWater + thisWater; // 물을 옮김 => 그 곳에 있는 양 + 옮긴 물의 양

                    moveWater = 0; // 물을 옮겼으니 여기는 물의 양이 없어짐
                    if (afterWater > standard[j]){
                        // 만약에 옮김 물의 양이 그 물통의 최대치 보다 크다면 넘치는 것은 옮길 수 없음 => moveWater에 남음
                        moveWater = afterWater - standard[j];
                        afterWater = standard[j];
                    }

                    totalAmount[i] = moveWater;
                    totalAmount[j] = afterWater;

                    visitedValue = Integer.toString(totalAmount[0]) + Integer.toString(totalAmount[1]) + Integer.toString(totalAmount[2]);
                    if (visited.contains(visitedValue))
                        continue;
                    visited.add(visitedValue);

                    if (totalAmount[0] == 0)
                        result.add(totalAmount[2]);
                    q.add(totalAmount);
                }
            }
        }
    }
}

// bfs를 이용해서 확인할 것