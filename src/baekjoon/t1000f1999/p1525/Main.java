/**
 * 문제 이름 : 퍼즐
 * URL : https://www.acmicpc.net/problem/1525
 * 문제 설명 :
 * 빈칸과 인접해 있는 숫자는 빈칸으로 이동이 가능하다. 이때, 숫자가 정렬된 상태로 되고 싶을 때, 최소 몇번 움직여야 하는지 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] arr = new int[3][3];
    static int[] startPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0)
                    startPoint = new int[] {i, j};
            }
        }
        print(arr);
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// 방문처리 가능?
// 메모리가 상당히 작음 => queue에 함부로 넣으면 안됨
// 근데 자바는 조금 많이 주네

// 이게 순서대로 만들어야하나?
// 그리고, 이미 만들어진 자리가 확정이 된 퍼즐의 위치는 절대로 바꾸면 안되는 건가?