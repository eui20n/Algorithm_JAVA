/**
 * 문제 이름 : 옥상 정원 꾸미기
 * URL : https://www.acmicpc.net/problem/6198
 * 문제 설명 :
 * 자기 보다 높은 건물의 옥상은 확인 할 수 없을때, 각각의 건물에서 확인 할 수 있는 다른 건물의 옥상을 합을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 스택
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t6000f6999.p6198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    static int N;
    static Stack<int[]> stack = new Stack<>();
    static List<Integer> buildings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            buildings.add(tmp);
        }

        buildings.add(Integer.MAX_VALUE);
        check();
    }

    static void check() {
        int[] result = new int[buildings.size()];
        long ans = 0;

        for (int i = 0; i < buildings.size(); i++) {
            if (stack.isEmpty()) {
                stack.add(new int[]{buildings.get(i), i});
                continue;
            }
            if (stack.peek()[0] > buildings.get(i)) {
                stack.add(new int[]{buildings.get(i), i});
                continue;
            }
            if (stack.peek()[0] <= buildings.get(i)) {
                while (true) {
                    if (stack.isEmpty() || stack.peek()[0] > buildings.get(i)) {
                        stack.add(new int[]{buildings.get(i), i});
                        break;
                    }

                    int[] stackValue = stack.pop();
                    int idx = stackValue[1];

                    result[idx] = i;
                }
            }
        }

        for (int i = 0; i < result.length; i++) {
            int value = 0;
            if (result[i] == 0)
                continue;
            value = result[i] - i - 1;
//            System.out.printf("i : %d, value : %d %n", i, value);
            ans += value;
        }
//        System.out.println(Arrays.toString(result));
        System.out.println(ans);
    }
}
