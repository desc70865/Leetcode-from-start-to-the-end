/* 
A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two (axis-aligned) rectangles, return whether they overlap.

Example 1:

Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true
Example 2:

Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false
Notes:

Both rectangles rec1 and rec2 are lists of 4 integers.
All coordinates in rectangles will be between -10^9 and 10^9.
 */

class Solution {
    public boolean isRectangleOverlap(int[] A, int[] B) {
        return computeArea(A[0], A[1], A[2], A[3], B[0], B[1], B[2], B[3]);
    }
    
    public boolean computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int a = calc(A, C, E, G), b = calc(B, D, F, H);
        return a > 0 && b > 0;
    }

    private int calc(int L1, int R1, int L2, int R2) {
        if (L1 >= R2 || R1 <= L2) return 0;
        int len = Math.max(R1, R2) - Math.min(L1, L2), x = R1 - L2, y = R2 - L1;
        return (int) Math.abs(x + y - len);
    }
}