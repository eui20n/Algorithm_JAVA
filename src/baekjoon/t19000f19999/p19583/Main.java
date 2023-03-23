/**
 * 문제 이름 : 싸이버개강총회
 * URL : https://www.acmicpc.net/problem/19583
 * 문제 설명 :
 * 출석부를 다음과 같이 관리함
 * 1. 개강총회를 시작하기 전에, 학회원의 입장 확인 여부를 확인. 확회원의 입장 여부는 개강총회가 시작한 시간 이전에 대화를 한 적이 있는
 * 학회원의 닉네임을 보고 체크한다. 개강총회를 시작하자마자 채팅 기록을 남긴 학회원도 제 시간에 입장이 확인된 것으로 간주
 * 2. 개강총회를 끝내고 나서, 스트리밍 끝낼 때까지 학회원의 퇴장 여부를 확인한다. 학회원의 퇴장 여부는 개강총회가 끝나고 스트리밍이 끝날 때까지
 * 대화를 한 적이 있는 학회원의 닉네임을 체크. 개강총회가 끝나자마자 채팅 기록을 남겼거나, 개강총회 스트리밍이 끝나자마자 채팅 기록을 남긴 학회원도
 * 제 시간에 퇴장이 확인된 것으로 간주
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t19000f19999.p19583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    static int startTime;
    static int endTime1; // 개강총회가 끝난 시간
    static int endTime2; // 스트리밍을 끝낸 시간
    static int result = 0;
    static HashSet<String> hashSet = new HashSet<>();
    static HashSet<String> alreadyCheck = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        startTime = changeSecond(tmp[0]);
        endTime1 = changeSecond(tmp[1]);
        endTime2 = changeSecond(tmp[2]);
        int standard = startTime;

        while (true) {
            if (standard >= endTime2) {
                break;
            }

            String[] student = br.readLine().split(" ");
            standard = check(student, standard);
        }

        System.out.println(result);
    }

    static int check(String[] student, int standard) {
        int studentTime = changeSecond(student[0]);
        String studentName = student[1];

        while (true) {
            if (standard == 1441) {
                return standard;
            }

            if (studentTime <= startTime && !hashSet.contains(studentName)) {
                hashSet.add(studentName);
                return standard;
            }
            if (studentTime >= endTime1 && studentTime <= endTime2 && hashSet.contains(studentName) && !alreadyCheck.contains(studentName)) {
                alreadyCheck.add(studentName);
                System.out.println(studentName);
                result += 1;
                return standard;
            }
            if (standard == studentTime) {
                return standard;
            }
            System.out.println(standard);
            standard += 1;
        }
    }

    static int changeSecond(String time) {
        String[] timeArr = time.split(":");
        int second = 0;
        second += Integer.parseInt(timeArr[0]) * 60;
        second += Integer.parseInt(timeArr[1]);

        return second;
    }
}

// 예외 처리만 하면 되는데
// 이거 예외처리 할줄 모르는데
// 다른 방법을 생각해야함