/**
 * 문제 이름 : 랭킹전 대기열
 * URL : https://www.acmicpc.net/problem/20006
 * 문제 설명 :
 * 플레이어 간 매칭을 해주는 시스템은 다음과 같음
 * 1. 플레이어가 입장을 신청하였을 때 매칭이 가능한 방이 없다면 새로운 방을 생성하고 입장시킨다. 이때 해당 방에는 처음 입장한 플레이어의
 * 레벨을 기준으로 -10 부터 +10까지 입장 가능하다.
 * 2. 입장 가능한 방이 있다면 입장시킨 후 방의 정원이 모두 찰 때까지 대기시킨다.
 * 2-1. 이때 입장이 가능한 방이 여러개라면 먼저 생성된 방에 입장한다.
 * 3. 방의 정원이 모두 차면 게임을 시작시킨다.
 * 플레이어의 수 p, 플레이어의 닉네임 n, 플레이어의 레벨 l, 방 한개의 정원 m이 주어졌을 때, 위와 같은 방법으로 매칭해주고 최종적으로
 * 만들어진 방의 상태와 입장 플레이어들을 출력해라
 * 시간복잡도 :
 * 핵심 로직 및 생각 :
 * 소요 시간 :
 * 제출할 때, package 삭제할 것
 */

package baekjoon.t20000f20999.p20006;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static String[][] player;
    static Map<String, Integer> playerManage = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);

        player = new String[n][2];

        for (int i = 0; i < n; i++) {
            String[] tmp2 = br.readLine().split(" ");
            player[i][0] = tmp2[0];
            player[i][1] = tmp2[1];
            playerManage.put(tmp2[1], Integer.parseInt(tmp2[0]));
        }
        game();
    }

    static void game() {
        List<List<String>> room = new ArrayList<>();

        go:
        for (int i = 0; i < n; i++) {
            String playerLevel = player[i][0];
            String playerName = player[i][1];

            for (int j = 0; j < room.size(); j++) {
                // 여기를 벗어나면 새로운 방을 만들기
                List<String> roomInfo = room.get(j);
                if (roomInfo.size() == m)
                    continue;

                int roomOwnerLevel = playerManage.get(roomInfo.get(0));
                int check = roomOwnerLevel - Integer.parseInt(playerLevel);

                if (Math.abs(check) > 10)
                    continue;

                roomInfo.add(playerName);
                continue go;
            }
            room.add(new ArrayList<>());
            room.get(room.size() - 1).add(playerName);
        }
        resultPrint(room);
    }

    static void resultPrint(List<List<String>> roomInfo) {
        for (List<String> tmp : roomInfo) {
            if (tmp.size() == m) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }
            Collections.sort(tmp);
            for (String tmp2 : tmp) {
                System.out.printf("%d %s %n", playerManage.get(tmp2), tmp2);
            }
        }
    }
}

// 시간을 널널하게 사용하면 됨
// 300 x 300 => 90000임
// 문자열 정렬;;; => 따로 해야할 듯함
// 그냥 이거 MAP으로 관리해서 MAP을 정렬하면 될 듯함 => 시간이 남거나 뒤에 문제를 도저히 못풀겟으면 하기
