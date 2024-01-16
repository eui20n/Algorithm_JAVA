/**
 * 문제 이름 : 햄버거 분배
 * URL : https://www.acmicpc.net/problem/19941
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t19000f19999.p19941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N, K;
    static char[] burgerPeopleArr;
    static boolean[] eatenBurger = new boolean[20000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        K = tmpInput[1];

        burgerPeopleArr = br.readLine().toCharArray();

        check();
    }

    static void check() {
        // 결과를 담을 변수
        int result = 0;

        // 사람 위치 구하기
        List<Integer> peopleIdx = findPeople();

        // 구한 사람 위치에서 반복 실행
        for (int people : peopleIdx) {
            // 왼쪽에서 가장 먼 것 => 이게 없다면 오른쪽에서 가장 가까운 것을 확인
            if (checkLeft(people)) {
                result += 1;
            } else {
                // 왼쪽에는 먹을 버거가 없음
                result = checkRight(people) ? result + 1 : result;
            }
        }

        System.out.println(result);
    }

    static boolean checkLeft(int people) {
        // 왼쪽에 버거를 먹을 수 있는지 확인해주는 메소드
        for (int i = K; i > 0; i--) {
            int burgerIdx = people - i;

            if (burgerIdx < 0)
                // 0보다 작을 수 없음
                continue;
            if (eatenBurger[burgerIdx])
                // 이미 먹었다면 그 버거는 더 이상 먹을 수 없음
                continue;
            if (burgerPeopleArr[burgerIdx] == 'P')
                // 해당 위치가 사람이면 안됨
                continue;
            eatenBurger[burgerIdx] = true;
            return true;
        }
        return false;
    }

    static boolean checkRight(int people) {
        // 오른쪽 버거를 먹을 수 있는지 확인해주는 메소드
        for (int i = 1; i <= K; i++) {
            int burgerIdx = people + i;

            if (burgerIdx >= N)
                // N보다 크거나 같을 수 없음
                continue;
            if (eatenBurger[burgerIdx])
                // 이미 먹었다면 그 버거는 더 이상 먹을 수 없음
                continue;
            if (burgerPeopleArr[burgerIdx] == 'P')
                // 해당 위치가 사람이면 안됨
                continue;
            eatenBurger[burgerIdx] = true;
            return true;
        }
        return false;
    }

    static List<Integer> findPeople() {
        List<Integer> peopleIdx = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char burgerPeople = burgerPeopleArr[i];

            if (burgerPeople == 'P') {
                peopleIdx.add(i);
            }
        }
        return peopleIdx;
    }
}

/*
    그냥 본인이 먹을 수 있는 것 중 가장 왼쪽에 있는 것을 먹으면 됨
    왼쪽에 먹을게 없다면 가장 가까운 오른쪽에 있는 것을 먹으면 됨
 */