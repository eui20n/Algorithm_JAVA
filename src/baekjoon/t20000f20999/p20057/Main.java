/**
 * 문제 이름 : 마법사 상어와 토네이도
 * URL : https://www.acmicpc.net/problem/20057
 * 문제 설명 : 마법사 상어가 토네이도를 배워서 토네이도를 쓴다. 이 때, 모래가 밖으로 나가는 양을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 단순 (화나는)구현
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] sandBoard;
    static int[][] tornado = new int[5][5];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sandBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            sandBoard[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        magic();
        System.out.println(result);
    }

    static void magic() {
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        int[][] board = new int[N][N];

        int[][] directionArr = makeDirectionArr(0, 0, 0, visited, board);
        print(directionArr);
        makeTornado();
        goTornado(directionArr);

    }

    static void goTornado(int[][] arr){
        int x = N / 2;
        int y = N / 2;

        while (true){
            if (0 > x || 0 > y){
                break;
            }
            print(sandBoard);

            int z = arr[x][y];
            int[][] newTornado;

            if (z == 0){
                newTornado = tornado;
            } else if (z == 1) {
                newTornado = rotate90(tornado);
            } else if (z == 2) {
                newTornado = rotate180(tornado);
            } else {
                newTornado = rotate270(tornado);
            }

            int nx = x + (dx[z] * -1);
            int ny = y + (dy[z] * -1);

            int sandAmount = sandBoard[nx][ny];
            sandBoard[x][y] = 0;
            magicTornado(nx, ny, z, sandAmount, newTornado);

            x = nx;
            y = ny;
        }

    }

    static void magicTornado(int nx, int ny, int z, int sandAmount, int[][] arr){
        // 방향만 정해주면 모래를 기준으로 퍼지는 것
        int[][] sand = new int[5][5];

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (arr[i][j] == 0)
                    continue;
                sand[i][j] += sandAmount / arr[i][j] * 0.01;
            }
        }

//        for (int i = nx - 1; i < 5 - nx - 1; i++){
//            for (int j = ny - 1; j < 5 - ny - 1; j++) {
//                if (0 > i || i >= N || 0 > j || j >= N) {
//                    result += sand[i - nx + 1][j - ny + 1];
//                    sandAmount -= sand[i - nx + 1][j + ny + 1];
//                } else {
////                    System.out.printf("%d %d %d %d %n", i, j, nx, ny);
//                    sandBoard[i][j] += sand[i - nx + 1][j - ny + 1];
//                }
//            }
//            sandBoard[nx + (dx[z] * -1)][ny + (dy[z] * -1)] += sandAmount;
//        }
    }

    static int[][] rotate90(int[][] arr) {
        int[][] newArr = new int[5][5];

        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 5; y++){
                newArr[x][y] = arr[5 - 1 - y][x];
            }
        }

        return newArr;
    }

    static int[][] rotate180(int[][] arr) {
        int[][] newArr = new int[N][N];

        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 5; y++){
                newArr[x][y] = arr[5 - 1 - x][5 - 1 - y];
            }
        }

        return newArr;
    }

    static int[][] rotate270(int[][] arr) {
        int[][] newArr = new int[5][5];

        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 5; y++){
                newArr[x][y] = arr[y][5 - 1 - x];
            }
        }

        return newArr;
    }

    static int[][] makeDirectionArr(int x, int y, int z, boolean[][] visited, int[][] board) {
        if (x == N / 2 && y == N / 2) {
            return board;
        }

        int nx = x + dx[z];
        int ny = y + dy[z];

        if (0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny]) {
            return makeDirectionArr(x, y, z == 3 ? 0 : z + 1, visited, board);
        }
        visited[nx][ny] = true;
        board[nx][ny] = z;
        return makeDirectionArr(nx, ny, z, visited, board);
    }

    static void makeTornado(){
        tornado[0][2] = 2;
        tornado[4][2] = 2;
        tornado[1][1] = 10;
        tornado[3][1] = 10;
        tornado[1][2] = 7;
        tornado[3][2] = 7;
        tornado[1][3] = 1;
        tornado[3][3] = 1;
        tornado[2][0] = 5;
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

    static void print(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 토네이도를 통해서 밖으로 나가는 양의 모래판도 배열로 관리
// 방향 배열을 미리 만들어서 구현할 것
// 90도 돌리는 것을 하나만 구현 해서 180, 270도는 90도 돌리는 것을 활용하기
// or 90도 돌리는 것과, 270도(-90도)도 구현하고, 180도는 90도로 돌리기
// a로 이동하는 모래의 양은 버려진 전체에서 이동하고 남은 양 => 45가 아니고 소수점 버려진 값도 있어서 조금은 큼
// 날아가는 모래의 양을 계속해서 돌릴꺼라서 정사각형인게 편함
// 반올림을 하는게 아니라 버리는 것