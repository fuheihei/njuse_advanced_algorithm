package com.lcy.chapter1;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 打印最多客人数量和聚会最多客人的时间。
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
            findMaxGuests(entry, exit, N);
        }
    }

    private static void findMaxGuests(int[] entry, int[] exit, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[][] people = new int[n][2];
        int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            people[i][0] = entry[i];
            people[i][1] = exit[i];
            start = Math.min(people[i][0], start);
            end = Math.max(people[i][1], end);
        }
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int j = 0;
        int mx = 0, t = 0;
        for (int i = start; i <= end; i++) {
            while (j < n && people[j][0] <= i) {
                pq.offer(people[j][1]);
                j++;
            }
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            if (pq.size() > mx) {
                mx = pq.size();
                t = i;
            }
        }
        System.out.printf("%d %d\n", mx, t);
    }
}
