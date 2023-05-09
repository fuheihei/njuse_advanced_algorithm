package com.lcy.chapter5.t8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < cases; i++) {
            String str1 = br.readLine();
            String[] str3 = str1.split(" ");
            int n = str3.length;
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < str3.length; j++) {
                arr.add(Integer.parseInt(str3[j]));
            }
            int[] LIS = getLIS(arr, n);
            int[] LDS = getLDS(arr, n);

            ArrayList<Integer> maxIndex = new ArrayList<Integer>();
            int[] lengthArr = getLengthAndMaxLength(LIS, LDS, n);
            int maxLength = lengthArr[lengthArr.length - 1];
            for (int j = 0; j < n; j++) {
                if (lengthArr[j] == maxLength) {
                    maxIndex.add(j);
                }
            }
            ArrayList<String> output = getOutPut(arr, lengthArr, n, maxIndex, LIS, LDS);
            output = getUniqueOutput(output);
            for (String string : output) {
                bw.write(string + "\n");
            }
        }
        bw.flush();
    }


    private static ArrayList<String> getUniqueOutput(List<String> output) {
        Set<String> s = new HashSet<>();
        s.addAll(output);
        ArrayList<int[]> al = new ArrayList<int[]>();
        ArrayList<String> unique = new ArrayList<>();
        for (String ss : s) {
            String[] d = ss.split(" ");
            int[] a = new int[d.length];
            for (int i = 0; i < d.length; i++) {
                a[i] = Integer.parseInt(d[i]);
            }
            al.add(a);
        }
        Collections.sort(al, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 0; i < o1.length; i++) {
                    if (o1[i] != o2[i]) {
                        return Integer.compare(o1[i], o2[i]);
                    }
                }
                return 0;
            }
        });
        for (int[] aa : al) {
            unique.add(String.join(" ",
                    Arrays.stream(aa).mapToObj(o -> Integer.toString(o)).toArray(String[]::new)));
        }
        return unique;
    }


    private static ArrayList<String> getOutPut(ArrayList<Integer> arr, int[] lengthArr, int n,
                                               ArrayList<Integer> maxIndex, int[] LIS, int[] LDS) {

        ArrayList<String> output = new ArrayList<String>();
        for (Integer i : maxIndex) {
            int front = LIS[i] - 1;
            ArrayList<String> sFront = new ArrayList<String>();
            sFront.add(arr.get(i) + "");
            while (front > 0) {
                ArrayList<Integer> index = new ArrayList<Integer>();
                for (int j = 0; j < i; j++) {
                    if (LIS[j] == front) {
                        index.add(j);
                    }
                }
                if (!index.isEmpty()) {
                    sFront = addFirst(sFront, arr, index);
                }
                front--;
            }
            int after = LDS[i] - 1;
            while (after > 0) {
                ArrayList<Integer> index = new ArrayList<Integer>();
                for (int j = i + 1; j < n; j++) {
                    if (LDS[j] == after) {
                        index.add(j);
                    }
                }
                if (!index.isEmpty()) {
                    sFront = addAfter(sFront, arr, index);
                }
                after--;
            }
            output.addAll(sFront);
        }
        return output;
    }

    private static ArrayList<String> addAfter(ArrayList<String> str,
                                              ArrayList<Integer> arr, ArrayList<Integer> pos) {
        ArrayList<String> s = new ArrayList<String>();
        for (Integer index : pos) {
            if (!str.isEmpty()) {
                for (String string : str) {
                    int last_num = Integer.parseInt(string.split(" ")[string.split(" ").length - 1]);
                    if (arr.get(index) < last_num && index > arr.lastIndexOf(last_num)) {
                        string = string + " " + arr.get(index);
                        s.add(string);
                    }
                }
            } else {
                s.add(arr.get(index) + "");
            }
        }
        return s;
    }


    private static ArrayList<String> addFirst(ArrayList<String> str, ArrayList<Integer> arr, ArrayList<Integer> pos) {

        ArrayList<String> s = new ArrayList<String>();
        for (Integer index : pos) {
            if (!str.isEmpty()) {
                for (String string : str) {
                    int first_num = Integer.parseInt(string.split(" ")[0]);
                    if (arr.get(index) < first_num && index < arr.indexOf(first_num)) {
                        string = arr.get(index) + " " + string;
                        s.add(string);
                    }
                }
            } else {
                s.add(arr.get(index) + "");
            }
        }
        return s;
    }


    private static int[] getLengthAndMaxLength(int[] LIS, int[] LDS, int n) {
        int maxLength = 0;
        int[] lengthArr = new int[n + 10];
        for (int j = 0; j < n; j++) {
            int length = LIS[j] + LDS[j] - 1;
            lengthArr[j] = length;
            if (length > maxLength) {
                maxLength = length;
            }
        }
        lengthArr[lengthArr.length - 1] = maxLength;
        return lengthArr;
    }

    //获取以0为起点，以i为终点的最长递增子序列
    public static int[] getLIS(ArrayList<Integer> arr, int n) {
        int[] LIS = new int[n + 10];
        Arrays.fill(LIS, 1);
        for (int i = 1; i < arr.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j) < arr.get(i)) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }
        return LIS;
    }

    //获取以i为起点，以n为终点的最长递减子序列
    public static int[] getLDS(ArrayList<Integer> arr, int n) {
        int[] LDS = new int[n + 10];
        Arrays.fill(LDS, 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(i)) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }
        return LDS;
    }
}