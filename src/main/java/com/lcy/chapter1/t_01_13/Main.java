package com.lcy.chapter1.t_01_13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 四数之和
 *
 * https://leetcode.cn/problems/4sum/submissions/
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            solve(A, N, K);
        }
    }

    private static void solve(int[] A, int N, int K) {
        Arrays.sort(A);
        StringBuilder s = new StringBuilder();
        for (int first = 0; first < N; first++) {
            if (first > 0 && A[first] == A[first - 1]) {
                continue;
            }
            for (int second = first + 1; second < N; second++) {
                if (second > first + 1 && A[second] == A[second - 1]) {
                    continue;
                }
                for (int fourth = N - 1; fourth > second; fourth--) {
                    if (fourth < N - 1 && A[fourth] == A[fourth + 1]) {
                        continue;
                    }
                    int third = Arrays.binarySearch(A, second + 1, fourth,
                            K - A[first] - A[second] - A[fourth]);
                    if (third > 0) {
                        s.append(String.format("%d %d %d %d $", A[first], A[second], A[third], A[fourth]));
                    }
                }
            }
        }
        System.out.println(s);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }
}
