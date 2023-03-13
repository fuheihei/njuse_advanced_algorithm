package com.lcy.chapter0.t4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.indexOf(" ") > 0) {
                String[] data = line.split(" ");
                int sum = 0;
                for (int i = 1; i < data.length; i++) {
                    sum += Integer.parseInt(data[i]);
                }
                System.out.println(sum);
            }
        }
    }
}
