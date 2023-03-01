/**
 * 문제 이름 : 낚시왕
 * URL : https://www.acmicpc.net/problem/17143
 * 문제 설명 : R x C 격자에 상어가 최대 1마리 들어 있을 수 있다.
 * 낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다. 다음은 1초 동안 일어나는 일이며 낚시왕이 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.
 * 1. 낚시왕은 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 3. 상어가 이동한다.
 * 상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초 이다. 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을
 * 반대로 바꿔서 속력을 유지한채로 이동한다.
 * 상어가 이동을 마친 후에 한 칸에 상어가 2마리 이상 있을 때, 크기가 가장 큰 상어가 나머지 상어를 모두 먹는다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C, M;
    static int[][] sharkInfo; // 입력으로 들어오는 상어의 정보, 처음부터 0번 상어
    static boolean[] deathShark; // 상어의 생사 여부를 관리하는 배열
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int[][] board; // 현재 상어가 있는 곳을 나타내주는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        M = Integer.parseInt(tmp[2]);

        sharkInfo = new int[M][5];
        deathShark = new boolean[M]; // 상어의 수만크 만들어 주면 됨
        board = new int[R][C]; // 해당 배열의 값에는 상어의 번호가 있을 것

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] = -1; // 해당 칸에 상어가 없다면 번호는 -1
            }
        }

        for (int i = 0; i < M; i++) {
            sharkInfo[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x = sharkInfo[i][0] - 1;
            int y = sharkInfo[i][1] - 1;
            board[x][y] = i; // 상어의 번호를 넣어줌
        }
        System.out.println(goFish());
    }

    /**
     * 낚시를 하는 메소드
     * return : 잡은 상어의 총 크기
     * */
    static int goFish() {
        int result = 0;
        int fishKing = -1; // 낚시왕의 위치를 나타내주는 값으로, 초기에는 -1(가장 왼쪽 열의 한 칸 옆)에서 시작

        for (int i = 0; i < C; i++) {
            // 낚시왕이 이동을 한다.
            fishKing += 1;
            // 낚시왕이 본인의 열 중 가까운 물고기를 잡는다.
            result += fishShark(fishKing);
            // 상어가 이동한다.
            movingShark();
        }
        return result;
    }

    /**
     * 상어를 잡는 메소드
     * int c : 낚시왕이 현재 있는 열의 위치
     * return : 잡은 상어의 크기로, 잡은 상어가 없으면 0을 반환하게 만듬
     * */
    static int fishShark(int c) {
        for (int i = 0; i < R; i++) {
            int sharkNum = board[i][c];
            if (sharkNum == -1) // 현재 위치에서 상어의 번호가 -1이면 해당 위치에 상어가 없다는 소리
                continue;
            if (deathShark[sharkNum]) // 이 부분은 사실상 필요 없음 => 이미 죽은 상어는 번호를 남길 수 없음
                continue;
            deathShark[sharkNum] = true; // 해당 번호의 상어를 잡으면 상어 생사 여부 배열에 체크해주기
            return sharkInfo[sharkNum][4]; // 해당 번호의 상어의 사이즈를 반환
        }
        return 0; // 잡은 상어가 없다면 0을 반환
    }

    /**
     * 상어가 움직이는 메소드
     * */
    static void movingShark() {
        int[][] newBoard = new int[R][C]; // 상어의 위치를 관리할 새로운 배열 생성

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newBoard[i][j] = -1; // 위에서 처럼 처음에는 -1로 세팅해줌 => 0으로 해도 되는데, 이렇게 하는게 핸들링하기 쉬움
            }
        }

        for (int sharkNo = 0; sharkNo < sharkInfo.length; sharkNo++) {
            if (deathShark[sharkNo]) // 해당 상어가 죽은 상어면 이동을 시키지 않음
                continue;
            int x = sharkInfo[sharkNo][0] - 1;
            int y = sharkInfo[sharkNo][1] - 1;
            int speed = sharkInfo[sharkNo][2];
            int direction = sharkInfo[sharkNo][3];
            int size = sharkInfo[sharkNo][4];

            if (direction == 1 || direction == 2) {
                // 상어의 속도를 재조정해줌
                // 이렇게 안하면 상어는 계속 같은 위치를 반복하게 됨
                // 즉, 사이클을 한번만 하게 만드는 식
                speed = speed % (2 * (R - 1));
            } else {
                speed = speed % (2 * (C - 1));
            }

            while (true) {
                if (speed == 0) // 속도가 0이 될때까지 반복
                    break;
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (0 > nx || nx >= R) {
                    // 만약에 범위 밖으로 나가면 원래 위치에서 방향을 바꾸고 이동
                    direction = direction == 1 ? 2 : 1;
                    nx = x + dx[direction];
                } else if (0 > ny || ny >= C) {
                    // 만약에 범위 밖으로 나가면 원래 위치에서 방향을 바꾸고 이동
                    direction = direction == 3 ? 4 : 3;
                    ny = y + dy[direction];
                }

                x = nx;
                y = ny;
                speed--;
            }

            if (newBoard[x][y] != -1) {
                // 해당 위치에 다른 상어가 있을 경우
                int otherShark = newBoard[x][y];

                if (size > sharkInfo[otherShark][4]) {
                    // 해당 위치에 있는 상어보가 크기가 크면 해당 위치에 있던 상어를 죽임
                    sharkInfo[sharkNo][0] = x + 1;
                    sharkInfo[sharkNo][1] = y + 1;
                    sharkInfo[sharkNo][3] = direction;
                    deathShark[otherShark] = true; // 해당 위치에 있던 상어의 생사여부를 바꿔줌
                    newBoard[x][y] = sharkNo; // 해당 위치에 현재 상어의 위치를 넣어줌
                } else {
                    // 해당 위치에 있는 상어의 사이즈가 더 큰 경우
                    deathShark[sharkNo] = true; // 현재 상어의 생서여부를 바꿔줌
                }
            } else {
                // 해당 위치에 상어가 없을 경우
                newBoard[x][y] = sharkNo;
                sharkInfo[sharkNo][0] = x + 1;
                sharkInfo[sharkNo][1] = y + 1;
                sharkInfo[sharkNo][3] = direction;
            }
        }
        board = newBoard;
    }
}

/**
 * [문제정리]
 * 구현은 어렵지 않은 문제
 * 하지만, 상어를 그냥 이동시키면 시간 초과가 나서 힘든 문제
 * 상어의 속력을 다시 조정을 하는 것이 핵심!
 * */

