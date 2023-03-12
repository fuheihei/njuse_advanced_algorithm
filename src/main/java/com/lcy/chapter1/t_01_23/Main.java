package com.lcy.chapter1.t_01_23;

/*
棋盘覆盖问题：给定一个大小为2^n 2^n个小方格的棋盘，其中有一个位置已经被填充，
现在要用一个L型（2*2个小方格组成的大方格中去掉其中一个小方格）形状去覆盖剩下的小方格。
求出覆盖方案，即哪些坐标下的小方格使用同一个L型格子覆盖。
注意：坐标从0开始。左上方的第一个格子坐标为(0,0)，第一行第二个坐标为(0,1)，
第二行第一个为(1,0)，以此类推。

Input

输入第一行为测试用例个数，后面每一个用例有两行，第一行为n值和特殊的格子的坐标（用空格隔开），
第二行为需要查找其属于同一个L型格子的格子坐标。


Output

输出每一行为一个用例的解，先按照行值从小到大、再按照列值从小到大的顺序输出每一个用例的两个坐标；
用逗号隔开。
 */

import java.util.*;

/**
 * 分治算法，网上"棋盘覆盖问题"资料较多，就不再赘述
 *
 * 可以不申请(2^n)*(2^n)的内存空间进行求解
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            // 特殊方格的位置
            int dx = sc.nextInt();
            int dy = sc.nextInt();
            // 需要查找的方格的位置
            int x = sc.nextInt();
            int y = sc.nextInt();
            solve(N, 0, 0, dx, dy, x, y);
        }
    }


    /**
     * @param n
     * @param tx 左上角方格的下标(tx,ty)
     * @param ty
     */
    private static void solve(int n, int tx, int ty, int dx, int dy, int x, int y) {
//        System.out.printf("%d %d %d %d %d %d %d\n", n, tx, ty, dx, dy, x, y);
        if (n == 1) {
            int[][] board = new int[][]{{0, 0}, {0, 0}};
            board[dx][dy] = 1;
            board[x][y] = 1;
            List<int[]> ans = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (board[i][j] == 0) {
                        ans.add(new int[]{tx + i, ty + j});
                    }
                }
            }
            Collections.sort(ans, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        return Integer.compare(o1[1], o2[1]);
                    }
                    return Integer.compare(o1[0], o2[0]);
                }
            });
            System.out.printf("%d %d,%d %d\n", ans.get(0)[0], ans.get(0)[1],
                    ans.get(1)[0], ans.get(1)[1]);
            return;
        }
        int size = 1 << n, half = size >> 1;
        // 要查找的点在左上区域
        if (x < half) {
            if (y < half) {
                if (dx < half && dy < half) {
                    solve(n - 1, tx, ty, dx, dy, x, y);
                    return;
                }
                if (x == half - 1 && y == half - 1) {
                    if (dx < half) dx = 0;
                    else dx = 1;
                    if (dy < half) dy = 0;
                    else dy = 1;
                    solve(1, tx + half - 1, ty + half - 1, dx, dy, 0, 0);
                    return;
                }
                solve(n - 1, tx, ty, half - 1, half - 1, x, y);
            } else {
                // 查找的点在右上区域
                if (dx < half && dy >= half) {
                    solve(n - 1, tx, ty + half, dx, dy - half, x, y - half);
                    return;
                }
                if (x == half - 1 && y - half == 0) {
                    if (dx < half) dx = 0;
                    else dx = 1;
                    if (dy < half) dy = 0;
                    else dy = 1;
                    solve(1, tx + half - 1, ty + half - 1, dx, dy, 0, 1);
                    return;
                }
                solve(n - 1, tx, ty + half, half - 1, 0, x, y - half);
            }
        } else {
            //点在左下区域
            if (y < half) {
                if (dx >= half && dy < half) {
                    solve(n - 1, tx + half, ty, dx - half, dy, x - half, y);
                    return;
                }
                if (x - half == 0 && y == half - 1) {
                    if (dx < half) dx = 0;
                    else dx = 1;
                    if (dy < half) dy = 0;
                    else dy = 1;
                    solve(1, tx + half - 1, ty + half - 1, dx, dy, 1, 0);
                    return;
                }
                solve(n - 1, tx + half, ty, 0, half - 1, x - half, y);
            } else {
                //点在右下区域
                if (dx >= half && dy >= half) {
                    solve(n - 1, tx + half, ty + half, dx - half, dy - half, x - half, y - half);
                    return;
                }
                if (x - half == 0 && y - half == 0) {
                    if (dx < half) dx = 0;
                    else dx = 1;
                    if (dy < half) dy = 0;
                    else dy = 1;
                    solve(1, tx + half - 1, ty + half - 1, dx, dy, 1, 1);
                    return;
                }
                solve(n - 1, tx + half, ty + half, 0, 0, x - half, y - half);
            }
        }
    }
}
