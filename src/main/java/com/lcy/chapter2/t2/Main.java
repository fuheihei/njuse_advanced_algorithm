package com.lcy.chapter2.t2;

import java.util.*;

/**
 * BFS 入门问题
 */
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
            // 记录 id 和 nodeName的对应关系，例如 0 对应 a，1 对应 b 等等
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

            // BFS
            LinkedList<Integer> q = new LinkedList<>();
            q.addLast(node2Id.get(start));
            boolean[] vis = new boolean[N];
            List<Integer> ans = new ArrayList<>();
            vis[q.getFirst()] = true;
            while (!q.isEmpty()) {
                int sz = q.size();
                while (sz-- > 0) {
                    Integer x = q.poll();
                    ans.add(x);
                    for (int i = 0; i < grid[x].length; i++) {
                        if (grid[x][i] != 0 && !vis[i]) {
                            vis[i] = true;
                            q.addLast(i);
                        }
                    }
                }
            }
            for (int i = 0; i < ans.size() - 1; i++) {
                System.out.print(id2Node.get(ans.get(i)) + " ");
            }
            System.out.println(id2Node.get(ans.get(ans.size() - 1)));
        }
    }
}
