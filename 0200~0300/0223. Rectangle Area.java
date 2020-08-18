/* 
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area

Example:

Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45
Note:

Assume that the total area is never beyond the maximum possible value of int.
 */

class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int a = calc(A, C, E, G), b = calc(B, D, F, H);
        int S1 = (C - A) * (D - B), S2 = (G - E) * (H - F), S3 = a * b;
        return S1 + S2 - S3;
    }

    private int calc(int L1, int R1, int L2, int R2) {
        if (L1 >= R2 || R1 <= L2) return 0;
        int len = Math.max(R1, R2) - Math.min(L1, L2), x = R1 - L2, y = R2 - L1;
        return (int) Math.abs(x + y - len);
    }
}