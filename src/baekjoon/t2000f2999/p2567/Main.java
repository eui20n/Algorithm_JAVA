/**
 * 문제 이름 : 색종이 - 2
 * URL : https://www.acmicpc.net/problem/2657
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] scarfs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scarfs = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            scarfs[i][0] = Integer.parseInt(tmp[0]);
            scarfs[i][1] = Integer.parseInt(tmp[1]);
        }

        check();
    }

    /**
     * 정답을 구해주는 메소드
     * */
    static void check() {
        int[][] scarfArr = makeArr();
        System.out.println(checkZone(scarfArr));
    }

    /**
     * 둘레를 구하는 메소드
     * int[][] arr : 둘레가 궁금한 2차원 배열
     * return : 파라미터로 받은 배열의 둘레
     * */
    static int checkZone(int[][] arr) {
        // 0(흰천)부분과 닿아 있는 스카프의 영역을 4방 탐색을 통해서 구함
        // 1(스카프)인 부분은 넘어가고, 0(흰 천)인 부분에서 스카프랑 닿아있는지 체크
        int zoneCnt = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int x = 0; x < 101; x++) {
            for (int y = 0; y < 101; y++) {
                if (arr[x][y] != 0)
                    continue;

                for (int z = 0; z < 4; z++) {
                    int nx = x + dx[z];
                    int ny = y + dy[z];

                    if (0 > nx || nx >= 101)
                        continue;
                    if (0 > ny || ny >= 101)
                        continue;
                    if (arr[nx][ny] == 1)
                        zoneCnt++;
                }
            }
        }
        return zoneCnt;
    }

    /**
     * 전체 범위에서 스카프가 1이고, 천이 0인 배열을 만들어 주는 메소드
     * return : 스카프가 1이고, 천이 0인 배열 2차원 배열
     * */
    static int[][] makeArr() {
        // 101로 하는 이유는 스카프가 가장 외곽에 있을 경우 문제를 풀이하기 힘들기 때문에 가상의 외곽을 만들어 주기 위해서
        // 문제에서 스카프는 100의 범위를 벗어나지 않기 때문에 가상의 외곽을 만들어도 된다고 판단
        int[][] newArr = new int[101][101];
        for (int[] scarf : scarfs) {
            for (int i = scarf[0]; i < scarf[0] + 10; i++) {
                for (int j = scarf[1]; j < scarf[1] + 10; j++) {
                    newArr[i][j] = 1;
                }
            }
        }

        return newArr;

    }

    /**
     * 배열을 보기 좋은 형태로 프린트 해주는 메소드
     * int[][] arr : int[][]중 print를 하고 싶은 배열
     * */
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
