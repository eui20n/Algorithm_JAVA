/**
 * 문제 이름 : 새로운 게임2
 * URL : https://www.acmicpc.net/problem/17837
 * 문제 설명 : 체스판의 말들을 가지고 게임을 만들려고 한다. 크기는 N x N인 체스판에서 진행되고, 사용하는 말의 개수는 K개이다.
 *           하나의 말 위에 다른 말을 올릴 수 있다. 체스판의 각 칸은 흰색, 빨간색, 파란색 중 하나로 칠해져있다.
 *           게임은 체스판 위에 말을 K개를 놓고 시작한다. 말은 1번 부터 K번까지 번호가 매겨져 있고, 이동 방향도 미리 정해져 있다.
 *           이동방향은 위, 아래, 왼쪽, 오른쪽 중 하나이다. 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다. 한 말이 이동할 때
 *           위에 올려져 있는 말도 함께 이동한다. 턴이 진행되던 중에 말이 4개 이상 쌓이면 순간 게임이 종료된다.
 *           A번 말이 이동하려는 칸이
 *              흰색인 경우
 *                  그냥 이동한다.
 *                  이동하려는 칸에 말이 이미 있는 경우 가장 위에 A번 말을 놓는다.
 *                      A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
 *              빨간색인 경우
 *                  이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여 있는 순서를 반대로 바꾼다.
 *                  예를 들면 이동할 칸에 E C B 가 있고 이동하려는 말이 A D F G면 E C B G F D A가 된다.
 *              파란색인 경우
 *                  A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
 *                  방향을 바꾸고 이동하려는 칸이 파란색인 경우 이동하지 않고 가만히 있는다 => 방향만 바뀐다.
 *              체스판을 벗어나는 경우
 *                  파란색과 같은 경우
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t17000f17999.p17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, K;
    static int[][] board;
    static int[][] pieces;
    static List<List<List<Integer>>> state = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        K = Integer.parseInt(tmp[1]);

        board = new int[N][N];
        pieces = new int[K][3];

        for (int i = 0; i < N; i++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < K; i++){
            int[] tmp2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pieces[i][0] = tmp2[0] - 1;
            pieces[i][1] = tmp2[1] - 1;
            pieces[i][2] = tmp2[2];
        }

        newGame();
    }

    static void newGame(){
        move();
    }
    /**
     * 1회 움직이는 함수
     * */
    static void move(){
        for (int i = 0; i < pieces.length; i++) {
            int x = pieces[i][0];
            int y = pieces[i][1];
            int z = pieces[i][2];
        }
    }
}
// 방향 정보
// 1 우
// 2 좌
// 3 상
// 4 하

// 보드에서 흰색 = 0, 빨간 = 1, 파란 = 2
// 말의 입력 [행, 열, 이동방향]

// 3차원 리스트 => 선언하는 것 다른 사람 코드 보기