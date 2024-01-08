/**
 * 문제 이름 : 크로스 컨트리
 * URL : https://www.acmicpc.net/problem/9017
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t9000f9999.p9017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    static int T, N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            check();
        }
    }

    static void check() {
        // 복사된 배열
        int[] newArr = deepCopy(arr);

        // 복사된 배열 정렬
        Arrays.sort(newArr);

        // 배열 재구성 => 점수를 얻을 수 없는 팀 제외하기
        int[] rebuildingArr = rebuildingArr(newArr);

        // 점수 계산하기 => 점수는 2차원 배열로 계산을 할 것
        // 1차원 4명 까지의 점수, 2차원 5명 까지의 점수
        int[][] scoreArr = getScore(rebuildingArr);

        // 정답 구하기
        System.out.println(result(scoreArr));
    }

    static int[] deepCopy(int[] copyArr) {
        int[] newArr = new int[copyArr.length];

        for (int i = 0; i < copyArr.length; i++) {
            newArr[i] = copyArr[i];
        }

        return newArr;
    }

    static int[] rebuildingArr(int[] newArr) {
        HashSet<Integer> team = new HashSet<>();

        int cnt = 1;
        int lastNum = -1;
        for (int i = 0; i < newArr.length; i++) {
            if (lastNum == newArr[i]) {
                cnt += 1;
            } else {
                cnt = 1;
            }
            lastNum = newArr[i];

            if (cnt == 6) {
                team.add(lastNum);
            }
        }

        int[] rebuildingArr = new int[6 * team.size()];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (team.contains(arr[i])) {
                rebuildingArr[idx++] = arr[i];
            }
        }
        return rebuildingArr;
    }

    static int[][] getScore(int[] rebuildingArr) {
        int[][] scoreArr = new int[201][2];
        int[] teamCnt = new int[201];

        for (int i = 0; i < rebuildingArr.length; i++) {
            int teamNum = rebuildingArr[i];

            if (teamCnt[teamNum] < 4) {
                scoreArr[teamNum][0] += i + 1;
            } else if (teamCnt[teamNum] == 4) {
                scoreArr[teamNum][1] = scoreArr[teamNum][0] + i + 1;
            }
            teamCnt[teamNum] += 1;
        }

        return scoreArr;
    }

    static int result(int[][] score) {
        int winner = -1;
        int winnerScore = 100000000;

        for (int i = 1; i < score.length; i++) {
            int teamScore = score[i][0];

            if (teamScore == 0)
                continue;

            if (winnerScore > teamScore) {
                winner = i;
                winnerScore = teamScore;
            } else if (winnerScore == teamScore) {
                int winnerScore2 = score[winner][1];
                int teamScore2 = score[i][1];

                if (winnerScore2 > teamScore2) {
                    winner = i;
                    winnerScore = teamScore;
                }
            }
        }

        return winner;
    }
}
