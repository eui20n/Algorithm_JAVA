/**
 * 문제 이름 : 스네이크버드
 * URL : https://www.acmicpc.net/problem/16435
 * 문제 설명 : 스네이크버드는 뱀과 새의 모습을 닮은 생물체다. 스네이크버드가 과일을 먹으면 길이가 1 늘어난다.
 *           과일은 일정 높이에 있고, 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일만 먹을 수 있을때, 최대 길이를 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 * */

package baekjoon.t16000f16999.p16435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, L;
    static int[] feeds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        L = Integer.parseInt(tmp[1]);
        feeds = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        maxLength();
        System.out.println(L);
    }

    static void maxLength(){
        Arrays.sort(feeds);

        for (int i = 0; i < N; i++){
            if (feeds[i] > L){
                break;
            }
            L++;
        }
    }
}
