/**
 * 문제 이름 : 주유소
 * URL : https://www.acmicpc.net/problem/13305
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 : 그리디 + 투 포인터
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t13000f13999.p13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] distanceArr;
    static int[] priceArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        distanceArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        priceArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check();
    }

    static void check() {
        int idx = 0; // 시작 => 현재 있는 곳을 표시
        long result = 0;

        for (int i = 1; i < N; i++) {
            if (priceArr[idx] > priceArr[i]) {
                // 현재 있는 곳이 가격이 더 높음
                // 즉, 여기 까지 현재 있는 곳의 가격으로 받으면 됨

                for (int j = idx; j < i; j++) {
                    result += (long) priceArr[idx] * distanceArr[j];
                }

                // 현재 위치를 변경
                idx = i;
            }
        }

        // 위 처럼 하면 가장 저렴한 부분으로 끝이 나면 그 후 계산을 못함
        // ex) 2 1 1 1 라고 하면 1에서 멈춤 => 처음 1이 나온 시점 부터 끝까지 다 더해줘야함
        for (int j = idx; j < distanceArr.length; j++) {
            result += (long) priceArr[idx] * distanceArr[j];
        }

        System.out.println(result);
    }
}

/*
        로직
    그냥 지금 위치보다 더 싸지는 곳이 나올 때 동안 지금 위치에서 사면 되는거 아닌가?
    => for문 한 번에 해결 가능

    생각을 많이 하게 만드는 문제네 => 괜찮은 문제구만
 */