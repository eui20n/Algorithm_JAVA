/**
 * 문제 이름 : 교환
 * URL : https://www.acmicpc.net/problem/1039
 * 문제 설명 :
 * 0으로 시작하지 않는 정수 N이 주어진다. 이때, M을 정수 N의 자릿수라고 했을 때, 다음 연산을 K번 수행한다.
 * 1 <= i < j <= M인 i와 j를 고른다. 그 다음, i번 위치의 숫자와 j번 위치의 숫자를 바꾼다. 이떄, 바꾼 수가 0으로 시작하면 안된다.
 * 위의 연산을 K번 했을 때, 나올 수 있는 수의 최대값을 구해라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1039;

import java.util.Scanner;

public class Main {
    static int N, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
    }
}

// 그냥 제일 많은 것들을 빼면서 배치하면 될 듯 => 이웃과는 달라야함
// 그 때 배치를 못하면 -1, 배치가능하면 배치한 것을 출력하면 될듯
