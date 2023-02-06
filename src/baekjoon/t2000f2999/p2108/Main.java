/**
 * 문제 이름 : 통계학
 * URL : https://www.acmicpc.net/problem/2108
 * 문제 설명 : 산술평균(소수점 이하 첫째 자리에서 반올림), 중앙값, 최빈값(여러개면 두 번째로 작은 값 출력), 범위를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2108;

import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        check();
    }

    static void check() {
        int sum = 0;
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        int centerIdx = arr.length / 2;

        int centerNum = 0;

        Arrays.sort(arr);

        Map<Integer, Integer> cntNum = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int num = arr[i];

            if (centerIdx == i)
                centerNum = num;

            sum += num;
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);

            if (cntNum.containsKey(num)) {
                int cnt = cntNum.get(num);
                cntNum.replace(num, ++cnt);
            } else {
                cntNum.put(num, 1);
            }

        }

        double avg = getAvg(sum);
        int mode = getMode(cntNum);
        int range = getRange(maxNum, minNum);

        System.out.printf("%.0f%n%d%n%d%n%d", avg, centerNum, mode, range);
    }

    static double getAvg(int num) {
        return (double) num / N;
    }

    static int getRange(int maxNum, int minNum) {
        return maxNum - minNum;
    }

    static int getMode(Map<Integer, Integer> cntNum) {
        // modeCnt 이거 다시 생각해야됨
        int mode = 0;
        List<Integer> modeNum = new ArrayList<>();
        int modeCnt = 1;

        for (Integer key : cntNum.keySet()) {
            int keyMode = cntNum.get(key);
            if (mode < keyMode) {
                mode = keyMode;
                modeCnt = 1;
            } else if (mode == keyMode) {
                modeCnt++;
            }
        }

        for (Integer key : cntNum.keySet()) {
            if (mode == key) {
                modeNum.add(key);
            }
        }

        Collections.sort(modeNum);

        if (modeNum.size() >= 2) {
            return modeNum.get(modeNum.size() - 2);
        }
        return modeNum.get(0);

    }
}
