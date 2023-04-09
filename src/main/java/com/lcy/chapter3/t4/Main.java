package com.lcy.chapter3.t4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 能否成环
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] d = br.readLine().split(" ");
            int ans = formCircle(d, N);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    static boolean ok = false;

    /**
     * If chain can be formed, then print 1, else print 0.
     */
    private static int formCircle(String[] s, int n) {
        LinkedList<String> path = new LinkedList<>();
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            ArrayList<Integer> list = map.getOrDefault(s[i].charAt(0), new ArrayList<>());
            list.add(i);
            map.put(s[i].charAt(0), list);
        }
        ok = false;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < s.length; i++) {
            path.addLast(s[i]);
            vis[i] = true;
            dfs(s, path, vis, map);
            if (ok) break;
            vis[i] = false;
            path.removeLast();
        }
        return ok ? 1 : 0;
    }

    private static void dfs(String[] s, LinkedList<String> path, boolean[] vis, HashMap<Character, ArrayList<Integer>> map) {
        if (ok) return;
        if (path.size() == s.length) {
            if (path.getFirst().charAt(0) == path.getLast().charAt(path.getLast().length() - 1)) {
                ok = true;
            }
            return;
        }
        String last = path.getLast();
        ArrayList<Integer> arrayList = map.getOrDefault(last.charAt(last.length() - 1), new ArrayList<>());
        if (arrayList.isEmpty()) {
            return;
        }
        for (Integer i : arrayList) {
            if (vis[i]) continue;
            path.addLast(s[i]);
            vis[i] = true;
            dfs(s, path, vis, map);
            if (ok) break;
            path.removeLast();
            vis[i] = false;
        }
    }
}
