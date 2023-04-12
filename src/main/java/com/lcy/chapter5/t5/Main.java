package com.lcy.chapter5.t5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


/**
 * 差分，输入有特殊空格符号，需要单独处理下
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().replaceAll("[ | ]+", ""));
            String[] d = br.readLine().split("[ | ]+");
            int[] arrive = new int[n];
            for (int i = 0; i < n; i++) {
                arrive[i] = Integer.parseInt(d[i]);
            }
            int[] departure = new int[n];
            d = br.readLine().split("[ | ]+");
            for (int i = 0; i < n; i++) {
                departure[i] = Integer.parseInt(d[i]);
            }
            int ans = solve(arrive, departure, n);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int solve(int[] arrive, int[] departure, int n) {
        int maxTime = Arrays.stream(departure).max().getAsInt();
        int[] a = new int[maxTime + 2];
        for (int i = 0; i < n; i++) {
            a[arrive[i]]++;
            a[departure[i] + 1]--;
        }
        int cnt = 0, mx = 0;
        for (int i = 0; i < a.length; i++) {
            cnt += a[i];
            mx = Math.max(mx, cnt);
        }
        return mx;
    }
}