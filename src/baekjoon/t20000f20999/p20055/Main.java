/**
 * 문제 이름 : 컨베이어 벨트 위의 로봇
 * URL : https://www.acmicpc.net/problem/20055
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int N, K;
    static int[] beltArr;
    static int[] top, bottom; // 위쪽, 아래쪽
    static boolean[] visited; // 위쪽에 로봇이 있는지 알려주는 배열
    static Deque<Integer> robots = new ArrayDeque<>(); // 로봇 위치 관리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = tmpInput[0];
        K = tmpInput[1];
        top = new int[N];
        bottom = new int[N];
        visited = new boolean[N];
        beltArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(check());
    }

    static int check() {
        // 초기 배열 생성
        top = makeArr(true); // 위 쪽
        bottom = makeArr(false); // 아래 쪽

        int time = 0;
        int breakBeltCnt = 0;

        while (true) {
            if (breakBeltCnt >= K)
                return time;

            // 벨트가 각 칸위에 있는 로봇과 함께 한 칸 회전
            rotateBelt();

            // 버릴 내리는 칸에 로봇이 있는지 확인하고, 있으면 내리기
            robotCheck();

            // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
            // 이동할 수 없다면 가만히 있기 => 옆에 로봇 없고, 내구도 1 이상
            moveRobot();

            // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇 올리기
            putRobot();

            // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
            breakBeltCnt = checkBelt();
            time += 1;
        }
    }

    static int checkBelt() {
        int returnValue = 0;

        // 벨트의 내구도 확인
        for (int i = 0; i < N; i++) {
            if (top[i] == 0) {
                returnValue += 1;
            }
            if (bottom[i] == 0) {
                returnValue += 1;
            }
        }

        return returnValue;
    }

    static void putRobot() {
        if (top[0] >= 1) {
            visited[0] = true;
            top[0] -= 1;
            robots.add(0);
        }
    }

    static void moveRobot() {
        // 가장 먼저 들거간 로봇 부터 한 칸 씩 이동
        int loopSize = robots.size();

        for (int i = 0; i < loopSize; i++) {
            int robot = robots.pollFirst();
            if (!visited[robot + 1] && top[robot + 1] >= 1) {
                // 위 조건이 맞아야 움직일 수 있음
                robot += 1; // 이동함
                top[robot] -= 1; // 이동한 곳의 내구도를 1 깎아줌
                visited[robot] = true; // 이동한 위치 true
                visited[robot - 1] = false; // 있던 위치 false
            }

            if (robot == N - 1) {
                visited[N - 1] = false;
            } else {
                robots.add(robot);
            }
        }
    }

    static void robotCheck() {
        if (visited[N - 1]) {
            // 내리는 칸에 로봇이 있음 => 가장 먼저 들어간 것을 버려줌
            robots.pollFirst();
            visited[N - 1] = false;
        }
        // 로봇들의 위치 한 칸 씩 이동시켜주기
        int loopSize = robots.size();
        for (int i = 0; i < loopSize; i++) {
            int robot = robots.pollFirst();
            robots.add(robot + 1);
        }
    }

    static void rotateBelt() {
        // 위쪽 회전
        int[] newTop = new int[N];
        boolean[] newVisited = new boolean[N];
        int goBottom = top[N - 1]; // 아래로 가는 값
        for (int i = 1; i < N; i++) {
            newTop[i] = top[i - 1];
            newVisited[i] = visited[i - 1];
        }

        // 아래쪽 회전
        int[] newBottom = new int[N];
        int goTop = bottom[0]; // 위로 가는 값
        for (int i = 1; i < N; i++) {
            newBottom[i - 1] = bottom[i];
        }

        newTop[0] = goTop;
        newBottom[N - 1] = goBottom;

        top = newTop;
        bottom = newBottom;
        visited = newVisited;
    }

    static int[] makeArr(boolean flag) {
        // flag = true => 위쪽
        // flag = false = > 아래쪽
        int[] returnArr = new int[N];

        for (int i = 0; i < N; i++) {
            returnArr[i] = beltArr[flag ? i : 2 * N - 1 - i];
        }

        return returnArr;
    }
}

/*
    로봇이 들어온 순서는 큐로 관리 => 먼저 들어온 것 먼저 처리한다고 했으니까
    로봇이 해당 위치에 있다는 것을 방문처리 배열을 만들어서 처리
 */