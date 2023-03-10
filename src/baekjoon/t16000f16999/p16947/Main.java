/**
 * 문제 이름 : 서울 지하철 2호선
 * URL : https://www.acmicpc.net/problem/16947
 * 문제 설명 :
 * 순환선 => 한 역에서 출발해서 다시 출발한 역으로 돌아올 수 있는 노선
 * 지선 => 순환선에 속하는 한 역에서 시작하는 트리 형태의 노선
 * 역 A와 순환선 사이의 거리는 A와 순환선에 속하는 역 사이의 거리중 최솟값이다.
 * 지하철 2호선과 같은 형태의 노선도가 주어졌을 때, 각 역과 순환선 사이의 거리를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t16000f16999.p16947;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static List<List<Integer>> subway;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        subway = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            subway.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            int subway1 = Integer.parseInt(tmp[0]);
            int subway2 = Integer.parseInt(tmp[1]);

            subway.get(subway1).add(subway2);
            subway.get(subway2).add(subway1);
        }
        print(subway);
    }

    static void print(List<List<Integer>> list) {
        for (List<Integer> tmp : list) {
            System.out.println(Arrays.toString(tmp.toArray()));
        }
    }
}

// 순환선 찾기
// 그 사이의 거리 구하기