/**
 * 문제 이름 : 스위치 켜고 끄기
 * URL : https://www.acmicpc.net/problem/1244
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t1000f1999.p1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] switchState;
    static int cntStudent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        switchState = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cntStudent = Integer.parseInt(br.readLine());
        for (int i = 0; i < cntStudent; i++) {
            int[] studentInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            change(studentInfo);
        }
        int cnt = 0;
        for (int state : switchState) {
            if (cnt == 20) {
                System.out.println();
                cnt = 0;
            }

            cnt += 1;
            System.out.print(state + " ");
        }
    }

    static void change(int[] studentInfo) {
        int gender = studentInfo[0];
        int switchInfo = studentInfo[1];

        if (gender == 1) {
            male(switchInfo);
        } else {
            female(switchInfo);
        }

    }

    static void male(int num) {
        for (int i = num - 1; i < N; i += num) {
            switchState[i] ^= 1;
        }
    }

    static void female(int num) {
        int cnt = 1;
        int idx = num - 1;

        switchState[idx] ^= 1;

        while (true) {
            int left = idx - cnt;
            int right = idx + cnt;

            if (0 > left || right >= N) {
                break;
            }

            int leftState = switchState[left];
            int rightState = switchState[right];

            if (leftState == rightState) {
                switchState[left] ^= 1;
                switchState[right] ^= 1;
                cnt += 1;
            } else {
                break;
            }

        }

    }

}
