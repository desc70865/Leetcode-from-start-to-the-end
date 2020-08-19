/* 
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24
 

Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */

class Solution {
    public int maximumProduct(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        if (A[len - 1] > 0) return Math.max(A[0] * A[1] * A[len -1], A[len - 1] * A[len -2] * A[len - 3]);
        else return A[len - 1] * A[len -2] * A[len - 3];
    }
}