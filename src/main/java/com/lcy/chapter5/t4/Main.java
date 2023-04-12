package com.lcy.chapter5.t4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


/**
 * https://www.geeksforgeeks.org/job-sequencing-problem/
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] d = br.readLine().split(" ");
            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++) {
                jobs[i] = new Job();
                jobs[i].id = Integer.parseInt(d[i * 3]);
                jobs[i].deadline = Integer.parseInt(d[i * 3 + 1]);
                jobs[i].profit = Integer.parseInt(d[i * 3 + 2]);
            }
            int[] ans = JobScheduling(jobs, n);
            bw.write(ans[0] + " " + ans[1] + "\n");
        }
        bw.flush();
    }

    static int[] JobScheduling(Job[] tasks, int n) {
        // 按照 (利润，ddl)逆序排序
        Arrays.sort(tasks, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.profit == o2.profit) {
                    return o2.deadline - o1.deadline;
                }
                return o2.profit - o1.profit;
            }
        });
        int maxDDL = 0;
        for (int i = 0; i < n; i++) {
            maxDDL = Math.max(maxDDL, tasks[i].deadline);
        }
        int[] days = new int[maxDDL + 1];
        int profit = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = tasks[i].deadline; j >= 0; j--) {
                if (days[j] == 0) {
                    days[j] = 1;
                    profit += tasks[i].profit;
                    cnt++;
                    break;
                }
            }
        }
        return new int[]{cnt, profit};
    }

    static class Job {
        int id, profit, deadline;

        public Job() {
        }

        Job(int x, int y, int z) {
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }
}