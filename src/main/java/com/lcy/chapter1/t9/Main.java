package com.lcy.chapter1.t9;

import java.util.Scanner;

// 点的凸包

/**
 * TODO:
 * 凸包问题
 * 参考文章：https://blog.csdn.net/u013279723/article/details/104198688
 * 代码来自 https://alphacat.blog.csdn.net/article/details/114452734
 */

import java.util.*;

public class Main {

    private static boolean UPPER_HULL = true;
    private static boolean LOWER_HULL = false;
    private static int NOT_FOUND_NUM = -1;

    static class Point implements Comparable<Point> {
        private final String x;
        private final String y;

        public Point() {
            this.x = "0";
            this.y = "0";
        }

        public Point(String x, String y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return Integer.parseInt(x);
        }

        public int getY() {
            return Integer.parseInt(y);
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.getX() < o.getX()) {
                return -1;
            } else if (this.getX() > o.getX()) {
                return 1;
            } else {
                if (this.getY() < o.getY()) {
                    return -1;
                } else if (this.getY() > o.getY()) {
                    return 1;
                }
            }
            return 0;
        }
    }

    public static List<Point> getConvexHull(Point[] points) {
        List<Point> res = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            indexes.add(i);
        }
        List<Integer> upper = hull(points, indexes, UPPER_HULL);
        List<Integer> lower = hull(points, indexes, LOWER_HULL);
        //合并结果
        //若所有点处于同一条线上，则上下包返回的均为所有点索引列表
        if (upper.size() == lower.size() && upper.containsAll(lower)) {
            return res;
        }
        List<Integer> resIndex = new ArrayList<>();
        resIndex.add(indexes.get(0));
        resIndex.add(indexes.get(indexes.size() - 1));
        resIndex.addAll(upper);
        resIndex.addAll(lower);
        Collections.sort(resIndex);
        for (int i = 0; i < resIndex.size(); i++) {
            int index = resIndex.get(i);
            Point point = points[index];
            res.add(point);
        }
        return res;
    }

    public static List<Integer> hull(Point[] points, List<Integer> indexes, boolean dir) {
        //上包记录最大值，下包记录最小值
        int pDis = 0;
        int pIndex = NOT_FOUND_NUM;
        int size = indexes.size();
        Point p1 = points[indexes.get(0)];
        Point p2 = points[indexes.get(size - 1)];

        //记录上包或下包的点索引
        List<Integer> upOrLowList = new ArrayList<>();
        //记录在p1p2线上的点
        List<Integer> onList = new ArrayList<>();

        for (int i = 1; i < size - 1; i++) {
            int index = indexes.get(i);
            int dis = getDistance(p1, p2, points[index]);
            if (dis == 0) {
                onList.add(index);
            }

            if (dir == UPPER_HULL && dis > 0) {
                upOrLowList.add(index);
                if (dis > pDis) {
                    pDis = dis;
                    pIndex = index;
                }
            }

            if (dir == LOWER_HULL && dis < 0) {
                upOrLowList.add(index);
                if (dis < pDis) {
                    pDis = dis;
                    pIndex = index;
                }
            }

        }

        // 未找到上包或下包点，返回线上的点集
        if (pIndex == NOT_FOUND_NUM) {
            int start = indexes.get(0);
            int end = indexes.get(size - 1);
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < onList.size(); i++) {
                int index = onList.get(i);
                if (index != start && index != end) {
                    res.add(index);
                }
            }
            return res;
        }

        //对上包或下包进行划分，分成p1->pMax, pMax->p2两部分
        List<Integer> divide1 = new ArrayList<>();
        List<Integer> divide2 = new ArrayList<>();

        divide1.add(indexes.get(0));
        divide1.addAll(upOrLowList);
        divide1.add(pIndex);

        divide2.add(pIndex);
        divide2.addAll(upOrLowList);
        divide2.add(indexes.get(size - 1));

        //合并结果
        List<Integer> res1 = hull(points, divide1, dir);
        List<Integer> res2 = hull(points, divide2, dir);
        res1.add(pIndex);
        res1.addAll(res2);
        return res1;
    }

    //计算行列式
    //  |x1 y1 1|                                             >0 p3在p1p2左侧
    //  |x2 y2 1| = x1y2 + x3y1 + x2y3 - x3y2 - x2y1 - x1y3   =0 p3在p1p2上
    //  |x3 y3 1|                                             <0 p3在p1p2右侧
    //同时该行列式绝对值的1/2等于三角形p1p2p3的面积
    public static int getDistance(Point p1, Point p2, Point p3) {
        return p1.getX() * p2.getY()
                + p3.getX() * p1.getY()
                + p2.getX() * p3.getY()
                - p3.getX() * p2.getY()
                - p2.getX() * p1.getY()
                - p1.getX() * p3.getY();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            Point[] points = new Point[n];
            for (int j = 0; j < n; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[j] = new Point(String.valueOf(x), String.valueOf(y));
            }

            if (n == 0) {
                System.out.println(NOT_FOUND_NUM);
                continue;
            }

            Arrays.sort(points);
            List<Point> convexHull = getConvexHull(points);
            print(convexHull);
        }
    }

    private static void print(List<Point> convexHull) {
        if (convexHull.size() <= 2) {
            System.out.println(NOT_FOUND_NUM);
            return;
        }
        for (int j = 0; j < convexHull.size() - 1; j++) {
            System.out.print(convexHull.get(j) + ", ");
        }
        System.out.println(convexHull.get(convexHull.size() - 1));
    }

}


