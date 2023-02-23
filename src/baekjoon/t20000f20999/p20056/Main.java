/**
 * 문제 이름 : 마법사 상어와 파이어볼
 * URL : https://www.acmicpc.net/problem/20056
 * 문제 설명 : N x N인 격자에 파이어볼 M개를 발사했다. 파이어볼에는 질량, 방향, 속력과 현재의 위치정보가 있다.
 *           또한, 1번 행과 열은 N행과 열에 연결되어 있다(범위를 벗어나는 경우가 없다)
 *           파이어볼이 이동하면 다음 일들이 일어난다.
 *           1. 모든 파이어볼이 자신의 방향으로 속력만큼 이동한다.
 *              - 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
 *           2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
 *              1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
 *              2. 파이어볼은 4개의 파이어볼로 나누어진다.
 *              3. 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
 *                  1. 질량은 (합져진 파이어볼 질량의 합) / 5 의 내림 => 다시 알아보기
 *                  2. 속력은 (합져진 파이어볼 속력의 합) / 합쳐진 파이어볼의 개수
 *                  3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다
 *              4. 질량이 0인 파이어볼은 소멸되어 없어진다.
 *          파이어볼의 이동 명령을 K번 후 남아 있는 파이어볼 질량의 합을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 구현
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t20000f20999.p20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, M ,K;
    static List<int[]> fireBall;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        K = Integer.parseInt(tmp[2]);

        fireBall = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            fireBall.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
    }

    static void check() {
        List<List<List<Integer>>> board = makeList();

    }

    static List<List<List<Integer>>> makeList() {
        List<List<List<Integer>>> tmp = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tmp.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                tmp.get(i).add(new ArrayList<>());
            }
        }
        int idx = 0;
        for (int[] fire : fireBall) {
            int x = fire[0] - 1;
            int y = fire[1] - 1;
            tmp.get(x).get(y).add(idx++);
        }

        return tmp;
    }
}

// 표현이 상당히 까다롭네
// 자료구조좀 생각하고 다시 풀 것
// 따로 배열(리스트)를 만들지 않고 주어진 거로 바로 하기
// 이동이 끝나면 정렬을 해서 체크를 하기
// 각각의 객체를 담아줄 클래스를 만들어서 하기