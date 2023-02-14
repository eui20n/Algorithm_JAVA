/**
 * 문제 이름 : 두 개의 숫자열
 * 문제 번호 : 1959
 * 문제 설명 : 두 개의 숫자열을 서로 마주보게 해서 곱한 값의 합 중 가장 큰 값을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 입력으로 받는 배열 중 Arr1의 길이가 무조건 Arr2이하여야 편하게 계산가능
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1959;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int T;
    static int A;
    static int B;
    static int[] intArr1;
    static int[] intArr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            A = tmp[0];
            B = tmp[1];

            int[] arr1 = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int[] arr2 = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            if (arr1.length > arr2.length) {
                intArr1 = arr2;
                intArr2 = arr1;
            } else {
                intArr1 = arr1;
                intArr2 = arr2;
            }

            System.out.println("#" + i + " " + check());

        }

    }

    public static int check() {
        int intArrLength1 = intArr1.length;
        int intArrLength2 = intArr2.length;
        int cnt = 0;
        int tmp = 0;
        int result = 0;

        while (true) {
            for (int i = 0; i < intArrLength1; i++) {
                if (cnt + i >= intArrLength2) {
                    return result;
                }

                int arrNum1 = intArr1[i];
                int arrNum2 = intArr2[cnt + i];

                tmp += (arrNum1 * arrNum2);
            }
            result = Math.max(tmp, result);
            tmp = 0;
            cnt += 1;

        }

    }

}