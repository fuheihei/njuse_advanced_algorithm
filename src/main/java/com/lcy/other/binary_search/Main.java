package com.lcy.other.binary_search;

import java.util.*;
import java.io.*;

/**
 * 二分查找
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] a = new int[]{1, 2, 3, 4, 4, 5, 5, 6, 7};
        for (int i = 0; i < a.length; i++) {
            int j = bisect_left(a, a[i]);
            System.out.println(j);
        }
        br.close();
        bw.close();
    }

    public static int bisect_left(int[] a, int t) {
        int left = -1, right = a.length;
        while (left + 1 < right) {
            int mid = left + right >> 1;
            if (a[mid] < t) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static int bisect_right(int[] a, int t) {
        int left = -1, right = a.length;
        while (left + 1 < right) {
            int mid = left + right >> 1;
            if (a[mid] <= t) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
