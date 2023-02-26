/**
 * 문제 이름 : 상호의 배틀필드
 * 문제 번호 : 1873
 * 문제 설명 : 입력으로 주어지는 명령을 수행하고 난 후의 필드가 어떤지 출력하면 됨
 * . 평지
 * * 벽돌
 * # 강철
 * - 물
 * ^ 위쪽을 바라보는 전차
 * v 아래쪽을 바라보는 전차
 * < 왼쪽을 바라보는 전차
 * > 오른쪽을 바라보는 전차
 * U 바라보는 방향을 위로 바꾸고 평지라면 한칸 이동
 * D 바라보는 방향을 아래로 바꾸고 평지라면 한칸 이동
 * L 바라보는 방향을 왼쪽으로 바꾸고 평지라면 한칸 이동
 * R 바라보는 방향을 오른쪽으로 바꾸고 평지라면 한칸 이동
 * S 현재 바라보고 있는 방향으로 포탄 발사
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int T;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            String[] tmp = br.readLine().split(" ");
            int R = Integer.parseInt(tmp[0]);
            int C = Integer.parseInt(tmp[1]);

            String[][] board = new String[R][C];
            int[] tankInfo = new int[3];
            for (int j = 0; j < R; j++) {
                board[j] = br.readLine().split("");
                for (int k = 0; k < board[0].length; k++) {
                    if (board[j][k].equals("^")) {
                        // 위는 방향 0으로 설정
                        tankInfo[0] = j;
                        tankInfo[1] = k;
                        tankInfo[2] = 0;
                    } else if (board[j][k].equals("v")) {
                        // 아래는 방향 1로 설정
                        tankInfo[0] = j;
                        tankInfo[1] = k;
                        tankInfo[2] = 1;
                    } else if (board[j][k].equals("<")) {
                        // 왼쪽은 방향 2로 설정
                        tankInfo[0] = j;
                        tankInfo[1] = k;
                        tankInfo[2] = 2;
                    } else if (board[j][k].equals(">")) {
                        // 오른쪽은 방향 3으로 설정
                        tankInfo[0] = j;
                        tankInfo[1] = k;
                        tankInfo[2] = 3;
                    }
                }
            }
            br.readLine(); // 입력으로 들어오는 명령의 수로 필요 없음
            String[] commands = br.readLine().split("");

            battleField(board, commands, tankInfo);
            System.out.printf("#%d ", i);
            print(board);
        }
    }

    static void battleField(String[][] board, String[] commands, int[] tankInfo) {
        // if문으로 처리하기 싫어서 맵에 넣고 해당 방향의 명령이 오면 바로 그 방향에 맞는 int값이 나오게 만듬
        Map<String, Integer> dirInfo = new HashMap<>();
        dirInfo.put("U", 0);
        dirInfo.put("D", 1);
        dirInfo.put("L", 2);
        dirInfo.put("R", 3);

        for (String command : commands) {
            if (command.equals("S")) {
                // 포탄을 쏠 때
                shoot(board, tankInfo);
            } else {
                // 그 외의 경우는 주어지는 방향으로 가는 경우
                // 방향은 위에 Map을 통해서 명령의 문자를 바로 int로 변경
                go(board, tankInfo, dirInfo.get(command));
            }
        }
    }

    /**
     * 포탄을 쏘는 메소드
     * */
    static void shoot(String[][] board, int[] tankInfo) {
        int x = tankInfo[0];
        int y = tankInfo[1];
        int z = tankInfo[2];

        while (true) {
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (0 > nx || nx >= board.length)
                return;
            if (0 > ny || ny >= board[0].length)
                return;
            if (board[nx][ny].equals("#"))
                return;
            if (board[nx][ny].equals("*")) {
                // 벽을 부셨기 때문에 부순 곳은 평지로 바뀜
                board[nx][ny] = ".";
                return;
            }
            x = nx;
            y = ny;
        }
    }

    /**
     * 방향에 맞게 한칸 가는 메소드
     * */
    static void go(String[][] board, int[] tankInfo, int dir) {
        String[] direction = {"^", "v", "<", ">"};

        int x = tankInfo[0];
        int y = tankInfo[1];

        // 현재 탱크의 값을 변경해줌
        tankInfo[2] = dir;
        board[x][y] = direction[dir];

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (0 > nx || nx >= board.length)
            return;
        if (0 > ny || ny >= board[0].length)
            return;
        if (board[nx][ny].equals(".")) {
            // 만약에 다음에 갈 수 있는 경우면 현재의 경우에서 이동을 해줌
            tankInfo[0] = nx;
            tankInfo[1] = ny;
            board[nx][ny] = direction[dir];
            board[x][y] = ".";
        }
    }

    /**
     * 출력을 하는 메소드
     * */
    static void print(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
