package com.lcy.other.fast_read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Java 快速读
 * <p>
 * http://118.190.20.162/view.page?gpid=T104
 * <p>
 * 如果不用BufferedReader,而用Scanner会报错
 */
public class Main {
    static class Reader {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer("");

        String nextLine() throws IOException {// 读取下一行字符串
            return reader.readLine();
        }

        String next() throws IOException {// 读取下一个字符串
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {// 读取下一个int型数值
            return Integer.parseInt(next());
        }

        double nextDouble() throws IOException {// 读取下一个double型数值
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] args) throws Exception {
        Reader sc = new Reader();
        int N = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        HashMap<Integer, Integer> v1 = new HashMap<>();
        for (int i = 0; i < a; i++) {
            int j = sc.nextInt();
            v1.put(j, sc.nextInt());
        }
        long ans = 0;
        for (int i = 0; i < b; i++) {
            int j = sc.nextInt();
            ans += (long) v1.getOrDefault(j, 0) * sc.nextInt();
        }
        System.out.println(ans);
    }
}
