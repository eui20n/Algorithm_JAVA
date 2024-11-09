/**
 * 문제 이름 : 비슷한 단어
 * URL : https://www.acmicpc.net/problem/2179
 * 문제 설명 :
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t2000f2999.p2179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static String[] wordArr;

    public static void main(String[] args) throws IOException {
        input();
        check();
    }

    static void check() {
        Integer[] idxArr = makeIdxArr();
//        print(idxArr);

        int maxCnt = 0;
        int[] resultIdx = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}; // Sidx, Tidx

        for (int i = 0; i < N - 1; i++) {
            int standardCnt = 0;
            int standardIdx = idxArr[i];
            for (int j = i + 1; j < N; j++) {
                int compareIdx = idxArr[j];

                int cnt = cntSameWord(wordArr[standardIdx], wordArr[compareIdx]);
                if (standardCnt > cnt)
                    break;

                int smallerIdx = Math.min(standardIdx, compareIdx); // 첫 번째
                int biggerIdx = Math.max(standardIdx, compareIdx); // 두 번째

                if (maxCnt < cnt) {
                    // 정답 갱신
                    updateIdx(resultIdx, smallerIdx, biggerIdx);
                    maxCnt = cnt;
                } else if (maxCnt == cnt) {
                    // 더 빨리 등장하는 것이 정답
                    if (resultIdx[0] == smallerIdx) {
                        // 첫 번째 인덱스가 같을 때
                        if (resultIdx[1] > biggerIdx) {
                            // 두 번째 인덱스가 더 큼
                            // 정답 갱신
                            updateIdx(resultIdx, smallerIdx, biggerIdx);
                        }
                    } else if (resultIdx[0] > smallerIdx) {
                        // 첫 번째 인덱스가 더 큰
                        // 정답 갱신
                        updateIdx(resultIdx, smallerIdx, biggerIdx);
                    }
                }
                standardCnt = cnt;
            }
        }
//        System.out.println(maxCnt);
        System.out.println(wordArr[resultIdx[0]]);
        System.out.println(wordArr[resultIdx[1]]);
    }

    static void print(Integer[] idxArr) {
        for (int idx : idxArr) {
            System.out.println(wordArr[idx]);
        }
        System.out.println();
    }

    static void updateIdx(int[] resultIdx, int standardIdx, int compareIdx) {
        resultIdx[0] = standardIdx;
        resultIdx[1] = compareIdx;
    }

    static int cntSameWord(String word1, String word2) {
        char[] wordCharArr1 = word1.toCharArray();
        char[] wordCharArr2 = word2.toCharArray();

        int loopSize = Math.min(wordCharArr1.length, wordCharArr2.length);
        int cnt = 0;

        for (int i = 0; i < loopSize; i++) {
            if (wordCharArr1[i] != wordCharArr2[i])
                return cnt;
            cnt++;
        }
        return cnt;
    }

    static Integer[] makeIdxArr() {
        // 배열을 생성 후, 정렬 까지 해서 반환
        Integer[] returnArr = new Integer[N];
        for (int i = 0; i < N; i++) {
            returnArr[i] = i;
        }

        Arrays.sort(returnArr, (o1, o2) -> {
            String o1Word = wordArr[o1];
            String o2Word = wordArr[o2];
            return o1Word.compareTo(o2Word);
        });

        return returnArr;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wordArr = new String[N];

        for (int i = 0; i < N; i++) {
            wordArr[i] = br.readLine();
        }
    }
}

/*
    정렬하면 되잖아!

    기준을 잡고 그거랑 같은 것을 계속 봐야함
    그래봤자 최대 100개만 더 보면 되는 것임

    [방법 1.] => 틀림
    1. 정렬을 함
    2. 그 후, 자신과 하나 앞을 비교
    2-1. 이렇게 하면 반례가 없을 줄 알았는데, 모두 길이가 같을 때, 반례가 생김
         나와 한 칸 바로 앞에 있는 것만 보니까, 정답은 세 칸 앞인 경우가 생김 => 이 경우는 한 문자열과 비슷한 문자열이 여러개이고, 그것이 정답일 때 생김
    3. 비교 한 값을 리스트에 넣음
    4. 그 리스트를 조건에 맞게 정렬
    5. 정렬 후, 리스트의 가장 앞에 있는 값을 출력

    [방볍 2.] => 메모리 초과
    1. 정렬을 함
    2. 기준 문자열 부터 계속해서 비슷한 문자열인지 확인을 할 것
    2-1. 여기서, 만약에 이전 값보다 더 작아지면 중단하고, 다음 문자열로 넘어가기
    3. 2번에서 확인 한 값을 다 리스트에 넣음
    4. 리스트 정렬
    5. 정렬 후, 가장 앞에 있는 값을 출력

    [방법 3.] => 정답
    그냥 입력을 다 같은 후에, 인덱스로 계속 접근하면서 처리하기
    방법은 [방법 2.]와 동일한데, 접근만 입덱스로 계속하는 것
    => 이렇게 하면 시간과 공간을 모두 줄일 수 있을 듯함
 */