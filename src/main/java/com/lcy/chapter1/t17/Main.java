package com.lcy.chapter1.t17;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 冒泡排序
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            bubbleSort(A);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.printf("%d\n", A[N - 1]);
        }
    }

    private static void bubbleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }
}
