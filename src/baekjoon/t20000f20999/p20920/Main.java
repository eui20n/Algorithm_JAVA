/**
 * 문제 이름 : 영단어 암기는 괴로워
 * URL : https://www.acmicpc.net/problem/20920
 * 문제 설명 :
 * 1. 자주 나오는 단어일수록 앞에 배치
 * 2. 해당 단어의 길이가 길수록 앞에 배치
 * 3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20920;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static HashSet<String> wordSet = new HashSet<>(); // 등장한 단어
    static int[] wordCnt = new int[100001]; // 등장한 단어의 횟수
    static HashMap<String, Integer> wordIdx = new HashMap<>(); // 등장한 단어의 인덱스 => 리스트로 횟수 관리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tmpInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmpInput[0];
        M = tmpInput[1];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            if (word.length() < M)
                continue;

            if (wordSet.contains(word)) {
                // 단어가 있는 경우
                int idx = wordIdx.get(word);
                wordCnt[idx] += 1;
            } else {
                // 단어가 없는 경우
                int idx = wordIdx.size();
                wordIdx.put(word, idx);
                wordCnt[idx] += 1;
                wordSet.add(word);
            }
        }
        check();
    }

    static void check() throws IOException {
        /*
         * 1. 자주 나오는 단어일수록 앞에 배치
         * 2. 해당 단어의 길이가 길수록 앞에 배치
         * 3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치
         */
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<String> dict = new PriorityQueue<>((o1, o2) -> {
            int o1Idx = wordIdx.get(o1);
            int o2Idx = wordIdx.get(o2);
            int o1Cnt = wordCnt[o1Idx];
            int o2Cnt = wordCnt[o2Idx];

            if (o1Cnt == o2Cnt) {
                // 등장 횟수가 동일함
                if (o1.length() == o2.length()) {
                    // 길이가 동일함
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return o2Cnt - o1Cnt;
        });

        for (String word : wordSet) {
            dict.add(word);
        }

        int loopSize = dict.size();
        for (int i = 0; i < loopSize; i++) {
            String word = dict.poll();
            bw.write(word);
            bw.write("\n");
        }
        bw.flush();
    }
}

/*
        [방법 1.]
        해시 맵으로 상태를 계속 업데이트해주기
        해시 맵을 위 조건에 맞게 정렬해주기
        => 실패.... 도저히 시간 초과를 해결 못하겠네

        [방법 2.]
        단어의 등장은 해시 셋으로 확인하기
        단어의 횟수는 배열로 확인하기
        단, 단어의 인덱스는 해시 맵으로 확인 => 해시 맵은 수정, 삭제 없고 추가만 함

        위로 해결한 후, 그걸 우선 순위 큐에 넣어서 확인하기

 */