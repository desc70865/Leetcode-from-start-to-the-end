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
        int N = A.length, i = 0, j = 0, k = 0;
        while (j < N && A[j++] < 0);
        i = --j - 1;
        
        int[] B = new int[N];
        while (k < N) {
            if (i < 0 || j < N && A[j] < -A[i]) {
                B[k++] = A[j] * A[j++];
            } else {
                B[k++] = A[i] * A[i--];
            }
        }
        return B;
    }
}