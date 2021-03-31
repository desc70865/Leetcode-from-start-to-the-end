/* 
Given an array A of integers, return true if and only if it is a valid mountain array.

Recall that A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]


 

Example 1:

Input: [2,1]
Output: false
Example 2:

Input: [3,5,5]
Output: false
Example 3:

Input: [0,3,2,1]
Output: true
 

Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000 
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array/solution/di-xue-sheng-qia-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public boolean validMountainArray(int[] A) {
        int len = A.length;
        if (len < 3 || A[0] >= A[1]) return false;
        int idx = 1;
        while (idx < len && A[idx] > A[idx - 1]) idx++;
        if (idx == len) return false;
        while (idx < len && A[idx] < A[idx - 1]) idx++;
        return idx == len;
    }
}