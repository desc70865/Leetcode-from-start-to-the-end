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
    public double minAreaFreeRect(int[][] points) {
        Set<Integer> set = new HashSet<>();
        for (int[] p: points) {
            set.add((p[0] << 10) + p[1]);
        }
        double res = Double.POSITIVE_INFINITY;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int[] X = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] Y = points[j];
                int a = Y[0] - X[0];
                int b = Y[1] - X[1];
                for (int k = j + 1; k < n; k++) {
                    int[] Z = points[k];
                    int c = Z[0] - X[0];
                    int d = Z[1] - X[1];
                    // vertical
                    if (a * c + b * d != 0) {
                        continue;
                    }
                    if (set.contains((X[0] + a + c << 10) + X[1] + b + d)) {
                        // Outer product
                        res = Math.min(res, Math.abs(a * d - c * b));
                    }
                }
            }
        }
        return Double.isInfinite(res) ? 0 : res;
    }
}