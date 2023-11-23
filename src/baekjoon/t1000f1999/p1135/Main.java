/**
 * 문제 이름 : 뉴스 전하기
 * URL : https://www.acmicpc.net/problem/1135
 * 문제 설명 :
 * 회사의 중요한 뉴스를 모든 직원에게 빠르게 전달하려고 한다. 회사는 트리 구조이다. 모든 직원은 정확히 한 명의 직속 상사가 있다.
 * 자기 자신은 그들 자기 자신의 직접 또는 간접 상사가 아니고, 모든 직원은 민식이의 직접 또는 간접적인 부하이다. => 민식이는 사장임
 * 민식이는 일단 자신의 직속 부하에게 한 번에 한 사람씩 전화를 한다. 뉴스를 들은 후에 각 부하는 그의 직속 부하에세 한 번에 한 사람씩 전화한다.
 * 이 것은 모든 직원이 뉴스를 들을 때까지 반복된다. 모든 사람은 자신의 직속 부하에게만 전화를 걸 수 있고, 전화는 정확하게 1분 걸린다.
 * 이때, 모든 직원이 소식을 듣는데 걸리는 시간의 최솟값을 구해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t1000f1999.p1135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] parentArr;
    static int[] dpTable;
    static ArrayList<ArrayList<Integer>> childArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parentArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        childArr = makeChildArr();
        dpTable = new int[n];
        check(0, -1);
    }

    static int check(int child, int parent) {
        // child가 본인

        ArrayList<Integer> arr = childArr.get(child);
        if (arr.size() == 0) {
            // 여기가 리프 노드임
            dpTable[child] = 1;
            return 1;
        }

        for (int i = 0; i < arr.size(); i++) {
            // 리프노드 까지 들어가기
            check(arr.get(i), child);
            dpTable[child] = Math.max(dpTable[child], check(arr.get(i), child));
        }
        return 0;
    }

    static ArrayList<ArrayList<Integer>> makeChildArr() {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++)  {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (parentArr[i] == -1)
                continue;
            arr.get(parentArr[i]).add(i);
        }
        return arr;
    }
}

// 트리 DP 임
// 우선 leaf까지 가야함
