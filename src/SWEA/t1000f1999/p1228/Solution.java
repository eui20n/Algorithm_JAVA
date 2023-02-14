/**
 * 문제 이름 : 암호문1
 * 문제 번호 : 1228
 * 문제 설명 : 0 ~ 999999 사이의 수를 나열하여 만든 암호문이 있다
 * 이 암호문을 수정해야 하는 일이 생겼다. 수정은 특수 제작된 처리기로만 가능하고 1개의 기능을 제공한다.
 * 기능 : (삽입) x, y, s => 앞에서부터 x위 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들이다.
 * 위의 규칙에 맞게 작성된 명령어를 나열하여 만든 문자열이 주어졌을 때, 암호문을 수정하고, 수정된 결과의 처음 10개 숫자를 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할때, package 삭제하기
 */

package SWEA.t1000f1999.p1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(br.readLine());

            String[] tmp = br.readLine().split(" ");
            List<String> list = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                list.add(tmp[j]);
            }

            int commandCnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < commandCnt; j++) {
                st.nextToken();
                int idx = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                for (int k = 0; k < end; k++) {
                    list.add(idx++, st.nextToken());
                }
            }
            System.out.printf("#%d ", i);
            for (int j = 0; j < 10; j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }
    }
}
