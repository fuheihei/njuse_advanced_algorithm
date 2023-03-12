package com.lcy.chapter1.t_01_22;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            String[] data = sc.nextLine().trim().split("\\s+");
            long[] a = new long[data.length];
            for (int i = 0; i < data.length; i++) {
                a[i] = Long.parseLong(data[i]);
            }
            String[] intervals = sc.nextLine().trim().split("\\s+");
            for (int i = 0; i < intervals.length; i++) {
                int inter = Integer.parseInt(intervals[i]);
                shellSort(a, inter);
            }
            //打印
            for (int i = 0; i < a.length - 1; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println(a[a.length - 1]);
        }
    }

    // 将间隔 interval 的元素升序排序
    public static void shellSort(long[] nums, int interval) {
        for (int i = interval; i < nums.length; i++) {
            for (int j = i; j >= interval && nums[j] < nums[j - interval]; j -= interval) {
                long t = nums[j];
                nums[j] = nums[j - interval];
                nums[j - interval] = t;
            }
        }
    }
}

