package com.lcy.chapter1.t_01_21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 模板问题： 求逆序对个数
 * 方法：树状数组 统计逆序个数
 * <p>
 * 参考题目:
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
            }
            System.out.println(countSmaller(a));
        }
    }

    private static int[] C;
    private static int[] A;

    public static int countSmaller(int[] nums) {
        //1. 离散化
        discretization(nums);
        //2.初始化，构造树状数组
        init(nums.length + 5);
        int pairs = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = getId(nums[i]);
            pairs += query(id - 1);
            update(id);
        }
        return pairs;
    }

    private static void init(int length) {
        C = new int[length];
        Arrays.fill(C, 0);
    }

    private static int lowBit(int x) {
        return x & (-x);
    }

    private static void update(int pos) {
        while (pos < C.length) {
            C[pos] += 1;
            pos += lowBit(pos);
        }
    }

    private static int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += C[pos];
            pos -= lowBit(pos);
        }

        return ret;
    }

    private static void discretization(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        A = new int[size];
        int index = 0;
        for (int num : set) {
            A[index++] = num;
        }
        Arrays.sort(A);
    }

    private static int getId(int x) {
        return Arrays.binarySearch(A, x) + 1;
    }
}
