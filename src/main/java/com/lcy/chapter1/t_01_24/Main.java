package com.lcy.chapter1.t_01_24;

import java.util.Scanner;

/**
 * 最近对问题：使用分治算法解决最近对问题。
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            String[] data = sc.nextLine().split(",");
            float[][] point = new float[data.length][2];
            for (int i = 0; i < data.length; i++) {
                String[] datax = data[i].split("\\s+");
                point[i][0] = Float.parseFloat(datax[0]);
                point[i][1] = Float.parseFloat(datax[1]);
            }
            solve(point);
        }
    }

    private static void solve(float[][] point) {

    }
}
