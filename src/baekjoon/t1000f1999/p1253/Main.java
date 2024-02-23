/**
 * 문제 이름 : 좋다
 * URL : https://www.acmicpc.net/problem/1253
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        check();
    }

    static void check() {
        bruteForce();

    }

    static void bruteForce() {
        /*
            일단은 완전 탐색으로 구현을 해보기
            => 잘 모를때는 완전 탐색을 구현을 해보는 것이 좋음
            => 그럼 구현 도중 혹은 다 완성이 되면 어떤 알고리즘을 통해서 시간이나 공간을 줄일 수 있는지 보이는 경우가 있음
         */
        int result = 0;
        for (int x = 0; x < arr.length; x++) {
            int standardNum = arr[x];
            find:
            for (int i = 0; i < arr.length; i++) {
                if (x == i)
                    continue;

                int num1 = arr[i];
                for (int j = 0; j < arr.length; j++) {
                    if (x == j || i == j)
                        continue;

                    int num2 = arr[j];
                    if (num1 + num2 == standardNum) {
                        result += 1;
                        break find;
                    }
                }
            }
        }
        System.out.println(result);
    }
}

/*
        일단은 무슨 알고리즘을 써야할지 모르겠음
        그럴 때는, 먼저 완전 탐색으로 구현을 해보기

        이분 탐색...

        어떤 수를 만들 수 있다면, 그 수와 같은 수는 자동으로 만들 수 있음
 */
