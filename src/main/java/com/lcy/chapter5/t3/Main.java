package com.lcy.chapter5.t3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


/**
 * 经典dp
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int amount = Integer.parseInt(d[1]);
            d = br.readLine().split(" ");
            int[] coin = new int[n];
            for (int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(d[i]);
            }
            int ans = coinChange(coin, amount);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    public static int coinChange(int[] coin, int amount) {
        int n = coin.length;
        int[] dp = new int[amount + 1];
        int inf = 0x3f3f3f;
        Arrays.sort(coin);
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                if (coin[j] > i) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - coin[j]] + 1);
            }
        }
        return dp[amount] == inf ? -1 : dp[amount];
    }
}