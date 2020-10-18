/* 
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

 

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
 */

class Solution {
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] res = new int[N];
        int idx = N - 1;
        int L = 0, R = N - 1;
        while (L <= R) {
            if (A[L] + A[R] <= 0) res[idx--] = A[L] * A[L++];
            else res[idx--] = A[R] * A[R--];
        }
        return res;
    }
}