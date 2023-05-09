package com.lcy.chapter1.t18;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * 计数排序
 *
 * 这题因为数组申请空间有限制，只有用hashMap形式才能通过，用数组统计会有较多冗余空间。
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int N = d.length - 1;
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(d[i + 1]);
            }
            countSort2(A);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.printf("%d\n", A[N - 1]);
        }
    }

    // 计数排序
    public static void countSort(int[] a) {
        // 将最大值，最小值设置为bin
        // 如果最小值是负数，需要做离散化
        int mx = a[0];
        int mn = a[0];
        for (int i = 0; i < a.length; i++) {
            mx = Math.max(mx, a[i]);
            mn = Math.min(mn, a[i]);
        }
        int[] cnt = new int[mx - mn + 1];
        for (int i = 0; i < a.length; i++) {
            cnt[a[i] - mn]++;
        }
        int idx = 0;
        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                a[idx++] = i + mn;
            }
        }
    }

    // 计数排序
    public static void countSort2(int[] a) {
        // 将最大值，最小值设置为bin
        // 如果最小值是负数，需要做离散化
        TreeMap<Integer, Integer> counter = new TreeMap<>();
        for (int i = 0; i < a.length; i++) {
            counter.put(a[i], counter.getOrDefault(a[i], 0) + 1);
        }
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : counter.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                a[idx++] = e.getKey();
            }
        }
    }

}
