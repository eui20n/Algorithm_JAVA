/**
 * 문제 이름 : 돌 게임
 * URL : https://www.acmicpc.net/problem/9655
 * 문제 설명 :
 * 돌을 1개 혹은 3개를 가져가서 마지막에 돌을 가져가는 사람이 이기는 게임을 하고 있다.
 * 이 때, 게임이 완벽하게 진행되었을 때 상근이와 창영이중 누가 이기는지 구해라. 단, 게임은 상근이 먼저 진행을 한다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 완벽하게 => 반드시 1개 혹은 3개를 가져간다는 의미가 됨... 즉, 돌의 개수가 홀수 = 상근이 승리, 돌의 개수가 짝수 = 창영이 승리
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t9000f9999.p9655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        check();
    }

    static void check() {
        if (N % 2 == 1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
