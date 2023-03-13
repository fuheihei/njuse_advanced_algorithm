package com.lcy.chapter2.t3;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            String line = sc.nextLine();
            String[] data = line.split(" ");
            int N = Integer.parseInt(data[0]);
            String start = data[1];
            int[][] grid = new int[N][N];
            data = sc.nextLine().split(" ");
            // 记录 id 和 nodeName的对应关系，
            // 例如 0 对应 a，1 对应 b 等等
            HashMap<Integer, String> id2Node = new HashMap<>();
            HashMap<String, Integer> node2Id = new HashMap<>();
            for (int i = 0; i < N; i++) {
                id2Node.put(i, data[i]);
                node2Id.put(data[i], i);
            }
            int id = 0;
            for (int i = 0; i < N; i++) {
                data = sc.nextLine().split(" ");
                id2Node.put(id, data[0]);
                node2Id.put(data[0], id++);
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(data[j + 1]);
                }
            }
            // DFS,用stack模拟递归
            LinkedList<int[]> stack = new LinkedList<>();
            stack.addLast(new int[]{node2Id.get(start), 1});
            int mx = 1;
            while (!stack.isEmpty()) {
                int[] state = stack.removeLast();
                int x = state[0], depth = state[1];
                mx = Math.max(mx, depth);
                for (int i = x + 1; i < N; i++) {
                    if (grid[x][i] != 0) {
                        stack.addLast(new int[]{i, depth + 1});
                    }
                }
            }
            System.out.println(mx);
        }
    }
}
