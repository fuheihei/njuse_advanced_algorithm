package com.lcy.chapter3.t3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 单调栈
 * 力扣有原题
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int n = Integer.parseInt(d[0]), m = Integer.parseInt(d[1]);
            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                d = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    grid[i][j] = d[j].equals("1") ? 1 : 0;
                }
            }
            int ans = solve(n, m, grid);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int solve(int n, int m, int[][] grid) {
        int[] height = new int[m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            LinkedList<Integer> stack = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                while (!stack.isEmpty() && height[stack.getLast()] < height[j]) {
                    stack.removeLast();
                }
                stack.addLast(j);
                ans = Math.max(ans, height[j] * stack.size());
            }
        }
        return ans;
    }
}
