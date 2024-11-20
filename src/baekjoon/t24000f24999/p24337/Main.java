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
        int[] arr = makeArr();
        if (arr.length > N) {
            System.out.println(-1);
        } else {
            result(arr);
        }
    }

    static void result(int[] arr) {
        int[] resultArr = new int[N];

        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && leftWatch == 1) {
                resultArr[i] = arr[i];
            } else {
                resultArr[N - arr.length + i] = arr[i];
            }
        }
        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[i] >= 1) {
                continue;
            }
            resultArr[i] = 1;
        }
        printArr(resultArr);
    }

    static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb
                    .append(arr[i])
                    .append(" ");
        }
        System.out.println(sb.toString());
    }

    static int[] makeArr() {
        int[] arr = new int[leftWatch + rightWatch - 1];

        for (int i = 0; i < leftWatch; i++) {
            arr[i] = i + 1;
        }
        for (int i = 0; i < rightWatch; i++) {
            arr[arr.length - i - 1] = i + 1;
        }
        arr[leftWatch - 1] = Math.max(leftWatch, rightWatch);
        return arr;
    }
}

/*
        핵심 로직

        예를 들면 왼쪽에서 보는게 3, 오른쪽에서 보는게 2라면 아래처럼 만들어줌 => 배열의 길이가 5를 가정하고 있음
        12311

        저걸 만들어주는 로직이 무엇일까...
        1. 왼쪽것을 만족하는 배열을 만들어줌
        1-1. 예시를 3으로 했으니, 이를 만족하는 사전순으로 가장 먼저 있는 것은 123임
        2. 앞에걸을 포함한 뒤에 것을 만족하는 배열을 만들어줌
        2-1. 예시가 2니까, 123뒤에 붙을 것중 가장 사전순으로 먼저 올 수 있는 것은 12311임
        3. 그럼 12311이 가장 뒤에 오는 것이고, 나머지는 배열의 길이에 맞게 앞에 1을 붙여주면 됨
        3-1. 예를 들면 길이가 10이면 1111112311 이렇게 하면 됨
        4. 해당 배열의 길이가 입력으로 주어진 배열의 길이보다 큰 경우 만들지 못하는 경우임
        5. 완성할 수 있으면 완성 배열을, 완성 못하면 -1을 출력하면 됨
 */

/*
        조합으로 모든 경우를 구해서 가지를 치는 것보다, 그냥 조건에 맞는 배열을 구하는 것이 더 쉬울듯 => 즉, 그리디 문제라는 소리
        그리고 양쪽을 비교해서 더 큰쪽이 더 큰값을 가지게 하면 됨
 */

/*
        조합으로 가장한 그리디 문제
 */