/* 
Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).

Return true if all the rectangles together form an exact cover of a rectangular region.

 

Example 1:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
Output: true
Explanation: All 5 rectangles together form an exact cover of a rectangular region.
Example 2:


Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
Output: false
Explanation: Because there is a gap between the two rectangular regions.
Example 3:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
Output: false
Explanation: Because there is a gap in the top center.
Example 4:


Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
Output: false
Explanation: Because two of the rectangles overlap with each other.
 

Constraints:

1 <= rectangles.length <= 2 * 104
rectangles[i].length == 4
-105 <= xi, yi, ai, bi <= 105
 */

class Solution {
    static final int INF = 1_000_000_007;
    static final int WIDTH = (int) 1e6;
    Set<Long> set = new HashSet<>();

    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        int xL = INF;
        int xR = -INF;
        int yD = INF;
        int yU = -INF;
        for (int[] r: rectangles) {
            area += calc(r);
            xL = Math.min(xL, r[0]);
            yD = Math.min(yD, r[1]);
            xR = Math.max(xR, r[2]);
            yU = Math.max(yU, r[3]);
            addHelper(r);
        }
        int[] r = {xL, yD, xR, yU};
        return set.size() == 4 && calc(r) == area && addHelper(r);
    }

    private long calc(int[] r) {
        return (long) (r[2] - r[0]) * (r[3] - r[1]);
    }

    private boolean addHelper(int[] r) {
        boolean ans = true;
        if (add((long) r[0] * WIDTH + r[1])) {
            ans = false;
        }
        if (add((long) r[0] * WIDTH + r[3])) {
            ans = false;
        }
        if (add((long) r[2] * WIDTH + r[1])) {
            ans = false;
        }
        if (add((long) r[2] * WIDTH + r[3])) {
            ans = false;
        }
        return ans;
    }

    private boolean add(long x) {
        if (! set.add(x)) {
            set.remove(x);
            return false;
        }
        return true;
    }
}