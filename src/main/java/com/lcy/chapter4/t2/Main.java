package com.lcy.chapter4.t2;

import java.io.*;
import java.util.HashSet;

// 0 1 2 3 4 5 6 | 7 8
// 0 0 1 1 1 0 1 | 0 0

/**
 * 找规律 + 矩阵前缀和
 * <p>
 * 1.对于 C 的规律是
 * 0 1 2 3 4 5 6 | 7 8
 * 0 0 1 1 1 0 1 | 0 0
 * 也就是奇偶性每7个一循环
 * <p>
 * 2.构建 (MAX_N * MAX_N) 的矩阵
 * 求矩阵的前缀和 dp[i][j] 表示 左上角(0,0) 到 (i,j)的矩阵元素和
 * 那么对于每次询问 n
 * 即求 dp[n][n] 的值
 */
public class Main {
    static int MAX_N = (int) 1e3;
    static int[][] map = new int[MAX_N][MAX_N];
    static int[][] dp = new int[MAX_N + 1][MAX_N + 1];

    static void init() {
        HashSet<Integer> set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(6);
        for (int i = 0; i < MAX_N; i++) {
            for (int j = 0; j < MAX_N; j++) {
                int x = (i + 1) * (j + 1);
                x %= 7;
                x = (x * x * x) % 7;
                if (set.contains(x)) {
                    map[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < MAX_N; i++) {
            for (int j = 0; j < MAX_N; j++) {
                dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j] + map[i][j] - dp[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n][n] + "\n");
        }
        bw.flush();
    }
}
