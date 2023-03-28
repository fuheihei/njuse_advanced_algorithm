package com.lcy.chapter1.t20;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 非递归归并排序
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            mergeSort2(A, 0, N);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.printf("%d\n", A[N - 1]);
        }
    }

    /**
     * 非递归归并排序
     * 用stack来模拟归并的过程
     */
    private static void mergeSort2(int[] a, int from, int to) {
        int tmp[] = new int[a.length];
        Deque<Integer> stack = new LinkedList<>();
        stack.offer(to);
        stack.offer(from);
        while (!stack.isEmpty()) {
            Integer ll = stack.removeLast();
            Integer rr = stack.removeLast();
            if (ll <= rr - 1) continue;
            int mid = ll + rr >> 1;
            merge(a, tmp, ll, mid);
            merge(a, tmp, mid, rr);
        }
        merge(a, tmp, from, to);
    }

    /**
     * 递归归并排序
     */
    private static void mergeSort1(int[] a, int from, int to) {
        int tmp[] = new int[a.length];
        merge(a, tmp, from, to);
    }

    static void merge(int a[], int t[], int ll, int rr) {
        // 用来把 a 数组 [ll, rr - 1] 这一区间的数排序。 t
        // 数组是临时存放有序的版本用的。
        if (rr - ll <= 1) return;
        int mid = ll + rr >> 1;
        merge(a, t, ll, mid);
        merge(a, t, mid, rr);
        int p = ll, q = mid, s = ll;
        while (s < rr) {
            if (p >= mid || (q < rr && a[p] > a[q])) {
                t[s++] = a[q++];
                // ans += mid - p;
            } else
                t[s++] = a[p++];
        }
        for (int i = ll; i < rr; ++i) a[i] = t[i];
    }
}
