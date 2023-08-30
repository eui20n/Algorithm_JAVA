/**
 * 문제 이름 : 지름길
 * URL : https://www.acmicpc.net/problem/1446
 * 문제 설명 : 고속도로에 지름길이 있다. 이 때, 운전해야하는 최소값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1446;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int D;
    static int[][] shortcut;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");

        N = Integer.parseInt(tmp[0]);
        D = Integer.parseInt(tmp[1]);

        shortcut = new int[N][3];

        for (int i = 0; i < N; i++) {
            shortcut[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}

// 이거 스위핑 인가? => 약간 그리디 느낌으로 하면 될거 같은데ㅔㅔㅔ

/*
        문제 해결 프로세스
    1. 지름길의 길이 순으로 정렬을 함 => (끝 - 시작) 대비 지름길의 길이가 더 큰 것이 앞에 오게 정렬을 해야함
    1-1. 만약에 지름길의 길이가 같은게 있다면 시작이 더 작은 것으로 정렬을 함 => 이 것은 기본값이라서 별다른 설정을 할 필요 없음
    2. 순서대로 지름길을 둔다.
    3. 이 때, 이미 지름길로 간 길이라면 건너뛴다.
    4. 끝!
 */