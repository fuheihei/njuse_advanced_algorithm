package com.lcy.chapter3.t5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 是否是17的倍数
 */
public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int x = Integer.parseInt(br.readLine());
            formFavorNumber(x);
            if (!ans.isEmpty())
                bw.write(ans + "\n");
            else bw.write("Not Possible\n");
        }
        bw.flush();
    }

    static String ans = "";

    private static void formFavorNumber(int x) {
        int[] cnt = new int[10];
        int n = String.valueOf(x).length();
        while (x > 0) {
            cnt[x % 10]++;
            x /= 10;
        }
        ans = "";
        StringBuilder s = new StringBuilder("");
        for (int i = 9; i >=0;i --) {
            if (cnt[i] > 0) {
                s.append(i);
                cnt[i]--;
                dfs(s, cnt, n);
                if (!ans.isEmpty()) return;
                cnt[i]++;
                s.deleteCharAt(s.length() - 1);
            }
        }
    }

    private static void dfs(StringBuilder s, int[] cnt, int n) {
        if (!ans.isEmpty()) return;
        if (s.length() == n) {
            if (Integer.parseInt(s.toString()) % 17 == 0) {
                ans = s.toString();
            }
            return;
        }
        for (int i = 9; i >=0;i --) {
            if (cnt[i] > 0) {
                s.append(i);
                cnt[i]--;
                dfs(s, cnt, n);
                if (!ans.isEmpty()) return;
                cnt[i]++;
                s.deleteCharAt(s.length() - 1);
            }
        }
    }
}
