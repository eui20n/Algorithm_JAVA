/**
 * 문제 이름 : NBA 농구
 * URL : https://www.acmicpc.net/problem/2852
 * 문제 설명 : 농구 경기동안 각 팀이 이기고 있는 시간을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] teamGaol = new int[3];
    static int[] leadTime = new int[3];
    static String team1Time;
    static String team2Time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] goalInfo = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            goalInfo[i][0] = Integer.parseInt(tmp[0]);

            int[] goalTime = Arrays.stream(tmp[1].split(":")).mapToInt(Integer::parseInt).toArray();
            goalInfo[i][1] = goalTime[0] * 60 + goalTime[1];

        }

        basketBall(goalInfo);
        System.out.println(team1Time);
        System.out.println(team2Time);
    }

    static void basketBall(int[][] goalInfo) {
        int time = 0;
        int idx = 0;
        while (true) {
            if (time == 2880) {
                break;
            }
            if (idx < N) {
                int goalTeam = goalInfo[idx][0];
                int playTime = goalInfo[idx][1];

                if (time == playTime) {
                    idx+= 1;
                    teamGaol[goalTeam] += 1;
                }
            }

            if (teamGaol[1] > teamGaol[2]) {
                leadTime[1] += 1;
            } else if (teamGaol[2] > teamGaol[1]) {
                leadTime[2] += 1;
            }
            time++;
        }
        secondToMinute();
    }

    static void secondToMinute() {
        int team1 = leadTime[1];
        int team2 = leadTime[2];

        team1Time = String.format("%02d:%02d", team1 / 60, team1 % 60);
        team2Time = String.format("%02d:%02d", team2 / 60, team2 % 60);
    }
}

// 농구는 48분 동안 게임이 진행이됨
// 그냥 48분을 초로 생각해서 각 초마다 개인이 진행이 된다고 생각
// 그래서 서로 득점을 비교해서 이기고 있는 시간만큼 초로 기록
// 나중에 출력할 때는 분으로 바꿔서 출력
// 정렬을 할 필요 없음 => 문제에서 골이 들어갈때마다 기록한다고 되어 있음(순서대로 들어온다는 것!)