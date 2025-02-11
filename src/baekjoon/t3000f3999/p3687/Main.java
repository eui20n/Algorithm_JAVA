/**
 * 문제 이름 : 성냥개비
 * URL : https://www.acmicpc.net/problem/3687
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3687;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int matchesCnt = Integer.parseInt(br.readLine());

            check(matchesCnt);
        }
    }

    static void check(int matchesCnt) {
        // 정답을 구해주는 메소드
        String[] maxArr = makeStringArr();
        long[][] minArr = makeMinArr();

        System.out.println(minArr[matchesCnt][0] + " " + maxArr[matchesCnt]);
    }

    static long[][] makeMinArr() {
        long[][] returnArr = initialArr();

        for (int i = 8; i < returnArr.length; i++) {
            for (int j = 2; j <= i - 2; j++) {
                // preNum와 sufNum로 구현했기 때문에 절반만 가는 것이 아니라 끝까지 가야함
                // 근데 여기서 i - 2 까지 가는 이유는, i - 1, i 즉, 1과 0으로 표현 가능한 숫자가 없기때문임
                long num = makeNum(j, i - j, returnArr);

                if (num < returnArr[i][0] || returnArr[i][0] == 0) {
                    // 값 갱신
                    returnArr[i][0] = num;
                    returnArr[i][1] = returnArr[j][1] + returnArr[i - j][1];
                }
            }
        }
        return returnArr;
    }

    static long makeNum(int preNum, int sufNum, long[][] arr) {
        if (arr[preNum][1] + arr[sufNum][1] > 64) {
            // 만약 만들어질 숫자가 long범위를 벗어나면 바로 종료하기
            return Long.MAX_VALUE;
        }

        StringBuilder sb = new StringBuilder();

        // preNum가 앞에, sufNum가 뒤에 붙기
        // 이렇게 하는 이유는 로직을 간단하게 하기 위해서
        // 이렇게 안하면 두개를 바꾸고, 6이면 0으로 바꾸는 등 로직이 복잡해짐
        sb.append(arr[preNum][0]);
        if (sufNum == 6) {
            sb.append(0);
        } else {
            sb.append(arr[sufNum][0]);
        }
        return Long.parseLong(sb.toString());
    }

    static String[] makeStringArr() {
        String[] returnArr = new String[101];

        for (int i = 2; i < returnArr.length; i++) {
            if (i % 2 == 0) {
                int oneCnt = i / 2;
                returnArr[i] = makeMaxNumToString(oneCnt, 0);
            }

            if (i % 2 == 1) {
                int oneCnt = (i - 3) / 2;
                returnArr[i] = makeMaxNumToString(oneCnt, 1);
            }
        }

        return returnArr;
    }

    static long[][] initialArr() {
        // 기본 배엶 만들기
        long[][] returnArr = new long[101][2];

        returnArr[2][0] = 1;
        returnArr[3][0] = 7;
        returnArr[4][0] = 4;
        returnArr[5][0] = 2;
        returnArr[6][0] = 6;
        returnArr[7][0] = 8;

        returnArr[2][1] = 1;
        returnArr[3][1] = 1;
        returnArr[4][1] = 1;
        returnArr[5][1] = 1;
        returnArr[6][1] = 1;
        returnArr[7][1] = 1;

        return returnArr;
    }

    static String makeMaxNumToString(int oneCnt, int sevenCnt) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sevenCnt; i++) {
            sb.append("7");
        }

        for (int i = 0; i < oneCnt; i++) {
            sb.append("1");
        }

        return sb.toString();
    }
}

/*
        최대값 로직
    1. 수를 가장 적게 사용해야 최대값을 갈 수 있음
    2. 즉, 가장 적게 사용하는 것 중 가장 큰 값을 사용하면 됨
    3. 위 2가지를 고려하면 1과 7이 남음
    4. 그 후, 짝수인 것은 가능한 1을 다 해주면 되고, 홀수이면 가장 앞에 7을 붙이고 나머지는 1을 붙이면 됨
    5. 최대값은 대충 계산해도 long의 범위를 벗어남. 즉, 비교 연산을 하는 것이 아닌 정해진 방법으로 한다는 것을 알 수 있음

        최소값 로직
    1. 최소값중 가장 큰 값은 long범위를 벗어나지 않음... 즉, 비교를 할 수 있음
    2. 기본 수를 구하기
    3. 그 후, 지금까지 왔던 곳을 돌아가면서 수를 만들고, 그 수와 현재를 비교하기
    4. 만약 작다면 변경해주면 됨
    5. 여기서 자릿수도 같이 구해서, long범위를 벗어나거나 현재 수보다 자릿수가 크다면 그냥 넘어가면 됨

        로직
    1. 입력이 100까지 밖에 안되기 때문에, 미리 배열을 만들어 놓으면 됨
    2. 그 후, 입력을 바로 넣어서 값을 구해주면 됨
 */
