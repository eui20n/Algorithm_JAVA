/**
 * 문제 이름 : 사칙연산 유효성 검사
 * 문제 번호 : 1233
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(br.readLine());

            List<int[]> node = new ArrayList<>();
            char[] operators = new char[N + 1];
            node.add(new int[] {});


            for (int j = 1; j <= N; j++) {
                String[] inputArr = br.readLine().split(" ");
                if (inputArr.length == 4) {
                    char operator = inputArr[1].charAt(0);
                    int son1 = Integer.parseInt(inputArr[2]);
                    int son2 = Integer.parseInt(inputArr[3]);
                    operators[j] = operator;

                    node.add(new int[] {son1, son2});

                } else if (inputArr.length == 3) {
                    char operator = inputArr[1].charAt(0);
                    int son1 = Integer.parseInt(inputArr[2]);
                    operators[j] = operator;

                    node.add(new int[] {son1});

                } else {
                    char operator = inputArr[1].charAt(0);
                    operators[j] = operator;
                    node.add(new int[] {});
                }
            }

            System.out.println(check(node, operators, 1));
        }
    }

    static int check(List<int[]> nodes, char[] operators, int idx){
        int checkNum = Integer.MAX_VALUE;

        if (nodes.get(idx).length == 0){
            if (operators[idx] == '+')
                return 0;
            if (operators[idx] == '-')
                return 0;
            if (operators[idx] == '*')
                return 0;
            if (operators[idx] == '/')
                return 0;
            return 1;
        }

        for (int[] nodeArr : nodes){
            System.out.println(Arrays.toString(nodeArr));
            for (int node : nodeArr){
                checkNum = Math.min(check(nodes, operators, node), checkNum);
                if (checkNum == 0){
                    return checkNum;
                }

                if (operators[idx] == '+')
                    continue;
                if (operators[idx] == '-')
                    continue;
                if (operators[idx] == '*')
                    continue;
                if (operators[idx] == '/')
                    continue;
                return 0;
            }
        }
        return 1;
    }
}
