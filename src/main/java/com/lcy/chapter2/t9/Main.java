package com.lcy.chapter2.t9;

import java.util.Arrays;
import java.util.Scanner;

// 和第一章 宠物屋涂色 二分的那题一样
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int K = sc.nextInt();
            int N = sc.nextInt();
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
            }
            solve(N, a, K);
        }
    }

    private static void solve(int n, int[] a, int k) {
        // 二分，可知每个工人完成作业需要的最长时间，范围是 [max(a), sum(a)],
        // 那么对 (max(a)-1,sum(a)+1) 进行二分
        int left = Arrays.stream(a).max().getAsInt() - 1,
                right = Arrays.stream(a).sum() + 1;
        while (left + 1 < right) {
            int mid = left + right >> 1;
            if (check(mid, a, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
    }

    public static boolean check(int x, int[] a, int k) {
        int bulk = 1, bulkSize = a[0];
        for (int i = 1; i < a.length; i++) {
            bulkSize += a[i];
            if (bulkSize > x) {
                bulkSize = a[i];
                bulk++;
            }
        }
        return bulk <= k;
    }

}
