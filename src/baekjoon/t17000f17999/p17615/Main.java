/**
 * 문제 이름 : 볼 모으기
 * URL : https://www.acmicpc.net/problem/17615
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static char[] arr;
    static List<Integer> redArea = new ArrayList<>();
    static List<Integer> blueArea = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        System.out.println(check());
    }

    static int check() {
        // 영역 나누기
        areaSplit();

        // 만약에 영역을 나눴는데, 둘다 길이가 1이거나 하나라도 0인 것이 있다면 답은 0
        if (redArea.size() <= 1 && blueArea.size() <= 1) {
            return 0;
        }

        // 정답 구하기
        // 빨간색 최솟값
        // 앞 뒤 모두 빨간색
        int redResult = 0;
        int redState = 0; // 0 -> 앞뒤 다 더하기, 1 -> 앞에 있는 것 더하기, 2 -> 뒤에 있는 것 더하기
        if (arr[0] == 'R' && arr[N - 1] == 'R') {
            if (redArea.get(0) >= redArea.get(redArea.size() - 1)) {
                // 앞에 있는 것이 더 크면 뒤에 있는 것으로 더하기
                redState = 2;
            } else {
                // 앞에 있는 것으로 더하기
                redState = 1;
            }
        } else if (arr[0] == 'R') {
            // 앞이 빨간색 => 뒤에 있는 것 더하기
            redState = 2;
        } else if (arr[N - 1] == 'R') {
            // 뒤가 빨간색 => 앞에 있는 것 더하기
            redState = 1;
        }
        for (int i = 0; i < redArea.size(); i++) {
            if (redState == 2 && i == 0)
                // 가장 앞이 빨간색 => 가장 앞에 있는 것을 제외하고 다 더하면 됨
                continue;
            if (redState == 1 && i == redArea.size() - 1)
                // 가장 마지막이 빨간색 => 가장 마지막에 있는 것을 제외하고 다 더하면 됨
                continue;
            // 앞뒤 모두 파란 색 => 그냥 다 더하면 됨
            redResult += redArea.get(i);
        }

        // 파란색 최솟값
        int blueResult = 0;
        int blueState = 0; // 0 -> 앞뒤 다 더하기, 1 -> 앞에 있는 것 더하기, 2 -> 뒤에 있는 것 더하기
        if (arr[0] == 'B' && arr[N - 1] == 'B') {
            if (blueArea.get(0) >= blueArea.get(blueArea.size() - 1)) {
                // 앞에 있는 것이 더 크면 뒤에 있는 것으로 더하기
                blueState = 2;
            } else {
                // 앞에 있는 것으로 더하기
                blueState = 1;
            }
        } else if (arr[0] == 'B') {
            // 앞이 빨간색 => 뒤에 있는 것 더하기
            blueState = 2;
        } else if (arr[N - 1] == 'B') {
            // 뒤가 빨간색 => 앞에 있는 것 더하기
            blueState = 1;
        }
        for (int i = 0; i < blueArea.size(); i++) {
            if (blueState == 2 && i == 0)
                // 가장 앞이 빨간색 => 가장 앞에 있는 것을 제외하고 다 더하면 됨
                continue;
            if (blueState == 1 && i == blueArea.size() - 1)
                // 가장 마지막이 빨간색 => 가장 마지막에 있는 것을 제외하고 다 더하면 됨
                continue;
            // 앞뒤 모두 파란 색 => 그냥 다 더하면 됨
            blueResult += blueArea.get(i);
        }

        return Math.min(redResult, blueResult);
    }

    static void areaSplit() {
        // 영역을 나눌 메소드
        Deque<int[]> q = new ArrayDeque<>();
        if (arr[0] == 'R') {
            // 현재 위치가 R이면 0
            q.add(new int[]{0, 1, 0}); // 현재 위치, 그룹에 속해 있는 영역, 현재 색깔
        } else {
            // 현재 위치가 B이면 1
            q.add(new int[]{0, 1, 1});
        }

        while (true) {
            if (q.isEmpty())
                break;

            int[] p = q.poll();
            int x = p[0];
            int cnt = p[1];
            int color = p[2];

            int nx = x + 1;
            if (nx >= N) {
                // 마지막을 넘어선 경우 => 이전의 값을 저장하면 됨
                if (color == 0) {
                    redArea.add(cnt);
                } else {
                    blueArea.add(cnt);
                }

                break;
            }

            int nextColor = arr[nx] == 'R' ? 0 : 1;
            if (nextColor == color) {
                // 색이 같을 경우
                q.add(new int[]{nx, cnt + 1, nextColor});
            } else {
                // 색이 다를 경우
                if (color == 0) {
                    redArea.add(cnt);
                } else {
                    blueArea.add(cnt);
                }
                q.add(new int[]{nx, 1, nextColor});
            }
        }
    }
}

/*
        2가지 경우로 나눠서 정답을 구해야 하나

        이 문제는 BFS => 영역 구하기임
        ... 알고리즘 분류는 그리디로 되어 있네
 */