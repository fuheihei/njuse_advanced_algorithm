package com.lcy.chapter3.t7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * TODO:没来得及细看
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s = br.readLine();
            String sub = findSubStr(s);
            bw.write(sub + "\n");
        }
        bw.flush();
    }

    public static String findSubStr(String str) {
        String res = "";
        boolean[] hasLetter = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            hasLetter[str.charAt(i) - 'A'] = true;
        }

        for (int differ = 1; differ < 26; differ++) {
            //差值differ从1递增到25（差值低优先）
            for (int first = 25; first >= 0; first--) {
                //ASCII码逆序找当前字符串首字母（ASCII码高优先）
                if (hasLetter[first]) {
                    String temp = "" + (char)('A' + first);
                    for (int next = first - differ; next >= 0; next -= differ) {
                        //找距离differ的下一字符
                        if (hasLetter[next]) {
                            temp += (char)('A' + next);
                        } else {
                            break;
                        }
                    }
                    if (temp.length() > res.length()) {
                        res = temp;
                    }
                }
            }
        }
        return res;
    }

}
