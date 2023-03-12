package com.lcy.chapter1.t_01_18;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 计数排序
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
            Arrays.sort(A);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.printf("%d\n", A[N - 1]);

        }
    }
}
