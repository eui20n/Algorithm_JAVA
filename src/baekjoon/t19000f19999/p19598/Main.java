/**
 * 문제 이름 : 최소 회의실 개수
 * URL : https://www.acmicpc.net/problem/19598
 * 문제 설명 :
 * 각 회의의 시작 시간과 끝나는 시간이 주어질때, 모든 회의를 할 수 있는 회의실의 최소 개수를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 그리디 + 정렬
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t19000f19999.p19598;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[][] meetings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            meetings[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(meetings, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        check();
    }

    static void check() {
        PriorityQueue<int[]> meetingRoom = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        for (int i = 0; i < N; i++) {
            if (meetingRoom.size() == 0)
                meetingRoom.add(meetings[i]);
            else {
                // 가장 빨리 끝나는 회의보다 시작 시간이 더 늦음 => 방을 더 잡을 필요 없음
                if (meetingRoom.peek()[1] <= meetings[i][0])
                    // 가장 빨리 끝나는 회의 방 빼기
                    meetingRoom.poll();
                meetingRoom.add(meetings[i]);
            }
        }
        // 결국 우선 순위에 남아 있는 수가 회의를 방의 수임
        System.out.println(meetingRoom.size());
    }
}

// 시작하는 시간으로 정렬을 해야함
// 우선순위 큐에 들어가는 값은 끝나는 시간으로 정렬
