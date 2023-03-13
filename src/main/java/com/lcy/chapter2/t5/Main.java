package com.lcy.chapter2.t5;

import java.util.*;


/**
 * 二分法
 * <p>
 * 原题
 * https://practice.geeksforgeeks.org/problems/magnet-array-problem3743/1
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            int N = Integer.parseInt(sc.nextLine());
            double[] pos = new double[N];
            String[] data = sc.nextLine().split("\\s+");
            for (int i = 0; i < data.length; i++) {
                pos[i] = Double.parseDouble(data[i]);
            }
            Arrays.sort(pos);
            solve(N, pos);
        }
    }

    private static void solve(int n, double[] pos) {
        double[] ans = new double[n - 1];
        for (int i = 0; i < n - 1; i++) {
            ans[i] = binary_search(pos, i);
        }
        print(ans);
    }

    // 查找坐标位于[pos[i],pos[i+1]] 之间位置的点
    private static double binary_search(double[] pos, int i) {
        double left = pos[i], right = pos[i + 1];
        double epsilon = 0.00000000001;
        // 缩小误差,原题的误差是 0.0000000000001，会超时
        while (left < right) {
            double mid = (left + right) / 2;
            double netForce = 0;
            for (int j = 0; j <= i; j++) {
                netForce += 1.0 / (mid - pos[j]);
            }
            for (int j = i + 1; j < pos.length; j++) {
                netForce -= 1.0 / (pos[j] - mid);
            }
            if ((-epsilon) < netForce && netForce < epsilon) {
                return mid;
            } else if (netForce > 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    // 输出结果
    private static void print(double[] ans) {
        for (int i = 0; i < ans.length - 1; i++) {
            System.out.printf("%.2f ", ans[i]);
        }
        System.out.printf("%.2f\n", ans[ans.length - 1]);

    }

}
