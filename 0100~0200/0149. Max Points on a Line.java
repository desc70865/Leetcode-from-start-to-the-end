/* 
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Solution {
    List<Set<Integer>> list;
    public int maxPoints(int[][] points) {
        int LEN = points.length;
        if (LEN < 3) {
            return LEN;
        }
        list = new ArrayList<>();
        for (int i = 0; i < LEN; i++) {
            list.add(new HashSet<Integer>());
        }
        int max = 0;
        for (int i = 0; i < LEN; i++) {
            int a = points[i][0], b = points[i][1];
            for (int j = i+1; j < LEN-1; j++) {
                if (check(i, j)) {
                    continue;
                }
                int c = points[j][0], d = points[j][1];
                max = Math.max(count(a, b, c, d, j+1, points), max);
            }
        }
        return max;
    }
    
    private void count(int x1, int y1, int x2, int y2, int start, int[][] points) {
        int res = 2;
        for (int i = start; i < points.length; i++) {
            int x3 = points[i][0], y3 = points[i][1];
            if ((x2 - x1) * (y3 - y2) == (x3 - x2) * (y2 - y1)) {
                res++;
            }
        }
        return res;
    }
    
    private boolean check(int i, int j) {
        for (Integer l : list.get(i)) {
            if (list.get(j).contains(l)) {
                return true;
            }
        }
        return false;
    }
}