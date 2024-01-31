/**
 * 문제 이름 : 숨바꼭질 3
 * URL : https://www.acmicpc.net/problem/13549
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t13000f13999.p13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static int start, end;
    static int[] dx = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputTmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        start = inputTmp[0];
        end = inputTmp[1];

        check();
    }

    static void check() {
        int[] secondArr = makeArr();
        Deque<int[]> q = new ArrayDeque<>();

        q.add(new int[] {start, 0}); // 현재 위치, 시간
        secondArr[start] = 0;
        int result = Integer.MAX_VALUE;

        while (true) {
            if (q.isEmpty())
                break;

            int[] p = q.poll();
            int x = p[0];
            int cost = p[1];

            if (x == end) {
                result = Math.min(result, cost);
                continue;
            }

            for (int z = 0; z < dx.length; z++) {
                // 순간이동을 안한 경우
                int nx = x + dx[z];
                if (checkCondition(secondArr, nx, cost + 1)) {
                    // 순간이동 안한 것 넣기
                    secondArr[nx] = cost + 1;
                    q.add(new int[] {nx, cost + 1});
                }
            }

            // 순간이동을 한 경우
            int nx = x + x;
            if (checkCondition(secondArr, nx, cost) && nx != 0) {
                // 순간이동을 한 것
                secondArr[nx] = cost;
                q.add(new int[] {nx, cost});
            }
        }
        System.out.println(result);
    }

    static boolean checkCondition(int[] secondArr, int nx, int cost) {
        if (0 > nx || nx >= secondArr.length)
            return false;
        if (secondArr[nx] <= cost)
            return false;
        return true;
    }

    static int[] makeArr() {
        int[] returnArr = new int[200001];
        for (int i = 0; i < returnArr.length; i++) {
            returnArr[i] = Integer.MAX_VALUE;
        }
        return returnArr;
    }
}

/*
        다익스트라
        배열의 최대값을 20만으로 한 이유는, 동생의 위치가 최대 10만임... 즉, 현재 위치가 0이면 최대 10만 만큼 소요됨
        => 즉, 순간이동(x2)한 값이 20만을 넘어가는 순간 부터는 무슨 일이 있어도 최솟값이 될 수 없음

        0 - 1 BFS 로 풀이가 된다고 하는데, 어떻게 푸는 거지?
 */