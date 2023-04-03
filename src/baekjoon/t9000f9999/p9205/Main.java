/**
 * 문제 이름 : 맥주 마시면서 걸어가기
 * URL : https://www.acmicpc.net/problem/9205
 * 문제 설명 :
 * 맥주 한 박스에는 맥주가 20개 들어있다. 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 즉, 50미터를 가려면 그 직전에 맥주
 * 한 병을 마셔야 한다. 출발점, 도착점, 편의점의 위치가 주어질 때, 도착점으로 갈 수 있는지 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t9000f9999.p9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] total = new int[n + 2][];

            for (int j = 0; j < n + 2; j++) {
                total[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            beerAndWalk(total);
        }
    }

    static void beerAndWalk(int[][] total) {
        int[][] arr = init(total);

        for (int k = 0; k < total.length; k++) {
            for (int i = 0; i < total.length; i++) {
                for (int j = 0; j < total.length; j++) {
                    if (i == j)
                        continue;
                    if (arr[i][j] == 1 || arr[j][i] == 1)
                        continue;
                    if (arr[i][k] == 0 || arr[k][i] == 0)
                        continue;
                    if (arr[k][j] == 0 || arr[j][k] == 0)
                        continue;
                    arr[i][j] = 1;
                    arr[j][i] = 1;
                }
            }
        }
        if (arr[0][total.length - 1] == 1 || arr[total.length - 1][0] == 1) {
            System.out.println("happy");
        } else {
            System.out.println("sad");
        }
    }

    static int[][] init(int[][] total) {
        int[][] arr = new int[total.length][total.length];

        for (int i = 0; i < total.length; i++) {
            for (int j = 0; j < total.length; j++) {
                if (i == j)
                    continue;

                if (check(total[i], total[j]))
                    arr[i][j] = 1;
            }
        }
        return arr;
    }

    static boolean check(int[] place1, int[] place2) {
        int place1X = place1[0];
        int place1Y = place1[1];
        int place2X = place2[0];
        int place2Y = place2[1];

        if (Math.abs(place1X - place2X) + Math.abs(place1Y - place2Y) <= Math.abs(1000)) {
            return true;
        }
        return false;
    }

    static void print(int[][] arr) {
        for (int[] tmp : arr) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}

// 집 -> 편의점 -> 페스티벌
// "갈 수 있다" or "갈 수 없다"만 확인하면 되는 것, 최단 경로가 아님
// 플로이드 워샬