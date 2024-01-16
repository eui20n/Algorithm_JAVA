/**
 * 문제 이름 : KCPC
 * URL : https://www.acmicpc.net/problem/3758
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3758;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n, k, t, m;

            int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = tmpInput[0];
            k = tmpInput[1];
            t = tmpInput[2];
            m = tmpInput[3];

            // 각 팀의 점수 => 2차원인 이유는 각 문제에 대해서 가장 큰 점수만 취급을 해서 각 문제에 대한 점수를 가지고 있어야함
            int[][] teamScore = new int[n][k];
            // 각 팀의 최종 점수
            int[] teamFinalScore = new int[n];
            // 각 팀의 제출 횟수
            int[] teamSubmitCnt = new int[n];
            // 각 팀의 마지막 제출 시점
            int[] teamLastSubmit = new int[n];

            for (int c = 0; c < m; c++) {
                int submitTeam, quizNum, score, quizNumScore;
                int[] tmpInput2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                submitTeam = tmpInput2[0] - 1;
                quizNum = tmpInput2[1] - 1;
                score = tmpInput2[2];
                quizNumScore = teamScore[submitTeam][quizNum];

                // 만약에 이전에 제출했던 문제의 최댓값이 바뀐 경우
                if (quizNumScore < score) {
                    teamFinalScore[submitTeam] -= quizNumScore; // 최종 점수에서 이전 값을 빼주고
                    teamFinalScore[submitTeam] += score; // 현재 값을 더해줌
                    teamScore[submitTeam][quizNum] = score; // 현재 점수를 다시 저장
                }
                // 제출 횟수 저장
                teamSubmitCnt[submitTeam] += 1;
                // 마지막에 제출한 순서 저장
                teamLastSubmit[submitTeam] = c;
            }

            int result = check(teamFinalScore, teamSubmitCnt, teamLastSubmit, t - 1, n);
            System.out.println(result);
        }
    }

    static int check(int[] teamFinalScore, int[] teamSubmitCnt, int[] teamLastSubmit, int myTeam, int teamCnt) {
        // 조건에 맞게 우선 순위 큐 생성
        PriorityQueue<Integer> rank = new PriorityQueue<>((o1, o2) -> {
           if (teamFinalScore[o1] == teamFinalScore[o2]) {
               // 점수가 같을 경우
               if (teamSubmitCnt[o1] == teamSubmitCnt[o2]) {
                   // 문제를 제출한 횟수가 같을 경우
                   return teamLastSubmit[o1] - teamLastSubmit[o2];
               }
               return teamSubmitCnt[o1] - teamSubmitCnt[o2];
           }
           return teamFinalScore[o2] - teamFinalScore[o1];
        });

        // 랭킹 구하기
        for (int i = 0; i < teamCnt; i++) {
            rank.add(i);
        }

        // 내 팀 랭킹 찾기
        int loopSize = rank.size();
        for (int i = 0; i < loopSize; i++) {
            if (rank.poll() == myTeam) {
                return i + 1;
            }
        }
        return 0;
    }
}

/*
        그냥 우선순위 큐 처럼 하면 될 듯
        저장해야하는 값
        1. 각 팀의 최종 점수
        2. 각 팀의 총 제출 횟수
        3. 각 팀의 마지막 제출을 언제했는지
 */