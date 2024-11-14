/**
 * 문제 이름 : 가희와 탑
 * URL : https://www.acmicpc.net/problem/24337
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t24000f24999.p24337;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int leftWatch;
    static int rightWatch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmpInput = br.readLine().split(" ");
        N = Integer.parseInt(tmpInput[0]);
        leftWatch = Integer.parseInt(tmpInput[1]);
        rightWatch = Integer.parseInt(tmpInput[2]);

        check();
    }

    static void check() {
        /*
                만들어야 하는 메소드
            1. 탑 보는 메소드 => 완료
            2. 사전순으로 가장 먼저 등장하는 탑의 모양
         */

    }

    static void topShape() {
        /*
                사전순으로 가장 먼저 등장하는 것을 출력하는 것이기 때문에
                시작 숫자는 1이 고정임 => 2가 될 수 없음 (기저 조건으로 활용)
         */
    }

    static boolean watchTop(int[] topArr) {
        /*
                일단은 for문 2개써서 만들고, 가능하면 나중에 합치기
         */
        int leftTopCnt = 0;
        int rightTopCnt = 0;

        // 왼쪽
        int leftTop = 0;
        for (int i = 0; i < N; i++) {
            if (topArr[i] > leftTop) {
                leftTopCnt += 1;
                leftTop = topArr[i];
            }
        }

        // 오른쪽
        int rightTop = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (rightTop == leftTop) {
                // leftTop은 해당 배열에서 가장 큰 값임... 즉 rightTop과 leftTop이 같다는 것은 제일 큰 값이고, 더 이상 볼 수 있는 탑은 없음
                break;
            }
            if (topArr[i] > rightTop) {
                rightTopCnt += 1;
                rightTop = topArr[i];
            }
        }
        if (leftTopCnt == leftWatch && rightTopCnt == rightWatch) {
            return true;
        }
        return false;
    }
}

/*
        가희와 단비가 왼쪽, 오른쪽에 있다.
        각각 탑을 보고 있다.
        각가 보고있는 탑의 수와 총 탑의 개수가 주어질 때, 이 탑들은 높이를 사전순으로 가장 먼저 등장하는 것을 구해라.
        각각 탑은 앞에 있는 것 보다 더 큰 탑만 볼 수 있다. 예를 들면 탑의 높이가 1 2 3 이라고 한다면, 가희는 3개, 단비는 1개 볼 수 있다.
 */