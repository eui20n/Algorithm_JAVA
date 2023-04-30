/**
 * 문제 이름 :
 * URL : https://www.acmicpc.net/problem/
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class start {
    static int N, M;
    static List<List<Integer>> info;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        info = makeList(N);

        for (int i = 1; i <= M; i++) {
            String[] tmp2 = br.readLine().split(" ");
            int floor = Integer.parseInt(tmp2[0]);
            int addPoint = Integer.parseInt(tmp2[1]);

            for (int j = floor; j <= N; j =  j + addPoint) {
                info.get(j).add(i);
            }
        }

        String[] tmp3 = br.readLine().split(" ");
        start = Integer.parseInt(tmp3[0]);
        end = Integer.parseInt(tmp3[1]);

        for (int i = 0; i < info.size(); i++) {
            if (info.get(i).size() == 0)
                continue;
            System.out.println(i);
            System.out.println(Arrays.toString(info.get(i).toArray()));
        }
    }

    static List<List<Integer>> makeList(int size) {
        List<List<Integer>> tmp = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            tmp.add(new ArrayList<>());
        }
        return tmp;
    }
}

// 자기가 갔던 층은 더 이상 안가도 됨
// 현재 층에서 갈 수 있는 엘리베이터를 봐야함
// 현재 엘리베이터가 갈 수 있는 곳을 보는 것이 아니라