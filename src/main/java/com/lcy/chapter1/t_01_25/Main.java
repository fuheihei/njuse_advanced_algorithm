package com.lcy.chapter1.t_01_25;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 按照数值个数排序
 * <p>
 * hashmap + 排序
 *
 * 对给定数组中的元素按照元素出现的次数排序，出现次数多的排在前面，如果出现次数相同，则按照数值大小排序。
 * 例如，给定数组为 2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            Integer[] a = new Integer[N];
            HashMap<Integer, Integer> counter = new HashMap<>();
            for (int i = 0; i < N; i++) {
                a[i] = sc.nextInt();
                counter.put(a[i], counter.getOrDefault(a[i], 0) + 1);
            }
            Arrays.sort(a, (x, y) -> counter.get(x) == counter.get(y) ? x - y : counter.get(y) - counter.get(x));
            for (int i = 0; i < N - 1; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println(a[N - 1]);
        }
    }
}
