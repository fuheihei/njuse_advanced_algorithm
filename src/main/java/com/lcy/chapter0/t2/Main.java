package com.lcy.chapter0.t2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.indexOf(" ") > 0) {
                String[] data = line.split(" ");
                int a = Integer.parseInt(data[0]);
                int b = Integer.parseInt(data[1]);
                System.out.println(a + b);
            }
        }
    }
}
