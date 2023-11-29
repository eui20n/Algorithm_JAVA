/**
 * 문제 이름 : MooTube (Silver)
 * URL : https://www.acmicpc.net/problem/15591
 * 문제 설명 :
 * 두 동영상이 서로 얼마나 가까운지 측정한 단위 "USADO"가 있다. 각각의 동영상 쌍에는 "USADO"가 있다.
 * 또한, 어느 동영상을 선택하든 모든 동영상과 연결이 되어 있다. 직접 연결되어 있지 않은 동영상의 유사도는 최소값을 따라간다.
 * 이 때, 유사도가 K 이상인 동영상이 N 동영상에서 몇 개 있는지 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t15000f15999.p15591;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static int Q;
    static int[][] usadoArr;
    static int[][] questArr;
    static List<List<Integer>> wholeArr;
    static int[][] videoUsadoArr = new int[5000][5000]; // 메모리 초과하면 입력에 맞는 값으로 하기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        Q = Integer.parseInt(tmp[1]);

        usadoArr = new int[N - 1][3];
        questArr = new int[Q][2];
        wholeArr = makeList();

        for (int i = 0; i < N - 1; i++) {
            usadoArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

            int video1 = usadoArr[i][0];
            int video2 = usadoArr[i][1];
            int usado = usadoArr[i][2];

            wholeArr.get(video1).add(video2);
            wholeArr.get(video2).add(video1);
            videoUsadoArr[video1][video2] = usado;
            videoUsadoArr[video2][video1] = usado;
        }
    }

    static List<List<Integer>> makeList() {
        List<List<Integer>> returnList = new ArrayList<>();

        // 메모리 초과하면 입력에 맞는 값 사용하기
        for (int i = 0; i < 5000; i++) {
            returnList.add(new ArrayList<>());
        }
        return returnList;
    }
}

/*
    완전 탐색을 해도 될 것 같은데...
    분명히 더 좋은 방법이 있을 것 같은데...
    => 가능하면 입력의 크기가 현재 문제 보다 20배 더 큰 문제를 해결 할 수 있는 코드르 짜보자
    => 그래프를 그리면서 해당 값이 몇인지 계속 기록하기
    => 이 문제의 핵심을 그래프를 어떻게 O(N^2) 이하로 그리는가 임
 */