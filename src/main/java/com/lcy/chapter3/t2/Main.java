package com.lcy.chapter3.t2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] d = br.readLine().split(" ");
            int[] a = new int[d.length];
            for (int i = 0; i < d.length; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            d = br.readLine().split(" ");
            int l = Integer.parseInt(d[0]), r = Integer.parseInt(d[1]);
            int k = Integer.parseInt(br.readLine());
            Arrays.sort(a, l - 1, r);
            bw.write(a[l + k - 2] + "\n");
        }
        bw.flush();
    }
}
