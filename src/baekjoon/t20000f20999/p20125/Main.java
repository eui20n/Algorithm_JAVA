/**
 * 문제 이름 : 쿠키의 신체 측정
 * URL : https://www.acmicpc.net/problem/20125
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] cookieArr;
    static final int LEFT_ARM = -1;
    static final int RIGHT_ARM = 1;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cookieArr = new char[N][N];

        for (int i = 0; i < N; i++) {
            cookieArr[i] = br.readLine().toCharArray();
        }

        check();
    }

    static void check() {
        int[] heartLocation = findHeart(); // 출력할 때, 1씩 더해주기
        int[] waistAndLeg = checkWaist(heartLocation);

        int leftArm = checkArm(LEFT_ARM, heartLocation);
        int rightArm = checkArm(RIGHT_ARM, heartLocation);
        int waist = waistAndLeg[0];
        int leftLeg = waistAndLeg[1];
        int rightLeg = waistAndLeg[2];

        sb.append(heartLocation[0] + 1)
                .append(" ")
                .append(heartLocation[1] + 1)
                .append("\n")
                .append(leftArm)
                .append(" ")
                .append(rightArm)
                .append(" ")
                .append(waist)
                .append(" ")
                .append(leftLeg)
                .append(" ")
                .append(rightLeg);

        System.out.println(sb);
    }

    static int[] findHeart() {
        // 머리를 찾고 거기서 한 칸 아래
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cookieArr[i][j] == '*')
                    return new int[]{i + 1, j};
            }
        }
        return null;
    }

    static int checkArm(int arm, int[] heartLocation) {
        int mean = 0;
        int x = heartLocation[0];
        int y = heartLocation[1];

        while (true) {
            int ny = y + arm;

            if (ny < 0 || ny >= N || cookieArr[x][ny] == '_') {
                return mean;
            }
            mean += 1;
            y = ny;
        }
    }

    static int[] checkWaist(int[] heartLocation) {
        int[] means = new int[3]; // 허리, 왼 다리, 오른 다리

        int x = heartLocation[0];
        int y = heartLocation[1];

        while (true) {
            int nx = x + 1;

            if (nx < 0 || nx >= N || cookieArr[nx][y] == '_') {
                means[1] = checkLeg(x + 1, y - 1); // 왼 다리
                means[2] = checkLeg(x + 1, y + 1); // 오른 다리
                return means;
            }

            x = nx;
            means[0] += 1;
        }
    }

    static int checkLeg(int startX, int startY) {
        int mean = 1;
        int x = startX;

        while (true) {
            int nx = x + 1;

            if (nx < 0 || nx >= N || cookieArr[nx][startY] == '_') {
                return mean;
            }

            x = nx;
            mean += 1;
        }
    }
}
