package com.lcy.chapter4.t3;

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
            int n = Integer.parseInt(br.readLine());
            String[] d = br.readLine().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            boolean[] vis = new boolean[10];
            int ans = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                Arrays.fill(vis, false);
                int x = a[i];
                while (x > 0) {
                    vis[x % 10] = true;
                    x /= 10;
                }
                int res = findNoRepeatSubSet(a, i + 1, vis, n, a[i]);
                ans = Math.max(res, ans);
            }
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int findNoRepeatSubSet(int[] a, int i, boolean[] vis, int n, int res) {
        if (i == n) {
            return res;
        }
        int x = a[i];
        boolean dup = false;
        while (x > 0) {
            if (vis[x % 10]) {
                dup = true;
                break;
            }
            x /= 10;
        }
        int res2 = 0;
        if (!dup) {
            x = a[i];
            while (x > 0) {
                vis[x % 10] = true;
                x /= 10;
            }
            res2 = findNoRepeatSubSet(a, i + 1, vis, n, res + a[i]);
            x = a[i];
            while (x > 0) {
                vis[x % 10] = false;
                x /= 10;
            }
        }
        return Math.max(res2, findNoRepeatSubSet(a, i + 1, vis, n, res));
    }
}
