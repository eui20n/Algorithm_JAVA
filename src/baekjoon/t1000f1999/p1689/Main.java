/**
 * 문제 이름 : 겹치는 선분
 * URL : https://www.acmicpc.net/problem/1689
 * 문제 설명 :
 * 일차원 좌표계에 선분이 있는데, 이 선분들이 겹친다. 이때 겹친 부분은 최대 몇 겹인지 구해라
 * 좌표계는 음수도 표현이 되어 있는 듯 함
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1689;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> line = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            line.add(tmp);
        }

        Collections.sort(line, ((o1, o2) -> {
            if (o1[0] == o2[0])
                return o2[1] - o1[1];
            return o1[0]- o2[0];
        }));

        check();
    }

    static void check() {
        Deque<int[]> deque = new ArrayDeque<>();
        int result = 0;

        for (int i = 0; i < line.size(); i++) {
            /*
                스텝 정리하기
                # 0번 인덱스가 가장 아래 있는 것
                # 마지막 번 인덱스가 가장 위에 있는 것
                # 덱에 들어가는 것은 시작 점과 끝 점

                * 덱에 아무 것도 없는 경우
                1. 덱에 현재 선분을 추가해준다.
                2. continue;

                * 덱에 값이 있는 경우
                1. 가장 위에 있는 것의 끝 점과 현재 것의 시작점을 비교한다.
                2-1. (가장 위 끝 > 현재 시작) => 겹치는 경우, 현재를 덱의 위에 추가해주면 된다.
                2-2. (가장 위 끝 <= 현재 시작) => 겹치지 않은 경우, 언제까지 겹치지 않는지 확인해줘서, 겹치지 않는 것들은 다 빼고 현재의 것을 덱의 가장 위에 추가해주면 된다.
                3. 위 과정을 한 후, 덱의 길이를 확인하다. => 겹친 수
                4. 겹친 수의 최대값을 계속해서 갱신한다.
             */
            int[] now = line.get(i);
            if (deque.isEmpty()) {
                // 덱에 아무 것도 없는 경우
                deque.add(now);
            } else {
                // 덱에 값이 있는 경우
                int[] top = deque.peekLast();

                if (top[1] > now[0]) {
                    // 겹치는 경우
                    deque.add(now);
                } else if (top[1] <= now[0]) {
                    // 겹치지 않는 경우
                    deque.poll();
                    while (true) {
                        // 겹치는 구간이 나올 때 까지 계속해서 값을 빼주기
                        if (deque.isEmpty())
                            // 덱에 아무 것도 없으면 그만하기
                            break;

                        top = deque.peekLast();
                        if (top[1] > now[0])
                            // 겹치는 부분
                            break;
                        deque.poll();
                    }
                    deque.add(now);
                }
            }
            // 최대값 갱신
            if (result < deque.size()) {
                for (int j = 0; j < deque.size(); j++) {
                    int[] tmp = deque.pollFirst();
                    System.out.print(Arrays.toString(tmp) + " ");
                    deque.addLast(tmp);
                }
                System.out.println();
                result = deque.size();
            }
//            result = Math.max(result, deque.size());
        }
        // 정답 출력
        System.out.println(result);
    }
}

/*
생각
1. 정렬한다. (시작 점이 제일 작게, 이러한게 여러개라면 끝도 제일 작게)
2. 시작점 부터 끝점을 본다.
3. 그 다음을 본다. 이 때, 해당 범위에 벗어나는지 확인하고 벗어나지 않는다면 겹치고 있는 것이다.
4. 이렇게 계속 반복해서 확인하다.
5. 기준이 되는 선은 겹치는지 확인하는 선의 시작점이 기준이 되는 선보다 커지면 바뀐다. => 해당 선이 기준선이 된다.
6. 위 과정을 모든 선에 대해서 수행하면 된다.

 */

/*
덱? => 완전히 벗어나는 경우에 대해서 생각해보기(시작과 끝이 기존에 있는 것보다 다 큰 경우, 복잡도가 얼마일지)
 */