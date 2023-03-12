package com.lcy.chapter1.t8;

import java.util.Scanner;

// 快速幂
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            System.out.println(myPow(a, b, c));
        }
    }

    // 注意数字精度，必须得是long，不能是int
    private static long myPow(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                res *= a;
                res %= mod;
            }
            a *= a;
            a %= mod;
            b >>= 1;
        }
        return res;
    }

}
