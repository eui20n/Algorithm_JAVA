/**
 * 문제 이름 : 백설 공주와 일곱 난쟁이
 * URL : https://www.acmicpc.net/problem/3040
 * 문제 설명 : 입력으로 들어오는 수 9개중 합이 100이 되는 7개를 찾아서 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t3000f3999.p3040;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[] arr = new int[9];
    static boolean printCheck;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
        }

        check(0, 0, 0, new ArrayList<>());
    }

    static void check(int cnt, int idx, int num, List<Integer> list) {
        if (cnt == 7) {
            if (num == 100) {
                printCheck = true;
                print(list);
            }
            return;
        }

        for (int i = idx; i < 9; i++) {
            if (list.contains(arr[i]))
                continue;

            list.add(arr[i]);
            num += arr[i];
            if (!(printCheck))
                check(cnt + 1, idx + 1, num, list);
            else return;
            num -= arr[i];
            list.remove(list.size() - 1);
        }
    }

    static void print(List<Integer> list) {
        for (Integer num : list) {
            System.out.println(num);
        }
    }
}
