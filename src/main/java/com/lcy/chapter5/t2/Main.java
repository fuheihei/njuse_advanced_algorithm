package com.lcy.chapter5.t2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * 原题
 * https://practice.geeksforgeeks.org/problems/geek-collects-the-balls5515/1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int N = Integer.parseInt(d[0]);
            int M = Integer.parseInt(d[1]);
            int[] a = new int[N];
            int[] b = new int[M];
            d = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            d = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(d[i]);
            }
            int ans = maxBalls(N, M, a, b);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    static int maxBalls(int n, int m, int a[], int b[]) {
        int i = 0, j = 0, s1 = 0, s2 = 0, c1, c2;
        while (i < n && j < m) {
            if (a[i] < b[j]) {
                s1 += a[i++];
            } else if (a[i] > b[j]) {
                s2 += b[j++];
            } else {
                int x = a[i], oldS1 = s1, oldS2 = s2;
                for (c1 = 0; i < n && a[i] == x; ++c1, ++i) ;
                for (c2 = 0; j < m && b[j] == x; ++c2, ++j) ;

                s1 = oldS1 + ((c1 > 1 && c2 > 1) ? (c1 + c2 - 2) * x : c1 * x);
                s1 = Math.max(s1, oldS2 + (c1 + c2 - 1) * x);

                s2 = oldS2 + ((c1 > 1 && c2 > 1) ? (c1 + c2 - 2) * x : c2 * x);
                s2 = Math.max(s2, oldS1 + (c1 + c2 - 1) * x);
            }
        }
        for (; i < n; ++i) {
            s1 += a[i];
        }
        for (; j < m; ++j) {
            s2 += b[j];
        }
        return Math.max(s1, s2);
    }
}