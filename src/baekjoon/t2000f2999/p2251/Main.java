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
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

public class Main {
    static int[] standard = new int[3];
    static int A, B, C;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        standard = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        A = standard[0];
        B = standard[1];
        C = standard[2];
        visited = new boolean[3][C + 1];

        check();
    }

    static void check() {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, C});
        visited[0][0] = true;
        visited[1][0] = true;
        visited[2][C] = true;

        while (true) {
            if (q.isEmpty())
                break;

            int[] totalAmount = q.poll();

            for (int z = 0; z < 3; z++) {
                if (totalAmount[z] == 0)
                    continue;

                for (int i = 0; i < 3; i++) {
                    if (i == z)
                        continue;
                    // i가 3보다 작아야하고, z랑 달라야함
                    int moveBottle = totalAmount[z];
                    int putBottle = totalAmount[i];

                    int afterMove = putBottle + moveBottle;
                    moveBottle = 0;
                    if (afterMove > standard[i]) {
                        moveBottle = afterMove - standard[i];
                        afterMove = standard[i];
                    }
                    totalAmount[z] = moveBottle;
                    totalAmount[i] = afterMove;

                    // 방문처리
//                    if (visited[0][totalAmount[0]] && visited[1][totalAmount[1]] && visited[2][totalAmount[2]])
//                        continue;
                    q.add(totalAmount);
                    visited[0][totalAmount[0]] = true;
                    visited[1][totalAmount[1]] = true;
                    visited[2][totalAmount[2]] = true;

//                    System.out.println(totalAmount[0]);
//                    System.out.println(totalAmount[1]);
//                    System.out.println(totalAmount[2]);
                    if (totalAmount[0] == 0)
                        System.out.println(totalAmount[2]);
                    System.out.println();

                }
            }
        }

//        System.out.println(Arrays.toString(visited[0]));
//        System.out.println(Arrays.toString(visited[1]));
//        System.out.println(Arrays.toString(visited[2]));
    }
}

// bfs를 이용해서 확인할 것

// 방문처리 방식에 대한 생각
// 방법 1.
// 각각의 몰통의 양을 문자열로 변환 => A,B,C 와 같은 꼴로
// 해당 문자열을 해쉬 집합에 저장하고, 이것으로 방문처리를 해결
// 이 방법을 할꺼면 그냥 집합에 문자열의 배열을 저장하면 되는거 아닌가 => 자바는 주소를 저장하기 때문에 안됨
// 방법 2.
// 각각의 숫자가 나오는 상태가 겹치나?
// 위 물음에 대해서 생각해보기
// 그냥 셋다 방문처리 리스트를 만들어서 관리하기
// 어느 경우든 모든 물통의 물의 양을 더하면 C임 => 겹치는게 있을 수가 없다는 의미