/**
 * 문제 이름 : 전구와 스위치
 * URL : https://www.acmicpc.net/problem/2138
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[] bulbArr;
    static char[] wantBulbArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bulbArr = br.readLine().toCharArray();
        wantBulbArr = br.readLine().toCharArray();

        check();
    }

    static void check() {
        int result = Math.min(push(true), push(false));
        System.out.println(result != Integer.MAX_VALUE ? result : -1);
    }

    static int push(boolean isPush) {
        /*
            isPush => 첫 번째 스위치를 눌렀는지 안눌렀는지 알려주는 매개 변수
         */
        int pushCnt = 0;
        char[] deepCopyBulbArr = deepCopy(bulbArr);

        if (isPush) {
            // 첫 번째를 누른 경우
            pushCnt += pushSwitch(deepCopyBulbArr, 0);
        }

        for (int i = 1; i < N; i++) {
            // 첫 번째 상태는 두 번째와 첫 번째만 영향을 줄 수 있음
            // 여기서는 한 칸 전의 상태를 봐야함
            // 원하는 답과 다르다면 누르고, 같다면 누르면 안됨
            if (deepCopyBulbArr[i - 1] != wantBulbArr[i - 1]) {
                // 위 2개가 달라야 눌러야 함
                pushCnt += pushSwitch(deepCopyBulbArr, i);
            }
        }
        if (equalArr(deepCopyBulbArr)) {
            return pushCnt;
        }
        return Integer.MAX_VALUE;
    }

    static int pushSwitch(char[] arr, int idx) {
        /*
                눌러주는 메소드
         */
        int prevIdx = idx - 1;
        int nextIdx = idx + 1;

        if (prevIdx >= 0) {
            arr[prevIdx] = arr[prevIdx] == '1' ? '0' : '1';
        }
        if (nextIdx < N) {
            arr[nextIdx] = arr[nextIdx] == '1' ? '0' : '1';
        }
        arr[idx] = arr[idx] == '1' ? '0' : '1';
        return 1;
    }

    static boolean equalArr(char[] arr) {
        // 2개의 배열이 같은 배열인지 확인
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != wantBulbArr[i])
                return false;
        }
        return true;
    }


    static char[] deepCopy(char[] arr) {
        char[] newArr = new char[arr.length];

        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }

        return newArr;
    }
}

/*
        첫 번째 스위치가 굉장히 중요함
        만약에 첫 번째 스위치를 눌렀다면, 첫 번째 스위치의 상태는 두 번째 스위치에 대해서만 변화가 생김
        여기서, 만약 첫 번째 스위치가 원하는 상태와 다르다면?? 무조건 두 번째 스위치를 눌러야함
        두 번째 스위치의 상태가 다르다면 세 번째를 눌러야함... 이를 반복하면 정답이 구해짐
        => 첫 번째만 누를지 말지 정해지면 나머지는 강제로 눌러야 할지 말지가 정해짐
        ==> 이 생각을 해내는 것이 굉장히 어렵고 까다로웠음
 */