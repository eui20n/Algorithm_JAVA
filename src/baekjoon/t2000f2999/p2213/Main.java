/**
 * 문제 이름 : 트리의 독립집합
 * URL : https://www.acmicpc.net/problem/2213
 * 문제 설명 : 서로 인접하지 않은 것의 집합을 독립집합 이라고 함, 이때, 가장 큰 독립집합과 요소들을 오름차순으로 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 정석 트리 DP임
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t2000f2999.p2213;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> treeInfo = new ArrayList<>();
        for (int i = 0; i < N; i++){
            treeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++){
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int node1 = tmp[0] - 1;
            int node2 = tmp[1] - 1;

            treeInfo.get(node1).add(node2);
            treeInfo.get(node2).add(node1);
        }
    }


}
// 트리라서 루트가 뭐가 되어도 상관이 없음
// 1번 노드를 루트라고 생각할 것