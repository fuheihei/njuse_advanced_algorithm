package com.lcy.chapter5.t9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 经典二数之和
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int[] a = new int[d.length];
            for (int i = 0; i < a.length; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            int t = Integer.parseInt(br.readLine());
            int ans = twoNumber(a, t);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int twoNumber(int[] a, int t) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(t - a[i])) {
                ans += map.get(t - a[i]);
            }
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        return ans;
    }
}
