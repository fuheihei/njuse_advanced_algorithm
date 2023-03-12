package com.lcy.chapter1.t_01_14;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 排序
 *
 * 大意是将N*N的矩阵按照下标排序递增排序
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N * N];
            for (int i = 0; i < N * N; i++) {
                A[i] = sc.nextInt();
            }
            Arrays.sort(A);
            for (int i = 0; i < N * N - 1; i++) {
                System.out.printf("%d ", A[i]);
            }
            System.out.printf("%d\n", A[N * N - 1]);
        }
    }
}
