package com.lcy.chapter4.t8;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 贪心
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int x = Integer.parseInt(d[1]);
            int y = Integer.parseInt(d[2]);
            int[][] ab = new int[n][2];
            d = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                ab[i][0] = Integer.parseInt(d[i]);
            }
            d = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                ab[i][1] = Integer.parseInt(d[i]);
            }
            // 绝对值大的排在前面
            Arrays.sort(ab, (ab2, ab1) -> Math.abs(ab1[0] - ab1[1]) - Math.abs(ab2[0] - ab2[1]));
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int a = ab[i][0], b = ab[i][1];
                if (a > b && x > 0) {
                    sum += a;
                    x--;
                } else if (a < b && y > 0) {
                    sum += b;
                    y--;
                } else if (x > 0) {
                    x--;
                    sum += a;
                } else {
                    y--;
                    sum += b;
                }
            }
            bw.write(sum + "\n");
        }
        bw.flush();
    }

}
