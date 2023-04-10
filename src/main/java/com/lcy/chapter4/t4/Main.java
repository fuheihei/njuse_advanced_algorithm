package com.lcy.chapter4.t4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 01 背包问题
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int h = Integer.parseInt(d[1]);
            int p = Integer.parseInt(d[2]);
            int[] w = new int[n];
            int[] v = new int[n];
            for (int i = 0; i < n; i++) {
                d = br.readLine().split(" ");
                w[i] = Integer.parseInt(d[0]);
                v[i] = Integer.parseInt(d[1]);
            }
            int[] dp = new int[h + 1];
            for (int i = 0; i < n; i++) {
                for (int j = h; j >= w[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            }
            if (dp[h] < p) {
                bw.write("NO\n");
            } else {
                for (int j = 0; j <= h; j++) {
                    if (dp[j] >= p) {
                        bw.write("YES " + j + "\n");
                        break;
                    }
                }
            }
        }
        bw.flush();
    }
}
