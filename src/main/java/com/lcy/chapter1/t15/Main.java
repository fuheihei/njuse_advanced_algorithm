package com.lcy.chapter1.t15;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * 最小交换次数
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
            System.out.println(minSwap(A));
        }
    }

    // 针对不重复的元素
    public static int minSwap(int[] nums) {
        //1.将数组离散化
        int[] b = new int[nums.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = nums[i];
        }
        Arrays.sort(b);
        int N = nums.length;
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[i] = left_bound(b, nums[i]);
        }

        //2.对离散化后的数组找环结构
        boolean[] vis = new boolean[N];
        int ans = N;
        for (int i = 0; i < N; i++) {
            int x = a[i];
            if (vis[x]) continue;
            while (!vis[x]) {
                vis[x] = true;
                x = a[x];
            }
            ans--;
        }
        return ans;
    }


   static int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 注意
        while (left < right) { // 注意
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            } else if (nums[mid] == target) {
                right = mid;
            }
        }
        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }

}
