/**
 * 문제 이름 : 파티
 * URL : https://www.acmicpc.net/problem/1238
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, X;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        input();
        check();
    }

    static void check() {
        floyd();
        result();
    }

    static void result() {
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X)
                continue;
            result = Math.max(result, dist[X][i] + dist[i][X]);
        }
        System.out.println(result);
    }

    static void floyd() {
        // 플로이드워샬
        // 경출도

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int z = 1; z <= N; z++) {
                    if (j == z)
                        continue;
                    if (dist[j][i] == Integer.MAX_VALUE || dist[i][z] == Integer.MAX_VALUE)
                        continue;
                    if (dist[j][z] > dist[j][i] + dist[i][z]) {
                        dist[j][z] = dist[j][i] + dist[i][z];
                    }
                }
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];
        X = tmp[2];

        dist = makeArr();
        for (int i = 0; i < M; i++) {
            int[] loc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dist[loc[0]][loc[1]] = loc[2];
        }
    }

    static int[][] makeArr() {
        int[][] returnArr = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                returnArr[i][j] = Integer.MAX_VALUE;
            }
        }
        return returnArr;
    }
}
