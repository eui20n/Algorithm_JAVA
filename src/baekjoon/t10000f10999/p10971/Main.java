/**
 * 문제 이름 : 외판원 순회2
 * URL : https://www.acmicpc.net/problem/10971
 * 문제 설명 : 외판원 순회에 필요한 최소 비용을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t10000f10999.p10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] arr;
    static int result = Integer.MAX_VALUE;
    static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        end = (int) Math.pow(2, N);

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check(0, 1, 0);
        System.out.println(result);
    }

    static void check(int x, int cnt, int value) {
        // 만약에 1로 가는 경우가 없다면 가면 안됨
        if (cnt == end - 1 && arr[x][0] != 0) {
            result = Math.min(result, value + arr[x][0]);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((cnt & (1 << i)) > 0)
                continue;
            if (arr[x][i] == 0) // 갈 수 없는 경우 가면 안됨
                continue;
            check(i, cnt | (1 << i), value + arr[x][i]);
        }
    }
}
// 결국 1번에서 시작해서 모든 도시를 방문 후 1번으로 와야함
// 그럼 1번으로 오는 경우는 보지않을 것
// 1번에서 출발해서 모든 도시를 방문하는 경우의 최소를 구하고, 1로 오는 가장 짧은 것을 붙여주면 될 듯