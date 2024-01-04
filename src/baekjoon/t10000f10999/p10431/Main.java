/**
 * 문제 이름 : 줄세우기
 * URL : https://www.acmicpc.net/problem/10431
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t10000f10999.p10431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int[] inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            check(inputArr);
        }
    }

    static void check(int[] arr) {
        int t = arr[0];
        int result = 0;
        Deque<Integer> q = new ArrayDeque<>();


        for (int idx = 1; idx < arr.length; idx++) {
            // 로직
            // 유니온 파인드 처럼 해봐도 될듯함 => 근데 시간 복잡도 관점에서 볼 때, 크게 이득은 아닌 것 같음.... 그냥 하는 것이랑 똑같을 듯
            // 큐로 하는 것이 가장 쉬운 방법일 듯 => 앞에서 부터 빼면서 위치 찾은 후, 그 위치에서 뒤에 몇개 있는지 세면 될 듯
            int height = arr[idx];

            if (q.isEmpty()) {
                // 아무 것도 없는 상태(초기 상태)면 그냥 값을 넣어줌
                q.add(height);
            } else {
                // 값이 있음
                // 여기 부터는 위치를 찾아서 넣으면 됨
                boolean flag = false;
                int loopSize = q.size();

                for (int i = 0; i < loopSize; i++) {
                    int compareHeight = q.pollFirst();
//                    System.out.println("height : " + height);
//                    System.out.println("compareHeight : " + compareHeight);

                    if (height < compareHeight) {
                        if (!flag) {
                            flag = true;
                            q.addLast(height);
                        }
                        result += 1;
                    }
                    q.addLast(compareHeight);
                }

                // 만약 현재 큐의 가장 큰 값 보다 들어올 값이 큰 경우 => 그냥 뒤에 추가해주면 됨
                if (loopSize == q.size()) {
                    q.add(height);
                }
            }
        }
        System.out.println(t + " " + result);
    }
}
