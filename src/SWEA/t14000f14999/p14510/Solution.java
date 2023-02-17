/**
 * 문제 이름 : 나무 높이
 * 문제 번호 : 14510
 * 문제 설명 : 모든 나무가 초기에 가장 높았던 나무의 높이가 되려면 최소 며칠이 걸리는지 출력해라
 * 홀수 날에는 물을 주면 1만큼 높아지고, 짝수날에는 2만큼 높아진다. 물을 안주는 날이 있어도 된다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t14000f14999.p14510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] woods = new int[N];

            String[] tmp = br.readLine().split(" ");

            int maxWood = Integer.MIN_VALUE;
            for (int j = 0; j < N; j++) {
                int tmpWood = Integer.parseInt(tmp[j]);
                woods[j] = tmpWood;
                maxWood = Math.max(tmpWood, maxWood);
            }

            System.out.printf("#%d %d %n", i, check(woods, maxWood));
        }
    }

    static int check(int[] woods, int maxWood) {
        int day = 1;
        List<Integer> odd;
        List<Integer> even;

        while (true) {
            odd = new ArrayList<>();
            even = new ArrayList<>();
            for (int i = 0; i < woods.length; i++) {
                if (woods[i] == maxWood) {
                    continue;
                }
                if ((maxWood - woods[i]) % 2 == 0) {
                    even.add(i);
                } else {
                    odd.add(i);
                }
            }

            if (odd.isEmpty() && even.isEmpty()) {
                break;
            }

            int woodIdx;
            if (day % 2 == 0) {
                // 짝수날에
                if (!(even.isEmpty())) {
                    // 남은 높이가 짝수가 있는 경우
                    woodIdx = even.get(0);
                    woods[woodIdx] += 2;
                } else {
                    // 남은 높이가 짝수가 없는 경우
                    for (Integer tmpNum : odd) {
                        woodIdx = tmpNum;
                        if (maxWood - woods[woodIdx] > 2) {
                            // 그럴떄, 그 경우가 2보다 큰 경우
                            woods[woodIdx] += 2;
                            break;
                        }
                    }
                }
            } else {
                // 홀수날에
                if (odd.isEmpty()) {
                    // 남은 높이가 홀수가 없는 경우
                    for (Integer tmpNum : even) {
                        woodIdx = tmpNum;
                        if (maxWood - woods[woodIdx] > 2) {
                            // 그럴때, 그 경우가 2보다 큰 경우
                            woods[woodIdx] += 1;
                            break;
                        }
                    }
                } else {
                    // 남은 노피가 홀수가 있는 경우
                    woodIdx = odd.get(0);
                    woods[woodIdx] += 1;
                }

            }
            day++;
        }
        return day - 1;
    }
}

// 정리
// 짝수인 날에 물을 주는 경우
// 1. 나무의 남은 높이가 짝수가 있는 경우
// 2. 나무의 남은 높이가 짝수는 없는데 홀수가 있는 경우 => 이때, 홀수의 남은 높이는 2보다 커야함
// 홀수인 날에 물을 주는 경우
// 1. 나무의 남은 높이가 홀수인 경우
// 2. 나무의 남은 높이가 홀수는 없는데 짝수가 있는 경우 => 이때, 짝수의 높이가 2보다 커야함
// 해당 문제는 그리디로 풀이가 되는 문제
// 즉, 남은 높이를 정렬을 할 필요 없이 가장 먼저 만나는 경우를 조건에 맞게 처리해주면 됨