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

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array/solution/zhe-ci-wo-hui-liao-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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