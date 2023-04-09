package com.lcy.chapter2.t6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


/**
 * 排序
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            String[] d = sc.nextLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int m = Integer.parseInt(d[1]);
            int[] a1 = new int[n];
            int[] a2 = new int[m];
            d = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                a1[i] = Integer.parseInt(d[i]);
            }
            d = sc.nextLine().split(" ");
            for (int i = 0; i < m; i++) {
                a2[i] = Integer.parseInt(d[i]);
            }
            solve(n, m, a1, a2);
        }
    }

    private static void solve(int n, int m, int[] a1, int[] a2) {
        // 记录a2中元素的索引
        HashMap<Integer, Integer> elementIndex = new HashMap<>();
        for (int i = 0; i < a2.length; i++) {
            elementIndex.put(a2[i], i);
        }
        Integer[] a3 = Arrays.stream(a1).boxed().toArray(Integer[]::new);
        Arrays.sort(a3, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int i = elementIndex.getOrDefault(o1, m);
                int j = elementIndex.getOrDefault(o2, m);
                if (i == j) {
                    return Integer.compare(o1, o2);
                }
                return i - j;
            }
        });
        System.out.println(String.join(" ", Arrays.stream(a3).map(Object::toString).toArray(String[]::new)));
    }

}
