/**
 * 문제 이름 : 도미노 찾기
 * URL : https://www.acmicpc.net/problem/1553
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1553;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] board = new int[8][7];

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < 7; j++) {
                board[i][j] = Integer.parseInt(tmp[j]);
            }
        }
    }
}

// 각자 누워서 2칸 가는 것과 일어서서 2칸 가는 것을 재귀로 들어가면 됨