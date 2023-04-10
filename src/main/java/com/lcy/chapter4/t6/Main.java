package com.lcy.chapter4.t6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 注意到 n [1,50], a_i [1,20], 都是比较小的范围
 */
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
            int ans = getMaximumSum(a);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int getMaximumSum(int[] a) {
        int n = a.length;
        int mx = Arrays.stream(a).max().getAsInt();
        int[] freq = new int[mx + 1];
        for (int i = 0; i < n; i++) {
            freq[a[i]]++;
        }
        int ans = 0, i = mx;
        while (i > 0) {
            if (freq[i] > 0) {
                ans += i * freq[i];
                freq[i - 1] -= freq[i];
                freq[i] = 0;
            }
            i--;
        }
        return ans;
    }
}
