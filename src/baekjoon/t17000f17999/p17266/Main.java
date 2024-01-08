/**
 * 문제 이름 : 어두운 굴다리
 * URL : https://www.acmicpc.net/problem/17266
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 매개변수 탐색
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int lampCnt;
    static int[] lampLocation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lampCnt = Integer.parseInt(br.readLine());
        lampLocation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        System.out.println(binarySearch(1, 100001));
    }

    static int binarySearch(int start, int end) {
        while (true) {
            if (start >= end)
                return start;

            int mid = (start + end) / 2;
            boolean height = light(mid);

            if (height) {
                // 현재 높이에서 가능하니까 높이를 줄여야함
                // 근데 현재 높이는 유지해야함
                end = mid;
            } else {
                // 현재 높이에서는 불가능하니 높이를 늘려야함
                // 근데 현재 높이는 버려야함
                start = mid + 1;
            }
        }
    }

    static boolean light(int height) {
        // 현재 높이에서 비출 수 있는지 확인하기
        // 빛출 수 있는지 확인은 길 전체가 가려지는지 확인하기
        int postEnd = -1;

        for (int i = 0; i < lampLocation.length; i++) {
            int lamp = lampLocation[i];

            int startLamp = lamp - height;
            int endLamp = lamp + height;

            if (i == 0) {
                // 시작일 때
                if (startLamp > 0) {
                    // 현재 높이에서는 못함
                    return false;
                }
            } else {
                // 중간 부분
                if (startLamp > postEnd) {
                    // 현재 높이에서 못함
                    return false;
                }
            }
            if (i == lampLocation.length - 1) {
                // 끝일 때
                if (endLamp < N) {
                    // 현재 높이에서 못함
                    return false;
                }
            }
            postEnd = endLamp;
        }
        return true;
    }
}
