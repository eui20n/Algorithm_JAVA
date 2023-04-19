/**
 * 문제 이름 : 굉장한 모비스터디
 * URL : https://www.acmicpc.net/problem/27726
 * 문제 설명 :
 * 모비스터디란 스터디원이 2명 이상인 것을 의미한다.
 * 굉장한 모비스터디란 아래와 같다
 * 1. 어떤 사람들이 세번 스터디에서 모두 같은 모비스터디에 속해야 한다.
 * 2. 그 외 나머지 인원들은 다른 인원들과 3번의 스터디 모두를 같이 않야하 한다.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */


package baekjoon.t27000f27999.p27726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int M1, M2, M3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M1 = tmp[0];
        M2 = tmp[1];
        M3 = tmp[2];


    }
}
