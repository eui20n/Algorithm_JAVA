/**
 * 문제 이름 : 감시 피하기
 * URL : https://www.acmicpc.net/problem/18428
 * 문제 설명 :
 * 장애물을 3개 놔서 선생님의 눈을 피할 수 있는지 체크해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t18000f18999.p18428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static String[][] arr;
    static List<int[]> teachers = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean ans = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new String[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                if (arr[i][j].equals("T")) {
                    teachers.add(new int[] {i, j});
                }
            }
        }

        check();
    }

    static void check() {
        teacherWatch(0, 0, 0);
        System.out.println(ans? "YES" : "NO");
    }

    static void teacherWatch(int x, int y, int cnt) {
        if (ans) {
            return;
        }

        if (cnt == 3) {
            // 기저 조건으로 장애물을 3개 놓으면 확인해야함
            ans = canAvoid();
            return;
        }
        if (x >= N)
            return;

        if (y >= N) {
            teacherWatch(x + 1, 0, cnt);
            return;
        }

        if (arr[x][y].equals("S") || arr[x][y].equals("T") || arr[x][y].equals("O")) {
            // 해당 곳에 장애물을 놓을 수 없음
            teacherWatch(x, y + 1, cnt);
            return;
        }
        arr[x][y] = "O";
        // 해당 곳에 넣은 경우
        teacherWatch(x, y + 1, cnt + 1);
        arr[x][y] = "X";

        // 해당 곳에 놓지 않은 경우
        teacherWatch(x, y + 1, cnt);
    }

    static boolean canAvoid() {
        for (int[] teacher : teachers) {
            for (int z = 0; z < 4; z++) {
                if(!watch(teacher, z))
                    return false;
            }
        }
        return true;
    }

    static boolean watch(int[] teacher, int dir) {
        int x = teacher[0];
        int y = teacher[1];

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (0 > nx || nx >= N || 0 > ny || ny >= N || arr[nx][ny].equals("O") || arr[nx][ny].equals("T"))
                // 학생을 볼 수 없음
                return true;

            if (arr[nx][ny].equals("S"))
                // 학생을 볼 수 있음
                return false;

            x = nx;
            y = ny;
        }
    }
}

// 처음에 선생님이 볼 수 있는 곳을 모두 체크 후, 그 곳에만 장애물을 놓으면 됨