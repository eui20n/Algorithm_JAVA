/**
 * 문제 이름 : 문제 추천 시스템 Version2
 * URL : https://www.acmicpc.net/problem/21944
 * 문제 설명 :
 * 명령이 3개 있다
 * 1. recommend G x
 * 1-1. x가 1인 경우 추천 문제 리스트에서 알고리즘 분류가 G인 문제중 가장 어려운 문제 번호를 출력
 * 여러개라면 문제 번호가 가장 큰 것으로 출력
 * 1-2. x가 -1인 경우 추천 문제 리스트에서 알고리즘 분류가 G인 문제중 가장 쉬운 문제 번호를 출력
 * 여러개라면 문제 번호가 가장 작은 것으로 출력
 * 2. recommend2 x
 * 2-1. x가 1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 가장 어려운 문제 번호를 출력
 * 여러개라면 문제 번호가 가장 큰 것으로 출력
 * 2-2. x가 -1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 가장 쉬운 문제 번호를 출력
 * 여러개라면 문제 번호가 가장 작은 것으로 출력
 * 3. recommend3 xL
 * 3-1. x가 1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 난이도 L보다 크거가 같은 문제 중 가장 쉬운 문제 번호를 출력
 * 여러개라면 문제 번호가 가장 작은 것으로 출력, 만약 조건에 만족하는 것이 없다면 -1 출력
 * 3-2. x가 -1인 경우 추천 문제 리스트에서 알고리즘 분류 상관없이 난이도 L보다 작은 문제 중 가장 어려운 문제 번호를 출력
 * 여러개라면 문제 번호가 가장 큰 것으로 출력, 만약 조건에 만족하는 것이 없다면 -1 출력
 * 4. add P L G : 추천 문제 리스트에서 난이도가 L이고 알고리즘 분류가 G인 문제 번호 P를 추가
 * 5. solved P : 추천 문제 리스트에서 문제 번호 P를 제거
 * 위 명령을 수행할 수 있는 문제 추천 시스템을 만들어라.
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t21000f21999.p21944;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int commandCnt;
    static boolean[] problemCheck = new boolean[100001]; // 해당 번호에 문제가 있다면 true, 아니면 false
    static List<List<int[]>> level; // 각 난이도에 문제 번호가 들어갈 것
    static List<List<int[]>> kind; // 각 알고리즘 분류에 문제 번호가 들어갈 것
    static Map<Integer, Integer> levelMap = new HashMap<>();
    static Map<Integer, Integer> kindMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        level = makeList();
        kind = makeList();

        for (int i = 0; i < N; i++) {
            int[] problemList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int problemNum = problemList[0];
            int problemLevel = problemList[1];
            int problemKind = problemList[2];

            level.get(problemLevel).add(new int[]{problemNum, problemKind});
            kind.get(problemKind).add(new int[]{problemNum, problemLevel});
            problemCheck[problemNum] = true;

            levelMap.put(problemNum, problemLevel);
            kindMap.put(problemNum, problemKind);
        }

        commandCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < commandCnt; i++) {
            String[] command = br.readLine().split(" ");
            String execute = command[0];

            if (execute.equals("add")) {
                add(command);
            } else if (execute.equals("recommend")) {
                System.out.println(recommend(command));
            } else if (execute.equals("recommend2")) {
                System.out.println(recommend2(command));
            } else if (execute.equals("recommend3")) {
                System.out.println(recommend3(command));
            } else if (execute.equals("solved")) {
                solved(command);
            }
        }
    }

    static void add(String[] command) {
        int problemNum = Integer.parseInt(command[1]);
        int problemLevel = Integer.parseInt(command[2]);
        int problemKind = Integer.parseInt(command[3]);

        level.get(problemLevel).add(new int[]{problemNum, problemKind});
        kind.get(problemKind).add(new int[]{problemNum, problemLevel});
        problemCheck[problemNum] = true;

        levelMap.put(problemNum, problemLevel);
        kindMap.put(problemNum, problemKind);
    }

    static int recommend(String[] command) {
        int commandKind = Integer.parseInt(command[1]);
        int problemKind = Integer.parseInt(command[2]);

        List<int[]> kindList = kind.get(problemKind);

        Collections.sort(kindList, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int result = 0;

        if (commandKind == 1) {
            // 어려운 문제중 번호가 가장 큰 것
            result = kindList.get(kindList.size() - 1)[0];
        } else {
            // 쉬운 문제중 번호가 가장 작은 것
            result = kindList.get(0)[0];
        }

        return result;
    }

    static int recommend2(String[] command) {
        int commandKind = Integer.parseInt(command[1]);
        List<int[]> levelList = null;

        if (commandKind == -1) {
            for (int i = 100; i >= 1; i--) {
                if (!level.get(i).isEmpty()) {
                    // 출력
                    levelList = level.get(i);

                }
            }
        } else {
            for (int i = 1; i < 101; i++) {
                if (!level.get(i).isEmpty()) {
                    // 출력
                    levelList = level.get(i);
                }
            }
        }

        Collections.sort(levelList, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int idx = commandKind == 1 ? 0 : levelList.size() - 1;
        return levelList.get(idx)[0];
    }

    static int recommend3(String[] command) {
        int commandKind = Integer.parseInt(command[1]);
        int problemLevel = Integer.parseInt(command[2]);

        List<int[]> levelList = null;

        if (commandKind == -1) {
            for (int i = problemLevel; i < 101; i++) {
                if (!level.get(i).isEmpty()) {
                    // 출력
                    levelList = level.get(i);

                }
            }
        } else {
            for (int i = problemLevel; i >= 1; i--) {
                if (!level.get(i).isEmpty()) {
                    // 출력
                    levelList = level.get(i);
                }
            }
        }

        Collections.sort(levelList, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int idx = commandKind == 1 ? 0 : levelList.size() - 1;
        return levelList.get(idx)[0];
    }

    static void solved(String[] command) {
        int problemNum = Integer.parseInt(command[1]);
        problemCheck[problemNum] = false;
        // 리스트에 있는 값 삭제해야함
    }

    static List<List<int[]>> makeList() {
        List<List<int[]>> tmp = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            tmp.add(new ArrayList<>());
        }
        return tmp;
    }
}

// 문제번호는 무조건 하나밖에 안들어옴
// 문제 번호의 최대값은 100,000임 => 그냥 배열을 만들어서 관리해도됨
// 이전에 삭제했던 문제가 다시 들어올 수 있음
// 우선순위 큐에 넣어서 관리하면 될듯

// 이렇게 하면 삭제 연산이 엄청 힘들어짐