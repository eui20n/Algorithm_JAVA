/**
 * 문제 이름 : 수 이어 쓰기
 * URL : https://www.acmicpc.net/problem/1515
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] checkNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        checkNum = br.readLine().toCharArray();

        check();
    }

    static void check() {
        int num = 1;
        int idx = 0;

        result:
        while (true) {
            // 숫자를 char의 배열로 표현
            char[] charNumArr = String.valueOf(num).toCharArray();

            // 해당 배열을 반복을 돌림
            for (char charNum : charNumArr) {
                // idx에 해당하는 숫자가 있는지 확인하기
                int idxNum = checkNum[idx];

                if (charNum == idxNum) {
                    // 만약에 같다면 인덱스 증가시키기
                    idx++;

                    // 인덱스가 범위를 벗어나면 종료
                    if (idx >= checkNum.length)
                        break result;
                }
            }
            num++;
        }

        System.out.println(num);
    }
}

/*
    무조건 스텝마다 숫자는 계속해서 증가해야함

    숫자가 증가할 때마다, 인덱스에 있는 값이 포함되어 있는지 확인해야함
    여기서 만약에 포함이 된다면 인덱스를 추가하고 확인을 해야함
 */