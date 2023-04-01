package com.lcy.chapter2.t8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


/**
 * 有9个因数的数字个数
 */
public class Main {

    static long MAX_N = (long) 1e12;
    static int MAX_SQRT2_N = (int) 1e6;
    static List<Long> primes = new ArrayList<>();
    static List<Long> numbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        primes.clear();
        numbers.clear();
        init_prime();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            long x = Long.parseLong(br.readLine());
            //需要 x = a^2 * b^2
            //或者 x = a^8
            int i = bisect_right(x);
            bw.write(i + "\n");
        }
        bw.flush();
    }
    // 2 3
    // 36 2^8 256

    private static int bisect_right(long t) {
        int left = -1, right = numbers.size();
        while (left + 1 < right) {
            int mid = left + right >> 1;
            if (numbers.get(mid) <= t) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private static void init_prime() {
        boolean[] isPrime = new boolean[MAX_SQRT2_N + 1];
        Arrays.fill(isPrime, true);
        for (long i = 2; i <= MAX_SQRT2_N; i++) {
            if (isPrime[(int) i]) {
                primes.add(i);
                for (long j = i * i; j <= MAX_SQRT2_N; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }

        for (int i = 0; i < primes.size(); i++) {
            for (int j = i + 1; j < primes.size(); j++) {
                double ab = primes.get(i) * primes.get(j);
                ab *= ab;
                if (ab <= MAX_N) {
                    numbers.add((long) ab);
                } else break;
            }
        }
        for (long a : primes) {
            double x = Math.pow(a, 8);
            if (x <= MAX_N) {
                numbers.add((long) x);
            } else break;
        }
        Collections.sort(numbers);
    }
}
