package com.lcy.chapter3.t10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int m = Integer.parseInt(d[1]);
            d = br.readLine().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            d = br.readLine().split(" ");
            int[] q = new int[m];
            for (int i = 0; i < m; i++) {
                q[i] = Integer.parseInt(d[i]);
            }
            solve(n, a, q);
        }
        bw.flush();
    }

    private static void solve(int n, int[] a, int[] q) {
        int max = a[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
        }
        int[] cnt = new int[max + 1];
        int[] ans = new int[max + 1];
        for (int i = 0; i < n; i++) {
            ++cnt[a[i]];
        }
        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                ans[i] += cnt[j];
            }
        }
        for (int i = 0; i < q.length; i++) {
            q[i] = ans[q[i]];
        }
        System.out.println(String.join(" ", Arrays.stream(q).mapToObj(x -> x + "").toArray(String[]::new)));
    }
}
