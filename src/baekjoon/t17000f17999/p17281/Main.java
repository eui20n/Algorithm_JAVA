/**
 * 문제 이름 : ⚾
 * URL : https://www.acmicpc.net/problem/17281
 * 문제 설명 :
 * 야구 팀의 감독이 타순을 정하려고 한다. 1번 선수를 4번 타자로 미리 결정을 했고, 각 타자가 어떤 결과를 주는지 알고 있을 때
 * 가장 많이 득점하는 타순에 얼마나 득점하는지 출력해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;

public class Main {
    static int[] battingOrder = {-1, -1, -1, 1, -1, -1, -1, -1, -1};
    static int[][] innings;
    static int N;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];

        for (int i = 0; i < N; i++) {
            innings[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        makeOrderHitter(1);
        System.out.println(result);
    }

    static void makeOrderHitter(int player) {
        /*
         * 재귀 만들기(수학적 귀납법)
         * 1. 기저조건 생각하기(n = 1 일때 or 조건의 끝 일때)
         * 2. n = k이가 만족한다고 가정하기
         * 3. n = k + 1을 구현하기
         * */
        if (player == 9) {
            result = Math.max(baseBall(), result);
            return;
        }

        for (int i = 0; i < battingOrder.length; i++) {
            if (battingOrder[i] != -1)
                continue;

            battingOrder[i] = player + 1;
            makeOrderHitter(player + 1);
            battingOrder[i] = -1;
        }
    }

    static int baseBall() {
        // 타순은 전역변수로 설정을 함
        int inning = 0;
        int playerIdx = 0;
        int score = 0;
        int[] base;
        while (true) {
            if (inning == N)
                break;

            int outCnt = 0;
            base = new int[]{0, 0, 0}; // 1루, 2루, 3루

            while (true) {
                if (outCnt == 3)
                    break;

                int[] inningInfo = innings[inning];
                int player = battingOrder[playerIdx];
                int go = inningInfo[player - 1]; // 이번 이닝에서 플레이어의 행동

                if (go == 0) {
                    // 아웃
                    outCnt++;
                } else if (go == 4) {
                    // 홈런
                    score += 1;
                    for (int i = 0; i < 3; i++) {
                        if (base[i] == 0)
                            continue;
                        score += 1;
                        base[i] = 0;
                    }
                } else {
                    for (int i = 2; i >= 0; i--) {
                        // 역순으로 해서 주자의 정보가 안바뀌게 해줌
                        // 정순으로 하면 중간에 바뀔때마다 계속 하는 행동이 달라지게됨
                        if (base[i] == 0)
                            continue;
                        int newBase = i + go;
                        if (newBase >= 3) {
                            // 주자 홈 인
                            score += 1;
                            base[i] = 0;
                            continue;
                        }
                        // 주자가 진루
                        base[newBase] = 1;
                        // 주자가 있던 자리는 0으로 해주기
                        base[i] = 0;
                    }
                    base[go - 1] = 1;
                }
                playerIdx = playerIdx >= 8 ? 0 : playerIdx + 1;
            }
            inning += 1;
        }
        return score;
    }
}

// 1 안타
// 2 2루타
// 3 3루타
// 4 홈런
// 0 아웃
// ㅋ
