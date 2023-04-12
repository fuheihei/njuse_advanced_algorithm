package com.lcy.chapter5.t7;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            String[] d = sc.nextLine().split(" ");
            int n = d.length;
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            d = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(d[i]);
            }
            int ans = minDiff(a, b);
            System.out.println(ans);
        }
    }

    private static int minDiff(int[] a, int[] b) {
        int n = a.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int[] c = new int[2 * n];
        // 消除负数对数组元素的影响
        for (int i = 0; i < n; i++) {
            c[i] = a[i];
            c[n + i] = b[i];
            min = Math.min(c[i], min);
            min = Math.min(c[i + n], min);
        }
        for (int i = 0; i < n; i++) {
            c[i] -= min;
            c[n + i] -= min;
            sum += c[i];
            sum += c[i + n];
        }
        int vmax = sum / 2;
        int[][] dp = new int[n + 2][vmax + 2];
        int[][] sel = new int[n + 2][vmax + 2];
        for (int j = 0; j < dp.length; j++) {
            for (int k = 0; k < dp[j].length; k++) {
                if (j == 0) {
                    dp[j][k] = 0;
                } else {
                    dp[j][k] = -1;
                }
                sel[j][k] = 0;
            }
        }
        //i从1开始而不是从0，是为了避免j-1<0越数组下界的情况,对arr数组进行遍历
        for (int j = 1; j <= 2 * n; j++) {
            for (int k = (Math.min(j, n)); k >= 1; k--) {
                for (int v = c[j - 1]; v <= vmax; v++) {
                    if (dp[k - 1][v - c[j - 1]] < 0) {
                        continue;
                    } else {
                        dp[k][v] = Math.max(dp[k][v], dp[k - 1][v - c[j - 1]] + c[j - 1]);
                    }

                }
            }
        }
        return sum - 2 * dp[n][vmax];
    }

}
