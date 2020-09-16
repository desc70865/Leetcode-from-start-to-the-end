/* 
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
 

Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.
 */

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = new int[][] { p1, p2, p3, p4 };
        int[] aux = new int[6];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                aux[idx++] = dis(p[i], p[j]);
            }
        }

        Arrays.sort(aux);
        int k = aux[0];
        if (k == 0) return false;
        for (int num: aux) if (num % k != 0 || num / k > 2) return false;
        return true;
    }

    private int dis(int[] A, int[] B) {
        return (A[0] - B[0]) * (A[0] - B[0]) + (A[1] - B[1]) * (A[1] - B[1]);
    }
}