/* 
Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

 

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
 

Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Explanation:
Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False

Explanation:
 */

class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        int len = points.size();
        int min = helper(points, len - 2, len - 1, 0);
        int max = min;
        int tmp = helper(points, len - 1, 0, 1);
        min = Math.min(tmp, min);
        max = Math.max(tmp, max);
        for (int i = 0; i < len - 2; i++) {
            int cur = helper(points, i, i + 1, i + 2);
            if (cur > max) max = cur;
            if (cur < min) min = cur;
            if (min * max < 0) return false;
        }
        return true;
    }

    private int helper(List<List<Integer>> points, int a, int b, int c) {
        return crossProduct(points.get(a), points.get(b), points.get(c));
    }

    private int crossProduct(List<Integer> a, List<Integer> b, List<Integer> c) {
        int x1 = b.get(0) - a.get(0);
        int y1 = b.get(1) - a.get(1);
        int x2 = c.get(0) - b.get(0);
        int y2 = c.get(1) - b.get(1);
        int crossPro = x1 * y2 - x2 * y1;
        if (crossPro == 0) return 0;
        return crossPro > 0 ? 1 : -1;
    }
}