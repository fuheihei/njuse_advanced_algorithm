package com.lcy.chapter2.t7;

import java.util.*;


/**
 * 排序
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            String[] d = sc.nextLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int m = Integer.parseInt(d[1]);
            long[] l = new long[n];
            long[] r = new long[n];
            d = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                l[i] = Long.parseLong(d[2 * i]);
                r[i] = Long.parseLong(d[2 * i + 1]);
            }
            long[] q = new long[m];
            d = sc.nextLine().split(" ");
            for (int i = 0; i < m; i++) {
                q[i] = Long.parseLong(d[i]);
            }
            solve(n, m, l, r, q);
        }
    }

    private static void solve(int n, int m, long[] l, long[] r, long[] q) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = r[i] - l[i] + 1;
        }
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + a[i];
        }
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            // 确定rank在哪个桶
            int j = bisect_left(sums, q[i]) - 1;
            long rank = l[j] + q[i] - sums[j] - 1;
            tmp.add(rank + "");
        }
        System.out.println(String.join(" ", tmp));
    }

    private static int bisect_left(long[] a, long t) {
        int left = 0, right = a.length;
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

}
