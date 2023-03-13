package com.lcy.chapter0.t3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.indexOf(" ") > 0) {
                String[] data = line.split(" ");
                int a = Integer.parseInt(data[0]);
                int b = Integer.parseInt(data[1]);
                if (a == 0 && b == 0) continue;
                System.out.println(a + b);
            }
        }
    }
}
