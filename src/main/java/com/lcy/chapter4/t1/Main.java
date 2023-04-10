package com.lcy.chapter4.t1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int r = Integer.parseInt(d[0]);
            int c = Integer.parseInt(d[1]);
            int[][] grid = new int[r][c];
            d = br.readLine().split(" ");
            int k = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    grid[i][j] = Integer.parseInt(d[k++]);
                }
            }
            int[][] dp = new int[r][c];
            for (int i = 0; i < r; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[r - 1][c - 1] = Math.max(1, 1 - grid[r - 1][c - 1]);
            for (int i = r - 1; i >= 0; i--) {
                for (int j = c - 1; j >= 0; j--) {
                    if (j >= 1)
                        dp[i][j - 1] = Math.min(dp[i][j - 1],
                                Math.max(dp[i][j] - grid[i][j - 1], 1));
                    if (i >= 1)
                        dp[i - 1][j] = Math.min(dp[i - 1][j],
                                Math.max(dp[i][j] - grid[i - 1][j], 1));
                }
            }
            bw.write(dp[0][0] + "\n");
        }
        bw.flush();
    }
}
