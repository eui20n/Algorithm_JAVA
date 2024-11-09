/**
 * 문제 이름 : 하늘에서 별똥별이 빗발친다
 * URL : https://www.acmicpc.net/problem/14658
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t14000f14999.p14658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, M, L, K;
    static int[][] starLoc;
    static List<List<Integer>> belongStar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];
        L = tmp[2];
        K = tmp[3];

        starLoc = new int[K][2];
        for (int i = 0; i < K; i++) {
            starLoc[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check();
    }

    static void check() {
        belongStar = makeList(); // 이거 잘 만들어졌는지 이따 확인하기 => 잘되는 듯
        printList(belongStar);

        /*
        *       로직정리
        *   1. 그냥 모두다 탐색해서, 겹치고 있는지 확인하면 됨
        *   2. 즉, 집합이라고 생각하면 편함
        *
        * */

        int result = 0;


    }

    static List<List<Integer>> makeList() {
        // 0번째 인덱스부터 어떤 별이 속하는지 확인하고 list를 만들어주는 메소드
        List<List<Integer>> returnList = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            returnList.add(new ArrayList<>());

            for (int j = 0; j < K; j++) {
                if (i == j)
                    continue;

                int[] standardStar = starLoc[i];
                int[] composeStar = starLoc[j];

                if (Math.abs(standardStar[0] - composeStar[0]) > L)
                    continue;
                if (Math.abs(standardStar[1] - composeStar[1]) > L)
                    continue;
                returnList.get(i).add(j);
            }
        }
        return returnList;
    }

    static void printList(List<List<Integer>> inputList) {
        int idx = 0;
        for (List<Integer> valueList : inputList) {
            System.out.println(idx++ + "번째");
            for (Integer value : valueList) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

// 관점을 별동별로 하면 될듯
// 어차피 탐색으로는 답을 찾을 수 없음 => 배열의 크기가 (50만 x 50만) 임
// (+, +), (+, -), (-, +), (-, -)
// (+, +) 방향에서 시계 방향으로 돌면 될듯 => 이 방법은 시간 초과임
// 이 문제는 모서리 체크도 해야함... 근데 뭘로 해야할지 모르겠네

// 각자 판안에서 갈 수 있는 것들은 다 구한다음, 거기서 겹치는거 찾으면 될듯
// 이거 그냥 집합이네
// 양방향, 단방향 이것만 고려해보기 => 현재 구현은 양방향으로 하고 있음