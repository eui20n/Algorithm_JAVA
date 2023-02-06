package baekjoon.t11000f11999.p11650;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr[i] = tmp;
        }

        br.close();

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String x, y;

        for (int i = 0; i < N; i++) {
            x = Integer.toString(arr[i][0]);
            y = Integer.toString(arr[i][1]);

            bw.write(x + " " + y + "\n");
            bw.flush();
        }

        bw.close();

    }

}