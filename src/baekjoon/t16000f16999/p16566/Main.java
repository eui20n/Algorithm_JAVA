/**
 * 문제 이름 : 카드 게임
 * URL : https://www.acmicpc.net/problem/16566
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16566;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, K;
    static int[] cardNum;
    static int[] cardGame;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        M = tmpInput[1];
        K = tmpInput[2];

        cardNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cardGame = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

/*
    [방법 1.]
    유니온 파인드
    일단은 다 연결을 함
    그리고 방문처리 배열을 하나만듬
    그래서 값이 들어오면 그 값보다 딱 한 칸 위의 값을 보내줌
    보내준 값을 방문처리해줌
    즉, 방문 처리가 안된 값들 중 가장 작은 값들을 보내주면 됨

    [방법 2.]
    이분 탐색
    예를 들어 4라는 값이 들어오면 4와 가장 가까운 큰 수를 찾음
    그래서 그 수를 출력해줌
    근데, 만갸에 가장 가까운 수가 방문을 했으면, 그 다음 가까운 수를 출력

 */