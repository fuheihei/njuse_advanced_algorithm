package com.lcy.chapter3.t1;

import java.io.*;
import java.util.*;

/**
 * 最长公共子序列
 * 求多个子序列
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s1 = br.readLine();
            String s2 = br.readLine();
            getLongestCommonSub(s1, s2);
        }
    }

    private static void getLongestCommonSub(String S1, String S2) {
        char[] s1 = S1.toCharArray();
        char[] s2 = S2.toCharArray();
        int n = s1.length, m = s2.length;
        HashSet<String>[][] result = new HashSet[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.setAll(result[i], e -> new HashSet<String>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s1[i] == s2[j]) {
                    HashSet<String> set = new HashSet<>();
                    for (String s : result[i][j]) {
                        result[i + 1][j + 1].add(s);
                    }
                    if (dp[i][j] == 0) {
                        set.add(s1[i] + "");
                    } else {
                        for (String s : result[i + 1][j + 1]) {
                            if (s.length() == dp[i][j]) {
                                set.add(s + s1[i]);
                            }
                        }
                    }
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    result[i + 1][j + 1] = set;
                } else {
                    dp[i + 1][j + 1] = dp[i][j];
                    for (String s : result[i][j]) {
                        result[i + 1][j + 1].add(s);
                    }
                    if (dp[i + 1][j] >= dp[i + 1][j + 1]) {
                        dp[i + 1][j + 1] = dp[i + 1][j];
                        for (String s : result[i + 1][j]) {
                            result[i + 1][j + 1].add(s);
                        }
                    }
                    if (dp[i][j + 1] >= dp[i + 1][j + 1]) {
                        dp[i + 1][j + 1] = dp[i][j + 1];
                        for (String s : result[i][j + 1]) {
                            result[i + 1][j + 1].add(s);
                        }
                    }
                    HashSet<String> set = new HashSet<>(result[i + 1][j + 1]);
                    result[i + 1][j + 1].clear();
                    for (String s : set) {
                        if (s.length() == dp[i + 1][j + 1])
                            result[i + 1][j + 1].add(s);
                    }
                }
            }
        }

        // 处理输出
        ArrayList<String> ans = new ArrayList<>();
        for (String s : result[n][m]) {
            ans.add(s);
        }
        Collections.sort(ans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                for (int i = 0; i < o1.length(); i++) {
                    if (o1.charAt(i) != o2.charAt(i)) {
                        return o1.charAt(i) - o2.charAt(i);
                    }
                }
                return 0;
            }
        });
        for (String s : ans) {
            System.out.println(s);
        }
    }
}
