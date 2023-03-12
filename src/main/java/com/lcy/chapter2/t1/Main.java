package com.lcy.chapter2.t1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

// 拓扑排序,计数
public class Main {

    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            String line = sc.nextLine();
            int N = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            String[] data = line.substring(line.indexOf(" ") + 1).split(",");
            int idx = 0;
            HashMap<String, Integer> hm = new HashMap<>();
            HashMap<Integer, HashSet<Integer>> edge = new HashMap<>();
            int[] inDeg = new int[N];
            for (String datum : data) {
                String[] split = datum.split("\\s+");
                if (!hm.containsKey(split[0])) {
                    hm.put(split[0], idx++);
                }
                if (!hm.containsKey(split[1])) {
                    hm.put(split[1], idx++);
                }
                HashSet<Integer> set = edge.getOrDefault(hm.get(split[0]), new HashSet<>());
                set.add(hm.get(split[1]));
                edge.put(hm.get(split[0]), set);
                inDeg[hm.get(split[1])]++;
            }
            ans = 0;
            boolean[] vis = new boolean[N];
            for (int i = 0; i < N; i++) {
                if (inDeg[i] == 0) {
                    vis[i] = true;
                    int[] copyInDeg = new int[inDeg.length];
                    for (int j = 0; j < copyInDeg.length; j++) {
                        copyInDeg[j] = inDeg[j];
                    }
                    dfs(i, copyInDeg, vis, edge, 1);
                    vis[i] = false;
                }
            }
            System.out.println(ans);
        }
    }

    private static void dfs(int i, int[] inDeg, boolean[] vis, HashMap<Integer, HashSet<Integer>> edge, int cnt) {
        if (cnt == vis.length) {
            ans++;
            return;
        }
        for (Integer y : edge.getOrDefault(i, new HashSet<>())) {
            inDeg[y]--;
        }
        for (int j = 0; j < inDeg.length; j++) {
            if (inDeg[j] == 0 && !vis[j]) {
                vis[j] = true;
                dfs(j, inDeg, vis, edge, cnt + 1);
                vis[j] = false;
            }
        }
    }
}
