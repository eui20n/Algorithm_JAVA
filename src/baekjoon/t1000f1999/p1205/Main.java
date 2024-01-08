/**
 * 문제 이름 : 등수
 * URL : https://www.acmicpc.net/problem/1205
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, newScore, rankSize;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        newScore = tmpInput[1];
        rankSize = tmpInput[2];

        if (N == 0) {
            System.out.println(1);
        } else {
            arr = new int[N];
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println(check());
        }
    }

    static int check() {
        Arrays.sort(arr);

        // 등수에 들어갈 수 있는지 확인
        // 등수가 가장 작은 것 보다 본인의 숫자가 더 커야함. 같아도 안됨
        // 근데 칸이 남으면 상관이 없음
        if (N == rankSize) {
            // 가장 작은 것을 비교
            if (arr[0] >= newScore) {
                return -1;
            }
        }

        boolean isFlag = false;
        List<Integer> rankList = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!isFlag && newScore >= arr[i]) {
                rankList.add(newScore);
                isFlag = true;
            }

            if (rankList.size() >= rankSize)
                break;

            rankList.add(arr[i]);
        }

        if (!isFlag)
            rankList.add(newScore);

        // 등수 구하기
        return getRank(rankList);
    }

    static int getRank(List<Integer> rankList) {
        int rank = 1;
        int cnt = 1;
        int lastNum = -1;

        for (int i = 0; i < rankList.size(); i++) {
            int score = rankList.get(i);

            if (score == lastNum) {
                // 이전거랑 등수가 같음 => 등수가 안변함
            } else {
                // 등수를 바꿔주면 됨
                rank = cnt;
            }

            if (score == newScore)
                return rank;

            cnt++;
        }
        return -1;
    }
}
