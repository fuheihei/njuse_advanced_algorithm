package com.lcy.chapter1.t24;

import java.util.*;

/**
 * 最近对问题：使用分治算法解决最近对问题。
 * <p>
 * TODO: 代码尚未理解
 */
public class Main {


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

        public float getX() {
            return Float.parseFloat(x);
        }

        public float getY() {
            return Float.parseFloat(y);
        }

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


    //获取两个点之间的欧氏距离
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    //通过分治法获取最近点对集合
    public static List<Point[]> getNearest(Point[] points, int left, int right) {
        //若只有一个点，则不存在最近点对的情况
        if (left == right) return new ArrayList<>();
        //若只有两个点，则它们就是最近点
        if (right == left + 1) {
            Point[] nearest = new Point[]{points[left], points[right]};
            List<Point[]> res = new ArrayList<>();
            res.add(nearest);
            return res;
        }
        //不止两个点的情况下，划分左右区间，分别取左右区间的最近点对集合
        int mid = (left + right) / 2;
        List<Point[]> leftNearest = getNearest(points, left, mid);
        List<Point[]> rightNearest = getNearest(points, mid + 1, right);
        double leftMinDistance = leftNearest.size() == 0 ? Double.MAX_VALUE : distance(leftNearest.get(0)[0], leftNearest.get(0)[1]);
        double rightMinDistance = rightNearest.size() == 0 ? Double.MAX_VALUE : distance(rightNearest.get(0)[0], rightNearest.get(0)[1]);
        //比较左右区间的最近点距离，更新当前区间的最近点距离以及最近点集合
        double minDistance = 0.0;
        List<Point[]> nearest = new ArrayList<>();
        if (leftMinDistance < rightMinDistance) {
            minDistance = leftMinDistance;
            nearest.addAll(leftNearest);
        } else if (leftMinDistance > rightMinDistance) {
            minDistance = rightMinDistance;
            nearest.addAll(rightNearest);
        } else {
            minDistance = leftMinDistance;
            nearest.addAll(leftNearest);
            nearest.addAll(rightNearest);
        }
        //处理最近点对一个点在左区间，另一个点在右区间的情况
        //在左区间检索到中间线距离<= minDistance的点，在右区间检索到中间线距离>= minDistance的点
        //记录检索到的点的索引
        List<Integer> leftIndex = new ArrayList<>();
        List<Integer> rightIndex = new ArrayList<>();
        for (int i = mid; i >= 0; i--) {
            if (points[mid].getX() - points[i].getX() <= minDistance) {
                leftIndex.add(i);
            } else {
                break;
            }
        }
        for (int i = mid + 1; i <= right; i++) {
            if (points[i].getX() - points[mid].getX() <= minDistance) {
                rightIndex.add(i);
            } else {
                break;
            }
        }
        for (int i = 0; i < leftIndex.size(); i++) {
            for (int j = 0; j < rightIndex.size(); j++) {
                Point leftPoint = points[leftIndex.get(i)];
                Point rightPoint = points[rightIndex.get(j)];
                //若两个点在y轴方向上的距离> minDistance，则跳过距离计算
                if (Math.abs(leftPoint.getY() - rightPoint.getY()) > minDistance) {
                    continue;
                }
                double tempDistance = distance(leftPoint, rightPoint);
                if (tempDistance < minDistance) {
                    //若距离小于当前最小距离，更新minDistance，并删除原有最近点对集合，将该点对插入
                    minDistance = tempDistance;
                    nearest = new ArrayList<>();
                    nearest.add(new Point[]{leftPoint, rightPoint});
                } else if (tempDistance == minDistance) {
                    //若距离等于当前最小距离，将点对插入最近点对集合
                    nearest.add(new Point[]{leftPoint, rightPoint});
                }
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            String[] points_str = scanner.nextLine().split(",");
            Point[] points = new Point[points_str.length];
            for (int j = 0; j < points.length; j++) {
                String[] point_str = points_str[j].split(" ");
                points[j] = new Point(point_str[0], point_str[1]);
            }
            Arrays.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    if (o1.getX() < o2.getX()) {
                        return -1;
                    } else if (o1.getX() > o2.getX()) {
                        return 1;
                    } else {
                        if (o1.getY() < o2.getY()) {
                            return -1;
                        } else if (o1.getY() > o2.getY()) {
                            return 1;
                        }
                    }
                    return 0;  //按x轴坐标进行排序，方便后续进行分治处理
                }
            });
            List<Point[]> nearest = getNearest(points, 0, points.length - 1);
            //将结果按第一个点x坐标进行排序
            nearest.sort(new Comparator<Point[]>() {
                @Override
                public int compare(Point[] o1, Point[] o2) {
                    if (o1[0].getX() < o2[0].getX()) {
                        return -1;
                    } else if (o1[0].getX() > o2[0].getX()) {
                        return 1;
                    }
                    return 0;
                }
            });
            for (int j = 0; j < nearest.size() - 1; j++) {
                System.out.print(nearest.get(j)[0] + "," + nearest.get(j)[1] + ",");
            }
            System.out.println(nearest.get(nearest.size() - 1)[0] + "," + nearest.get(nearest.size() - 1)[1]);
        }
    }
}
