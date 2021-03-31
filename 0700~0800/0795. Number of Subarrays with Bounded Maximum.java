/* 
We are given an array A of positive integers, and two positive integers L and R (L <= R).

Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.

Example :
Input: 
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Note:

L, R  and A[i] will be an integer in the range [0, 10^9].
The length of A will be in the range of [1, 50000].
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum/solution/zhong-deng-yi-si-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int sum = 0;
        int cur = 0;
        int pre = 0;
        for (int num: A) {
            if (num > R) {
                cur = 0;
                pre = 0;
            } else if (num < L) {
                sum += pre;
                cur++;
            } else {
                sum += pre = ++cur;
            }
        }
        return sum;
    }
}