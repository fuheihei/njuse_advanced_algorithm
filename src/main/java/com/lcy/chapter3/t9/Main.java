package com.lcy.chapter3.t9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * kmp 匹配
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(",");
            String s = d[0], pattern = d[1];
            List<Integer> res = match(s, pattern);
            bw.write(String.join(" ", res.stream().map(x -> x.toString()).toArray(String[]::new)) + "\n");
        }
        bw.flush();
    }

    private static List<Integer> match(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] next = getNext(p);
        int sLen = s.length(), pLen = p.length();

        int i = 0, j = 0;
        while (i < sLen) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }

            if (j == pLen) {
                res.add(i - j);
                j = next[j];
            }
        }

        return res;
    }

    private static int[] getNext(String p) {
        int len = p.length();
        if (len == 0) {
            return new int[0];
        }
        int[] res = new int[len + 1];

        res[0] = -1;
        int k = -1, j = 0;
        while (j < len) {
            if (k == -1 || p.charAt(j) == p.charAt(k)) {
                res[++j] = ++k;
            } else {
                k = res[k];
            }
        }
        return res;
    }
}
