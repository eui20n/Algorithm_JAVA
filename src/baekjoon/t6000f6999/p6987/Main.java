/**
 * 문제 이름 : 월드컵
 * URL : https://www.acmicpc.net/problem/6987
 * 문제 설명 : 조별리그 성적이 나올 때 나올 수 있는 경우의 수인지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t6000f6999.p6987;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            int[][] team = new int[6][3];

            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 3; y++) {
                    team[x][y] = sc.nextInt();
                }
            }
            int result = check(team) ? 1 : 0;
            System.out.print(result + " ");
        }
    }

    static boolean check(int[][] team) {
        int win = 0;
        int lose = 0;
        int draw = 0;
        boolean check = false;

        for (int i = 0; i < 6; i++) {
            if (team[i][0] + team[i][1] + team[i][2] != 5)
                return false;
            if (0 > team[i][0] || team[i][0] >= 6)
                return false;
            if (0 > team[i][1] || team[i][1] >= 6)
                return false;
            if (0 > team[i][2] || team[i][2] >= 6)
                return false;
            win += team[i][0];
            lose += team[i][2];
            draw += team[i][1];
        }

        List<int[]> play = new ArrayList<>();
        while (true) {
            int idx = -1;
            boolean next = false;

            for (int i = 0; i < 6; i++) {
                if (team[i][1] >= 1 && idx == -1) {
                    idx = i;
                } else if (team[i][1] >= 1 && !contain(play, new int[] {idx, i})) {
                    team[i][1] -= 1;
                    team[idx][1] -= 1;
                    win += 1;
                    lose += 1;
                    draw -= 2;
                    next = true;
                    play.add(new int[]{idx, i});
                    break;
                }
            }

            if (!next) {
                break;
            }
            if (draw == 0) {
                break;
            }
        }

//        System.out.println(win);
//        System.out.println(lose);
//        System.out.println(draw);

        if (win == 15 && lose == 15 && draw == 0) {
            check = true;
        }
        return check;
    }

    static boolean contain(List<int[]> list, int[] arr) {
        for (int[] intArr : list) {
            if (intArr[0] == arr[0] && intArr[1] == arr[1])
                return true;
        }
        return false;
    }
}

// 조합으로 패 뺄 녀셕 정해주기
// 조합으로 무승부 뺄 녀셕 정해주기
// 나온 케이스에서 패와 무승부에 서로 있는 경우면 건너띄기