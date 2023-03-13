package com.lcy.chapter1.t12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// TODO: 总是 runtime error
// 但是什么都不交可以通过
//  原题可以通过
// https://practice.geeksforgeeks.org/problems/maximum-intervals-overlap5708
/*
   客流量
   Description
   Consider a big party where a log register for guest’s entry and exit times is maintained.
   Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.
   Input
   The first line of input contains an integer T denoting the number of test cases.
   Then T test cases follow. Each test case contains an integer n denoting the size of the entry and exit array.
   Then the next two line contains the entry and exit array respectively.(1<=T<=10^5;1<=N<=10^5;1<=entry[i],exit[i]<=10^5)
   Output
   Print the maximum no of guests and the time at which there are maximum guests in the party.
*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] entry = new int[N];
            int[] exit = new int[N];
            for (int i = 0; i < N; i++) {
                entry[i] = sc.nextInt();
            }
            for (int i = 0; i < N; i++) {
                exit[i] = sc.nextInt();
            }
            getMaxNum(entry, exit);
        }
    }

    public static void getMaxNum(int[] entry, int[] exit) {
        //entry数组并不指定进入顺序，因此对数组进行排序，便于按时间顺序进行处理
//        int n = entry.length;
//        int mx = 0, t = 0;
//        int[][] persons = new int[n][2];
//        for (int i = 0; i < n; i++) {
//            persons[i][0] = entry[i];
//            persons[i][1] = exit[i];
//        }
//        Arrays.sort(persons, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) {
//                    return Integer.compare(o1[1], o2[1]);
//                }
//                return Integer.compare(o1[0], o2[0]);
//            }
//        });
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        for (int i = 0; i < n; i++) {
//            int cur = persons[i][0];
//            pq.offer(persons[i][1]);
//            while (!pq.isEmpty() && pq.peek() < cur) {
//                pq.poll();
//            }
//            if (mx < pq.size()) {
//                mx = pq.size();
//                t = cur;
//            }
//        }
        int[] ans = findMaxGuests(entry, exit, entry.length);

        System.out.printf("%d %d\n", ans[0], ans[1]);
    }


    public static int[] findMaxGuests(int[] entry, int exit[], int n) {
        Arrays.sort(entry);
        Arrays.sort(exit);
        int guests = 1, mx = 1, t = entry[0];
        int i = 1, j = 0;
        while (i < n && j < n) {
            if (entry[i] <= exit[j]) {
                guests++;
                if (guests > mx) {
                    mx = guests;
                    t = entry[i];
                }
                i++;
            } else {
                guests--;
                j++;
            }
        }
        return new int[]{mx, t};
    }
}
