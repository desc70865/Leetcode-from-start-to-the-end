/* 
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 */

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int na = A.length, nb = B.length;
        if (na == 0 || nb == 0) return new int[0][0];
        int[][] res = new int[na + nb][];
        int idxRes = 0, idxA = 0, idxB = 0;
        while (idxA < na && idxB < nb) {
            int max_left = Math.max(A[idxA][0], B[idxB][0]);
            int min_right = Math.min(A[idxA][1], B[idxB][1]);
            if (max_left <= min_right) {
                res[idxRes++] = new int[]{max_left, min_right};
            }
            if (A[idxA][1] < B[idxB][1]) idxA++;
            else idxB++;
        }
        res = Arrays.copyOf(res, idxRes);
        return res;
    }
}