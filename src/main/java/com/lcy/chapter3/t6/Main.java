package com.lcy.chapter3.t6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 牛
 * <p>
 * 斐波那契数列，线性dp优化
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            long N = Long.parseLong(br.readLine());
            long x = getFib(N + 1);
            bw.write(x + "\n");
        }
        bw.flush();
    }

    static int MOD = (int) 1e9 + 7;

    private static long getFib(long N) {
        int big = 0, small = 1;
        long[][] mat = new long[][]{{1, 1}, {1, 0}};
        long[][] res = new long[][]{{1, 0}, {0, 1}};
        while (N > 0) {
            if ((N & 1) != 0) {
                res = mul(res, mat);
            }
            N >>= 1;
            mat = mul(mat, mat);
        }
        return small * res[0][1] % MOD;
    }

    public static long[][] mul(long[][] mat1, long[][] mat2) {
        int n = mat1.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    res[i][j] += mat1[i][k] * mat2[k][j];
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
    }

}
