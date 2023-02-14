/**
 * 문제 이름 : 톱니바퀴
 * URL : https://www.acmicpc.net/problem/14891
 * 문제 설명 : 톱니바퀴를 돌렸을 때, 마지막은 어느 경우인지 출력을 해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t14000f14999.p14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Deque<Integer>> gear = new ArrayList<>();
        gear.add(makeGear(br.readLine().split(""))); // 1번 톱니바퀴
        gear.add(makeGear(br.readLine().split(""))); // 2번 톱니바퀴
        gear.add(makeGear(br.readLine().split(""))); // 3번 톱니바퀴
        gear.add(makeGear(br.readLine().split(""))); // 4번 톱니바퀴

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++){
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int rotateGear = tmp[0] - 1;
            int dir = tmp[1];

            int[] indexValue = getIndex(gear.get(rotateGear));
            System.out.println(Arrays.toString(indexValue));
        }
    }

    static Deque<Integer> makeGear(String[] gear){
        Deque<Integer> deque = new ArrayDeque<>();
        for (String data : gear){
            deque.add(Integer.parseInt(data));
        }
        return deque;
    }

    static int[] getIndex(Deque<Integer> gear){
        Deque<Integer> index3 = new ArrayDeque<>(gear);
        Deque<Integer> index7 = new ArrayDeque<>(gear);

        index3.pollFirst();
        index3.pollFirst();
        int indexValue3 = index3.pollFirst();

        index7.pollLast();
        int indexValue7 = index7.pollLast();
        return new int[]{indexValue3, indexValue7};
    }
}

// 덱을 이용하면 쉽게 풀이 할 듯
// 덱은 인덱스로 접근을 할 수 없음
// 결국 우리가 알아야 하는 것은 3번째와 7번째라서 그냥 pop해서 알아보기

// 입력을 다 받지 않고, 톱니바퀴가 돌아가는 연산은 받자 마자 처리 가능
// 1번의 3번째와 2번의 7번째
// 2번의 3번째와 3번의 7번째
// 3번의 3번째와 4번의 7번째

// N극은 0, S극은 1
// 극이 서로 다르면 반대 방향
// 극이 같으면 회전 안함
// 방향 1이 시계반향 = > 제일 앞에 있는 것이 제일 뒤로 가면 됨
// 방향 -1이 반시계 방향 => 제일 뒤에 있는 것이 제일 앞으로 가면 됨