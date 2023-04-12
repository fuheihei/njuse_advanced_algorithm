package com.lcy.chapter5.t10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

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
            // 窗口宽度
            int w = Integer.parseInt(br.readLine());
            LinkedList<Integer> stack = new LinkedList<>();
            int ans = 0;
            for (int i = 0; i < w; i++) {
                while (!stack.isEmpty() && a[stack.getLast()] < a[i]) {
                    stack.removeLast();
                }
                stack.addLast(i);
            }
            ans += a[stack.getFirst()];
            for (int i = w; i < a.length; i++) {
                while (!stack.isEmpty() && stack.getFirst() <= (i - w)) {
                    stack.removeFirst();
                }
                while (!stack.isEmpty() && a[stack.getLast()] < a[i]) {
                    stack.removeLast();
                }
                stack.addLast(i);
                ans += a[stack.getFirst()];
            }
            bw.write(ans + "\n");
        }
        bw.flush();
    }
}
