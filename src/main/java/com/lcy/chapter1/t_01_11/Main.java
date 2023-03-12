package com.lcy.chapter1.t_01_11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 排序问题
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[][] stu = new int[N][3];
            for (int i = 0; i < N; i++) {
                stu[i][0] = sc.nextInt();
                stu[i][1] = sc.nextInt();
                stu[i][2] = sc.nextInt();
            }
            Arrays.sort(stu, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] != o2[0]) {
                        return Integer.compare(o1[0], o2[0]);
                    } else if (o1[1] != o2[1]) {
                        return -Integer.compare(o1[1], o2[1]);
                    } else {
                        return Integer.compare(o1[2], o2[2]);
                    }
                }
            });
            for (int i = 0; i < N; i++) {
                System.out.printf("%d %d %d\n", stu[i][0], stu[i][1], stu[i][2]);
            }
        }
    }
}
