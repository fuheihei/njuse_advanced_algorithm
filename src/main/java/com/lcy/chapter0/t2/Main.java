package com.lcy.chapter0.t2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] nums = scanner.nextLine().split(" ");
            int sum = 0;
            for (String num : nums) {
                sum += Integer.parseInt(num);
            }
            System.out.println(sum);
        }
    }
}
