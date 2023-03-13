package com.lcy.chapter2.t4;

import java.util.*;

/**
 * 会不会是数据较弱？
 * 用回溯法是可以通过的
 */
public class Main {


    static int mn = 0;
    static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            int N = Integer.parseInt(sc.nextLine());
            String line = sc.nextLine();
            // 人员编号，任务编号从1开始
            int[][] cost = new int[N + 1][N + 1];
            String[] data = line.split(",");
            for (String datum : data) {
                String[] split = datum.split("\\s+");
                int i = Integer.parseInt(split[0]);
                int j = Integer.parseInt(split[1]);
                int k = Integer.parseInt(split[2]);
                cost[i][j] = k;
            }
            int[] jobs = new int[N + 1];
            mn = Integer.MAX_VALUE;
            boolean[] vis = new boolean[N + 1];
            solve(N, cost, 1, jobs, vis);
            StringBuilder s = new StringBuilder();
            Collections.sort(ans, new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    for (int i = 0; i < o1.size(); i++) {
                        if (!o1.get(i).equals(o2.get(i))) {
                            return o2.get(i) - o1.get(i);
                        }
                    }
                    return 0;
                }
            });
            for (int i = 0; i < ans.size(); i++) {
                if (s.length() != 0) {
                    s.append(",");
                }
                for (int j = 0; j < ans.get(i).size() - 1; j++) {
                    s.append(ans.get(i).get(j) + " ");
                }
                s.append(ans.get(i).get(ans.get(i).size() - 1));
            }
            System.out.println(s);
        }
    }

    private static void solve(int n, int[][] cost, int i, int[] jobs, boolean[] vis) {
        if (i == n + 1) {
            int sum = 0;
            for (int k = 1; k < jobs.length; k++) {
                sum += cost[k][jobs[k]];
            }
            if (mn > sum) {
                mn = sum;
                ans.clear();
                List<Integer> tmp = new ArrayList<>();
                for (int k = 1; k < jobs.length; k++) {
                    tmp.add(jobs[k]);
                }
                ans.add(tmp);
            } else if (mn == sum) {
                List<Integer> tmp = new ArrayList<>();
                for (int k = 1; k < jobs.length; k++) {
                    tmp.add(jobs[k]);
                }
                ans.add(tmp);
            }
            return;
        }
        // i 表示第 i 号工人
        // j 表示第 j 号任务
        for (int j = 1; j <= n; j++) {
            if (!vis[j]) {
                jobs[i] = j;
                vis[j] = true;
                solve(n, cost, i + 1, jobs, vis);
                vis[j] = false;
            }
        }
    }
}
