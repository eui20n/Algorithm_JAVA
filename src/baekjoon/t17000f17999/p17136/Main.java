/**
 * 문제 이름 : 색종이 붙이기
 * URL : https://www.acmicpc.net/problem/17136
 * 문제 설명 :
 * 1 x 1, 2 x 2, 3 x 3, 4 x 4, 5 x 5의 색종이를 10 x 10 종이 위에 붙이려고 한다. 종이 위에 색종이를 모두 붙이려고 할 때, 필요한
 * 색종이의 최소 개수를 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t17000f17999.p17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[][] arr = new int[10][10];
    static int[] paper = new int[100];
    static int[] colorPaperSize = {1, 2, 3, 4, 5};
    static int[] colorPaperAmount = {5, 5, 5, 5, 5};
    static int needColorPaperSize = 0;
    static int minNum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    int xy = changeTo(i, j);
                    paper[xy] = 1;
                    needColorPaperSize += 1;
                }
            }
        }
        colorPaper(0, 0, 0);
        System.out.println(minNum == Integer.MAX_VALUE ? -1 : minNum);
    }

    static void colorPaper(int xy, int cnt, int check) {
        if (xy == 100) {
            // 기저 조건
            // 모든 곳에 색종이를 붙였는지 확인하기
            if (check == needColorPaperSize) {
                // 다 붙인 것
                minNum = Math.min(cnt, minNum);
            }
            return;
        }

        if (paper[xy] == 0) {
            colorPaper(xy + 1, cnt, check);
            return;
        }

        int[] tmp = changeFrom(xy);

        paste : for (int i = 0; i < 5; i++) {
            if (colorPaperAmount[i] <= 0)
                continue;

            int size = colorPaperSize[i];
            List<Integer> place = new ArrayList<>();
            for (int x = tmp[0]; x < tmp[0] + size; x++) {
                for (int y = tmp[1]; y < tmp[1] + size; y++) {
                    // 갈 수 있는지 검증하기
                    if (0 > x || x >= 10 || 0 > y || y >= 10)
                        break paste;

                    int newXY = changeTo(x, y);
                    if (newXY >= 100 || paper[newXY] == 0)
                        // 이미 덮은 부분과 범위가 벗어나는 경우는 더 이상 갈 필요 없음
                        // 근데 작은 수 부터 하기때문에 여기서 더 못하면 그냥 종료를 시키면 됨
                        break paste;

                    place.add(newXY);
                }
            }

            // 붙일 수 있다는 의미 => 붙이기
            attach(place);
            colorPaperAmount[i] -= 1;
            colorPaper(xy + 1, cnt + 1, check + place.size());
            // 다시 떼기
            colorPaperAmount[i] += 1;
            detach(place);
        }
    }

    static void attach(List<Integer> list) {
        for (int xy : list) {
            paper[xy] = 0;
        }
    }

    static void detach(List<Integer> list) {
        for (int xy : list) {
            paper[xy] = 1;
        }
    }

    static int changeTo(int x, int y) {
        // 좌표를 받으면 일자로 펼쳐진 값을 반환하기
        int xy = y + x * arr.length;
        return xy;
    }

    static int[] changeFrom(int xy) {
        // 일자로 펼쳐진 값을 받으면 좌표로 반환하기
        int x = xy / arr.length;
        int y = xy % arr.length;
        return new int[]{x, y};
    }

    static void print(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}

// 일렬로 펼친 다음 계산을 진행
