/**
 * 문제 이름 : 이차원 배열과 연산
 * URL : https://www.acmicpc.net/problem/17140
 * 문제 설명 : 3 X 3 배열에서 1초가 지날때마다 아래 연산이 적용이 됨
 * 1. R 연산 : 배열의 모든 행에 대해서 정렬을 수행 -> 행의 개수 >= 열의 개수인 경우에 적용
 * 2. C 연산 : 배열의 모든 열에 대해서 정렬을 수행 -> 행의 개수 < 열의 개수인 경우에 적용
 * 위의 연산을 통해서 수를 정렬하면, 각각의 수가 몇 번 나왔는지 알아야함. 그 다음 수의 등장 횟수가 커지는 순으로, 그러한 것이
 * 여러가지면 수가 커지는 순으로 정렬함. 그 다음 배열에 정렬된 결과를 다시 넣어야함. 넣을 때는, 수와 등장 횟수를 모두 넣으면, 순서는 수가 먼저임
 * 이렇게 하면 행 또는 열의 크기가 변하는데, 가장 큰 것을 기준으로 빈 공간은 0으로 채움, 수를 정렬할 때 0은 무시
 * 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버리기
 * 배열의 r, c, k가 주어질 때, 배열[r][c]의 값이 k가 되기 위한 최소 시간을 구해라
 * (r, c는 1부터 시작함)
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] arr = new int[100][100];
    static int r, c, k;
    static int[] arrLength = {3, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = tmpArr[0] - 1;
        c = tmpArr[1] - 1;
        k = tmpArr[2];

        for (int i = 0; i < 3; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < tmp.length; j++) {
                arr[i][j] = tmp[j];
            }
        }
        System.out.println(operation());
    }

    static int operation() {
        int time = 0;
        while (true) {
            if (time > 100) {
                return -1;
            }

            int RLength = arrLength[0];
            int CLength = arrLength[1];

            if (arr[r][c] == k) {
                return time;
            }

            if (RLength >= CLength) {
                arr = RSort();
            } else if (CLength > RLength) {
                arr = CSort();
            }
            time++;
        }
    }

    static int[][] RSort() {
        int[][] newArr = new int[100][100];
        arrLength[1] = 0;
        int row = 0;

        for (int i = 0; i < arr.length; i++) {
            int[] tmpArr = arr[i];
            int[][] tmp = elementCount(tmpArr);

            Arrays.sort(tmp, (o1, o2) -> {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            });

            // [1, 2] [2, 3] => [1, 2, 2, 3] 으로 바꾸는 과정
            int col = 0;
            stop:
            for (int[] tmpR : tmp) {
                for (int tmpC : tmpR) {
                    if (col >= 100) {
                        break stop;
                    }
                    newArr[row][col++] = tmpC;
                }
            }
            arrLength[1] = Math.max(col, arrLength[1]);
            row++;
        }

        return newArr;
    }

    static int[][] CSort() {
        int[][] newArr = new int[100][100];
        arrLength[0] = 0;
        int col = 0;

        for (int j = 0; j < arr[0].length; j++) {
            int[] tmpArr = new int[arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                tmpArr[i] = arr[i][j];
            }

            int[][] tmp = elementCount(tmpArr);
            Arrays.sort(tmp, (o1, o2) -> {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            });
            int row = 0;
            stop:
            for (int[] tmpR : tmp) {
                for (int tmpC : tmpR) {
                    if (row >= 100) {
                        break stop;
                    }
                    newArr[row++][col] = tmpC;
                }
            }
            arrLength[0] = Math.max(row, arrLength[0]);
            col++;
        }
        return newArr;
    }

    static int[][] elementCount(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            if (num == 0) {
                continue;
            }

            if (map.containsKey(num)) {
                int getValue = map.get(num) + 1;
                map.put(num, getValue);
            } else {
                map.put(num, 1);
            }
        }

        int size = map.size();
        int[][] result = new int[size][];
        int idx = 0;
        for (int key : map.keySet()) {
            result[idx++] = new int[]{key, map.get(key)};
        }
        return result;
    }
}