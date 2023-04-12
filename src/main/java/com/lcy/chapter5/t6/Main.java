package com.lcy.chapter5.t6;

// TODO:

/**
 * 原题
 * https://www.geeksforgeeks.org/water-connection-problem/
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int n = Integer.parseInt(d[0]);
            int p = Integer.parseInt(d[1]);
            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            ArrayList<Integer> c = new ArrayList<>();
            for (int i = 0; i < p; i++) {
                d = br.readLine().split(" ");
                a.add(Integer.parseInt(d[0]));
                b.add(Integer.parseInt(d[1]));
                c.add(Integer.parseInt(d[2]));
            }
            ArrayList<ArrayList<Integer>> ans = solve(n, p, a, b, c);
            bw.write(ans.size() + "\n");
            for (int i = 0; i < ans.size(); i++) {
                bw.write(ans.get(i).get(0) + " ");
                bw.write(ans.get(i).get(1) + " ");
                bw.write(ans.get(i).get(2) + "\n");
            }
        }
        bw.flush();
    }

    static ArrayList<ArrayList<Integer>> solve(int n, int p, ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> d) {
        // code here
        int[] parent = new int[n + 1];
        int[] child = new int[n + 1];
        int[] diameter = new int[n + 1];
        for (int i = 0; i < p; i++) {
            parent[b.get(i)] = a.get(i);
            child[a.get(i)] = b.get(i);
            diameter[a.get(i)] = d.get(i);
        }
        List<Integer> startPoint = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (parent[i] == 0 && child[i] == 0) {
                continue;
            }
            if (parent[i] == 0) {
                startPoint.add(i);
            }
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (Integer i : startPoint) {
            int start = i;
            int min = Integer.MAX_VALUE;
            while (child[child[start]] != 0) {
                min = Math.min(min, diameter[start]);
                start = child[start];
            }
            min = Math.min(min, diameter[start]);
            ArrayList<Integer> partialRes = new ArrayList<>();
            partialRes.add(i);
            partialRes.add(child[start]);
            partialRes.add(min);
            res.add(partialRes);

        }
        return res;
    }
}
