/**
 * 문제 이름 : 준표의 조약돌
 * URL : https://www.acmicpc.net/problem/15831
 * 문제 설명 : 입력으로 총 조약돌의 길이가 정해져 있음 여기서 검은
 * 색과 흰 색이 있는데, 준표는 길을 따라가면 줄을 주울것 여기서 검은 돌은 최대 개수가 정해져 있고, 흰 돌의 최소 개수가 정해져 있을 때, 준표가 가는 길의 최대 값을
 * 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t15000f15999.p15831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    static int N;
    static int maxBlack;
    static int minWhite;
    static char[] roadArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
            .toArray();
        N = tmpArr[0];
        maxBlack = tmpArr[1];
        minWhite = tmpArr[2];

        roadArr = new char[N];
        roadArr = br.readLine().toCharArray();
        System.out.println(checkRoad());
    }

    static int checkRoad() {
        Deque<Character> checkDeque = new ArrayDeque<>();
        int result = 0;

        int blackStoneCnt = 0;
        int whiteStoneCnt = 0;

        for (char stone : roadArr) {
            checkDeque.add(stone);
            if (stone == 'B') {
                blackStoneCnt += 1;

                // 검은 돌의 개수가 초과하면 빼야함
                if (blackStoneCnt > maxBlack) {
                    while (true) {
                        if (blackStoneCnt <= maxBlack || checkDeque.isEmpty()) {
                            break;
                        }
                        char pollStone = checkDeque.pollFirst();

                        if (pollStone == 'B') {
                            blackStoneCnt -= 1;
                        } else {
                            whiteStoneCnt -= 1;
                        }
                    }
                }

            } else if (stone == 'W') {
                whiteStoneCnt += 1;
            }

            // 조건에 맞으면 결과를 도출하기
            if (blackStoneCnt <= maxBlack && whiteStoneCnt >= minWhite) {
                result = Math.max(result, checkDeque.size());
            }
        }
        return result;
    }
}

/*
bfs로 하면 메모리가 초과함 => 당연한 것임.... 큐에 2의 N승 만큼 들어감;;;
근데 N이 300,00임;;

이거 덱으로 구현 가능 할 듯 => 색의 개수가 초과하면 개수가 맞을 때 까지 계속 빼면 됨, 넣을 때 현재 상태를 계속해서 생각을 하는 것이 좋을 듯
 */