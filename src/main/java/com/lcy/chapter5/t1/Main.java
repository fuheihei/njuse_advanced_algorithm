package com.lcy.chapter5.t1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Dijkstra
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] grid = new int[n][n];
            int cnt = 0;
            String[] d = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(d[cnt++]);
                }
            }
            int ans = minCost(grid);
            bw.write(ans + "\n");
        }
        bw.flush();
    }

    private static int minCost(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = grid[0][0];
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> dist[x[0]][x[1]] - dist[y[0]][y[1]]);
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int x = poll[0], y = poll[1];
            for (int[] d : dir) {
                int a = d[0] + x, b = d[1] + y;
                if (a < 0 || a >= n || b < 0 || b >= n) continue;
                if (dist[a][b] == -1) {
                    dist[a][b] = grid[a][b] + dist[x][y];
                    pq.offer(new int[]{a, b});
                } else if (grid[a][b] + dist[x][y] < dist[a][b]) {
                    dist[a][b] = grid[a][b] + dist[x][y];
                }
            }
        }
        return dist[n - 1][n - 1];
    }
}