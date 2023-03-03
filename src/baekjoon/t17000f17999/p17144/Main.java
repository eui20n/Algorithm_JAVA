/**
 * 문제 이름 : 미세먼지 안녕!
 * URL : https://www.acmicpc.net/problem/17144
 * 문제 설명 : 집의 크기가 R x C인 격자에 공기청정기를 두려고 한다. 공기 청정기는 항상 1번열에 위치되어 있고, 크기는 두 행을 차지한다.
 * 1초동안 아래의 일이 일어난다.
 * 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 * - (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 * - 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
 * - 확산되는 양은 Arc/5이고 소수점은 버린다. => Arc는 (r, c)칸에 있는 미세먼지의 양
 * - (r, c)에 남은 미세먼지의 양은 Arc - ((Arc/5) * 확산된 방향의 개수) 이다
 * 2. 공기청정기가 작동한다.
 * - 공기청정기에서는 바람이 나온다.
 * - 위쪽 공기청정기의 바람은 반시계 방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다. => 위쪽 반시계, 아래쪽 시계
 * - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 * - 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
 * 이 때, T초 후에 미세먼지의 양을 출력해라
 * 시간복잡도 : O(4N ^ 2 * M) => O(NM), 단순 연산으로 10_000_000번 연산함
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R, C, T;
    static int[][] arr;
    static int[][] cleaner = new int[2][2];
    static int[] dx = {0, -1, 0, 1, 0, 1, 0, -1}; // 시계 방향, 반시계 방향
    static int[] dy = {1, 0, -1, 0, 1, 0, -1, 0}; // 시계 방향, 반시계 방향
    static int[][] tmpArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        R = Integer.parseInt(tmp[0]);
        C = Integer.parseInt(tmp[1]);
        T = Integer.parseInt(tmp[2]);

        int idx = 0;
        arr = new int[R][C];
        tmpArr = new int[R][C];
        for (int i = 0; i < R; i++) {
            int[] tmp2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < C; j++) {
                if (tmp2[j] == -1) {
                    cleaner[idx][0] = i;
                    cleaner[idx][1] = j;
                    idx++;
                }
                arr[i][j] = tmp2[j];
            }
        }

        goodBye();
    }

    /**
     * 미세먼지 안녕 메소드
     */
    static void goodBye() {
        int[][] directionArr = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                directionArr[i][j] = -1;
            }
        }

        makeDirectionArr(directionArr, "Up", cleaner[0][0], cleaner[0][1]); // 위 공기청정기의 방향 설정(시계방향)
        makeDirectionArr(directionArr, "Down", cleaner[1][0], cleaner[1][1]); // 아래 공기청정기의 방향 설정(반시계방향)


        int time = 0;
        while (true) {
            if (time == T)
                // 시간만큼 하면 종료
                break;
            // 1. 미세먼지 확산
            spread();
            // 2. 공기청정기 작동
            wind(directionArr, cleaner[0][0], cleaner[0][1]); // 위 공기청정기 작동
            wind(directionArr, cleaner[1][0], cleaner[1][1]); // 아래 공기청정기 작동
            time++;
        }

        System.out.println(result());

    }

    /**
     * 남아 있는 미세먼지의 양을 더해주는 함수
     * return : 남아 있는 미세 먼지
     * */
    static int result() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == -1)
                    continue;
                sum += arr[i][j];
            }
        }
        return sum;
    }

    /**
     * 바람이 부는 메소드
     * int[][] directionArr : 미리 설정했던 방향 배열
     * int startX : 시작의 X 값
     * int startY : 시작의 Y 값
     */
    static void wind(int[][] directionArr, int startX, int startY) {
        int x = startX;
        int y = startY + 1;
        int tmp = arr[x][y];
        arr[x][y] = 0;

        while (true) {
            int z = directionArr[x][y];
            int nx = x + dx[z];
            int ny = y + dy[z];

            if (arr[nx][ny] == -1)
                break;

            // 미세먼지 이동을 안해주는 것
            // 이렇게 안해도 되는데, 이렇게 하고 싶었음
            arr[nx][ny] = tmp + arr[nx][ny];
            tmp = arr[nx][ny] - tmp;
            arr[nx][ny] = arr[nx][ny] - tmp;

            x = nx;
            y = ny;
        }
    }

    /**
     * 미세먼지가 퍼지는 메소드
     */
    static void spread() {
        // 확산
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (arr[x][y] == 0)
                    continue;
                if (arr[x][y] == -1)
                    continue;

                int spreadNum = arr[x][y] / 5; // 확산될 양
                int spreadCnt = 0; // 몇번 확산됬는지

                for (int z = 0; z < 4; z++) {
                    int nx = x + dx[z];
                    int ny = y + dy[z];

                    if (0 > nx || nx >= R)
                        continue;
                    if (0 > ny || ny >= C)
                        continue;
                    if (arr[nx][ny] == -1)
                        continue;
                    spreadCnt += 1;
                    tmpArr[nx][ny] += spreadNum;
                }
                tmpArr[x][y] -= spreadNum * spreadCnt; // 원래 먼지 - 확산된 양
            }
        }

        // 합치기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == -1){
                    continue;
                }
                arr[i][j] += tmpArr[i][j]; // 기존에 배열에 합쳐주기
                tmpArr[i][j] = 0; // 0으로 해줘서, 해당 배열을 새로 만들기 보다는 계속해서 재사용
            }
        }
    }

    /**
     * 미리 공기청정기의 방향을 표시해주는 메소드
     * int[][] arr : 방향을 담을 배열
     * String check : 위인지 아래인지 알려주기 위한 파라미터
     * int startX : 시작의 X 값
     * int startY : 시작의 Y 값
     * */
    static void makeDirectionArr(int[][] arr, String check, int startX, int startY) {
        int dir = check.equals("Up") ? 0 : 4; // up이면 시계방향, 아니면 반시계방향
        int z = 0;
        int x = startX;
        int y = startY;

        while (true) {
            int nx = x + dx[z + dir];
            int ny = y + dy[z + dir];

            if (0 > nx || nx >= R || 0 > ny || ny >= C) {
                // 범위를 벗어나면 방향을 바꿔줌
                z = z + 1 == 4 ? 0 : z + 1;
                continue;
            }
            if (nx == startX && ny == startY) {
                arr[x][y] = z + dir;
                return;
            }
            arr[x][y] = z + dir; // 해당 방향을 배열에 넣어줌
            x = nx;
            y = ny;
        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
        정리
    1. 이렇게 방향이 고정되어 있는 문제일 경우 나는 방향을 미리 설정을 해줌
    1-1. 이렇게 방향을 미리 설정해서 하는 것이 나는 더 편했음 => 범위를 생각안하고 그냥 하면 됨
    2. 미세먼지가 동시에 퍼지기 때문에 퍼질 때 다른 배열을 만들어서 관리
    3. 그 외는 구현을 하면 됨
*/