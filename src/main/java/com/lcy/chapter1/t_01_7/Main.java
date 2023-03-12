package com.lcy.chapter1.t_01_7;

import java.util.Arrays;
import java.util.Scanner;

// 二叉树打印
//TODO:
public class Main {
    /*
    Description
    Given a Complete Binar
     */

    //实现完全二叉树的层级遍历
    public static void levelOrder(int[] nums) {
        //对于完全二叉树，只有每层节点填满后才会填下一层节点
        //即除去最后一层外，每层节点个数均为2^h，其中根节点h=0，余下的节点全部填进最后一层
        int n = nums.length, nodeCnt = 1, index = 0;
        while (n - nodeCnt >= 0) {
            n -= nodeCnt;
            int[] nodes = new int[nodeCnt];
            for (int i = 0; i < nodeCnt; i++) {
                nodes[i] = nums[index++];
            }
            print(nodes);
            nodeCnt <<= 1;
        }
        if (n > 0) {
            int[] nodes = new int[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = nums[index++];
            }
            print(nodes);
        }
    }

    //对每层节点进行排序去重输出
    public static void print(int[] nodes) {
        Arrays.sort(nodes);
        int i = 0;
        String output = "";
        while (i < nodes.length) {
            output += nodes[i] + " ";
            int tem = nodes[i];
            while (i < nodes.length && nodes[i] == tem) i++; //去重
        }
        output = output.substring(0, output.length() - 1);
        System.out.println(output);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int T = scanner.nextInt();
            for (int i = 0; i < T; i++) {
                int n = scanner.nextInt();
                int[] nums = new int[n];
                for (int j = 0; j < n; j++) {
                    nums[j] = scanner.nextInt();
                }
                levelOrder(nums);
            }
        }
    }
}
