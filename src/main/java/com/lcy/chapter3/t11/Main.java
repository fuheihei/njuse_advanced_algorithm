package com.lcy.chapter3.t11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 对称子字符串
 * 1538023 中 5380，前半边和后半边的数位加起来是相等的，都是8，要找到这样的偶数长度的子串，可以用dp来做
 * dp[i][j] 表示 s[i:j] （包含i，j）的数位和
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s = br.readLine();
            int ans = findLongestSubStr(s);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int findLongestSubStr(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i] = s[i] - '0';
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + (s[j] - '0');
            }
            // 以 i 为中心点
            for (int k = 0; k < i; k++) {
                if (2 * i - k - 1 < n && dp[k][i - 1] == dp[i][2 * i - k - 1] && (2 * i - 2 * k) > ans) {
                    ans = 2 * i - 2 * k;
                }
            }
        }
        return ans;
    }
}
