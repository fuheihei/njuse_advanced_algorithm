package com.lcy.chapter2.t8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 方法二，数个数的方式
 * 速度比方法一更好点
 */
public class Main2 {

    private boolean[] isPrime;
    private List<Integer> primeNumbers;

    public Main2(long max) {
        isPrime = new boolean[(int) Math.sqrt(max)];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < Math.sqrt(isPrime.length); i++) {
            if (isPrime[i - 1]) {
                for (int j = i; i * j <= isPrime.length; j++) {
                    int num = i * j - 1;
                    if (isPrime[num]) {
                        isPrime[num] = false;
                    }
                }
            }
        }
        primeNumbers = new ArrayList<>();
        for (int i = 1; i < isPrime.length; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i + 1);
            }
        }
        return;
    }

    public int getNumOfFactor(long num) {
        int count = 0;
        for (int i = 0; i < primeNumbers.size() - 1; i++) {
            int a = primeNumbers.get(i);
            if (Math.pow(a, 8) <= num) {
                count++;
            }
            if (Math.pow(a, 2) * Math.pow(primeNumbers.get(i + 1), 2) > num) {
                break;
            }
            for (int j = i + 1; j < primeNumbers.size(); j++) {
                int b = primeNumbers.get(j);
                if (Math.pow(a, 2) * Math.pow(b, 2) <= num) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        long[] tests = new long[T];
        long max = 0;
        for (int t = 0; t < T; t++) {
            tests[t] = scanner.nextLong();
            max = Math.max(max, tests[t]);
        }

        Main2 problem = new Main2(max);
        for (int i = 0; i < T; i++) {
            System.out.println(problem.getNumOfFactor(tests[i]));
        }
    }
}