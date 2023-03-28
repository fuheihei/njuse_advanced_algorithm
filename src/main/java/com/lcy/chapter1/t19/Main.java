package com.lcy.chapter1.t19;

import java.util.*;

/**
 * 非递归快排
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
            quickSort2(A, 0, N);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.printf("%d\n", A[N - 1]);
        }
    }


    static int partition(int[] a, int i, int j) {
        int w = a[i];
        while (i < j) {
            while (i < j && a[j] >= w) j--;
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            while (i < j && a[i] <= w) i++;
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = w;
        return i;
    }

    // 非递归
    // 用stack模拟快排递归的过程
    private static void quickSort2(int[] a, int from, int to) {
        int l = from, r = to - 1;
        Deque<Integer> stack = new LinkedList<>();
        stack.offer(r);
        stack.offer(l);
        while (!stack.isEmpty()) {
            int ll = stack.removeLast();
            int rr = stack.removeLast();
            int mid = partition(a, ll, rr);
            if (mid + 1 < rr) {
                stack.offer(rr);
                stack.offer(mid + 1);
            }
            if (ll < mid - 1) {
                stack.offer(mid - 1);
                stack.offer(ll);
            }
        }
    }

    // 递归
    private static void quickSort(int[] a, int from, int to) {
        int l = from, r = to - 1;
        if (l < r) {
            int i = partition(a, l, r);
            quickSort(a, l, i);
            quickSort(a, i + 1, r + 1);
        }
    }
}
