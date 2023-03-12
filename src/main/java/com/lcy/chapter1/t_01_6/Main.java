package com.lcy.chapter1.t_01_6;

import java.util.Arrays;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long MAX_P = (long) 1e15;
        ArrayList<Long> sums = new ArrayList<>();
        sums.add(1L);
        //预处理
        long pre = 1;
        for (int i = 2; i < (int) 1e6; i++) {
            pre += (long) i * i;
            if (pre > MAX_P) break;
            sums.add(pre);
        }
        while (T-- > 0) {
            long p = sc.nextLong();
            System.out.println(bisect_right(sums, p));
        }
    }

    public static int bisect_right(ArrayList<Long> a, long target) {
        int left = -1, right = a.size();
        while (left + 1 < right) {
            int mid = left + right >> 1;
            if (a.get(mid) <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
