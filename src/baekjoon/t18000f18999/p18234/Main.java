/**
 * 문제 이름 : 당근 훔쳐 먹기
 * URL : https://www.acmicpc.net/problem/18234
 * 문제 설명 :
 * 아무것도 심어져 있지 않은 텃밭이 있다. N종류의 당근을 하나씩 심고 T일 동안 재배할 예정이다
 * 당근 i는 처음에는 wi의 맛을 가지고 있고, 각 당근 i에 사용할 pi만큼 맛을 증가시켜주는 영양제가 당근 종류별로 T개씩 준비되어 있다.
 * 오리는 당근이 본래의 맛보다 훨씬 맛있어지기를 바라기 때문에 pi는 항상 wi이상의 값을 가지도록 준비했다.
 * 오리는 당근 i 가 i에 없다면 당근을 심고, 있다면 영양제를 준다.
 * 토끼가 당근을 훔쳐 먹을 건데, 하루에 최대 1개의 당근을 먹을 수 있다. 당연히 안먹을 수도 있다. 먹을 수 있는 당근 맛의 최대값을 구해라
 * 당근은 오후에만 먹는다 => 당근을 심는 것이 먼저다
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t18000f18999.p18234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, T;
    static int[][] carrots;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        T = Integer.parseInt(tmp[1]);

        carrots = new int[N][2];

        for (int i = 0; i < N; i++) {
            carrots[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(Arrays.toString(carrots[i]));
        }


    }
}

// 문제에서 당근은 정렬이 된 값임
// 줄세우기를 생각해보기
// 결정 문제임 => 값의 최대 값은 100억임. 충분히 파라메틱 서치를 의심해봐도됨
// 각각의 당근에 대해서 파라메틱 서치를 해야하나

// 그냥 파라마틱 서치라고 생각하기
// 1. 범위는 토끼가 먹을 수 있는 당근의 양
// 2. 이분 탐색을 돌릴 값 => 최대 당근
// 3. 최대 당근을 구하는 로직만 생각하면 됨
// 이럴수가 이거 그냥 수학문제였어

// 수학으로 접근
// 그냥 가장 길게 될 수 있는 것을 마지막에 먹기
// 그렇게 해서 순차적으로 뒤에 있는거 먹으면 됨
// 그리고 무조건 안먹는게 좋음 => 조건에서 영양제가 초기값 보다 크다고 했음