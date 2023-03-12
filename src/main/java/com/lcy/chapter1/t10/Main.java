package com.lcy.chapter1.t10;

import java.util.Scanner;

// 书本分法
// 二分法
// 和 宠物屋涂色 类似
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
            }
            int M = sc.nextInt();
            solve(N, a, M);
        }
    }

    private static void solve(int n, int[] a, int m) {
        if (n < m) {
            System.out.println(-1);
            return;
        }
        int left = a[0], right = 0;
        for (int i = 0; i < a.length; i++) {
            left = Math.max(left, a[i]);
            right += a[i];
        }
        while (left < right) {
            int mid = left + right >> 1;
            int bkCnt = get_buket_cnt(a, mid);
            if (bkCnt <= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right);
    }

    private static int get_buket_cnt(int[] a, int bkSize) {
        int currentBkSize = 0;
        int bucketCnt = 1;
        for (int e : a) {
            currentBkSize += e;
            if (currentBkSize > bkSize) {
                bucketCnt++;
                currentBkSize = e;
            }
        }
        return bucketCnt;
    }
}
