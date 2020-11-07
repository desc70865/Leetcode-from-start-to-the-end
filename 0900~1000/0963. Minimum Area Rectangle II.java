/* 
Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.

If there isn't any rectangle, return 0.

 

Example 1:



Input: [[1,2],[2,1],[1,0],[0,1]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
Example 2:



Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
Output: 1.00000
Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
Example 3:



Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
Output: 0
Explanation: There is no possible rectangle to form from these points.
Example 4:



Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
Output: 2.00000
Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.
 

Note:

1 <= points.length <= 50
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
Answers within 10^-5 of the actual value will be accepted as correct.
 */

class Solution {
    double min = Double.MAX_VALUE;

    public double minAreaFreeRect(int[][] points) {
        int len = points.length;
        for (int a = 0; a < len - 3; a++) {
            for (int b = a + 1; b < len - 2; b++) {
                for (int c = b + 1; c < len - 1; c++) {
                    for (int d = c + 1; d < len; d++) {
                        detectRectangle(points[a], points[b], points[c], points[d]);
                    }
                }
            }
        }
        return min == Double.MAX_VALUE ? 0 : min;
    }

    private void detectRectangle(int[] a, int[] b, int[] c, int[] d) {
        int[] p = new int[3];
        if ((p[0] = dis(a, b)) != dis(c, d)
         || (p[1] = dis(a, c)) != dis(b, d)
         || (p[2] = dis(a, d)) != dis(b, c)) return;
        Arrays.sort(p);
        min = Math.min(min, Math.sqrt(p[0]) * Math.sqrt(p[1]));
    }

    private int dis(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}



class Solution {
    public double minAreaFreeRect(int[][] points) {
        Set<Point> pointSet = new HashSet<>();
        for (int[] p: points) {
            pointSet.add(new Point(p[0], p[1]));
        }
        double res = Double.POSITIVE_INFINITY;
        int n = points.length;
        for (int i = 0; i < n; ++i) {
            int[] start = points[i];
            for (int j = i + 1; j < n; ++j) {
                int [] end0 = points[j];
                for (int k = j + 1; k < n; ++k) {
                    int[] end1 = points[k];
                    int dx0 = end0[0] - start[0];
                    int dy0 = end0[1] - start[1];
                    int dx1 = end1[0] - start[0];
                    int dy1 = end1[1] - start[1];
                    // vertical
                    if (dx0 * dx1 + dy0 * dy1 != 0) {
                        continue;
                    }
                    Point p = new Point(start[0] + dx0 + dx1, start[1] + dy0 + dy1);
                    if (pointSet.contains(p)) {
                        // Outer product
                        res = Math.min(res, Math.abs(dx0 * dy1 - dx1 * dy0));
                    }
                }
            }
        }
        return Double.isInfinite(res) ? 0 : res;
    }
}

class Point {
    private int x;
    private int y;

    public Point(int ix, int iy) {
        this.x = ix;
        this.y = iy;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}