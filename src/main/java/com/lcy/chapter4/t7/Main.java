package com.lcy.chapter4.t7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 前缀和
 * <p>
 * 在删除数组一个元素后，剩余数组中连续子数组的最大和为？
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];
            String[] d = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            int ans = findMaxSubArraySumRemoveOneElement(N, a);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int findMaxSubArraySumRemoveOneElement(int N, int[] a) {
        int[] pre = new int[N], suf = new int[N];
        int mx = a[0];
        for (int i = 1; i < N; i++) {
            pre[i] = Math.max(0, pre[i - 1] + a[i - 1]);
            mx = Math.max(mx, a[i]);
        }
        if (mx <= 0) return mx;
        int ans = Math.max(pre[N - 1], 0) + a[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            suf[i] = Math.max(0, suf[i + 1] + a[i + 1]);
            ans = Math.max(ans, suf[i] + pre[i] + Math.max(a[i], 0));
        }
        return ans;
    }
}
