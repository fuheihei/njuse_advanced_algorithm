package com.lcy.chapter4.t5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 打家劫舍变形问题
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] sell = new int[n][3];
            for (int i = 0; i < n; i++) {
                String[] d = br.readLine().split(" ");
                sell[i][0] = Integer.parseInt(d[0]);
                sell[i][1] = Integer.parseInt(d[1]);
                sell[i][2] = Integer.parseInt(d[2]);
            }
            int f0 = sell[0][0], f1 = sell[0][1], f2 = sell[0][2];
            for (int i = 1; i < n; i++) {
                int y0 = Math.min(f1, f2) + sell[i][0];
                int y1 = Math.min(f0, f2) + sell[i][1];
                int y2 = Math.min(f0, f1) + sell[i][2];
                f0 = y0;
                f1 = y1;
                f2 = y2;
            }
            int ans = Math.min(f0, Math.min(f1, f2));
            bw.write(ans + "\n");
        }
        bw.flush();
    }
}
