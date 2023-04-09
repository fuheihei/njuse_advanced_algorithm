package com.lcy.chapter3.t8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// 输入数据存在重复的点吗
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            HashMap<Integer, HashMap<Integer, Integer>> xMap = new HashMap<>();
            HashMap<Integer, HashMap<Integer, Integer>> yMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                String[] d = br.readLine().split(" ");
                int x = Integer.parseInt(d[0]);
                int y = Integer.parseInt(d[1]);
                HashMap<Integer, Integer> map = xMap.getOrDefault(x, new HashMap<>());
                map.put(y, map.getOrDefault(y, 0) + 1);
                xMap.put(x, map);
                map = yMap.getOrDefault(y, new HashMap<>());
                map.put(x, map.getOrDefault(x, 0) + 1);
                yMap.put(y, map);
            }
            int ans = 0;
            for (Map.Entry<Integer, HashMap<Integer, Integer>> e : xMap.entrySet()) {
                int cnt = 0;
                for (Integer v : e.getValue().values()) {
                    cnt += v;
                }
                for (Map.Entry<Integer, Integer> entry : e.getValue().entrySet()) {
                    ans += entry.getValue() * (cnt - entry.getValue());
                    cnt -= entry.getValue();
                }
            }
            for (Map.Entry<Integer, HashMap<Integer, Integer>> e : yMap.entrySet()) {
                int cnt = 0;
                for (Integer v : e.getValue().values()) {
                    cnt += v;
                }
                for (Map.Entry<Integer, Integer> entry : e.getValue().entrySet()) {
                    ans += entry.getValue() * (cnt - entry.getValue());
                    cnt -= entry.getValue();
                }
            }
            System.out.println(ans);
        }
    }
}
