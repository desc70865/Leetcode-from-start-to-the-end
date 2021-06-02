/* 
Given an array of integers nums, find the number of triples of indices (i, j, k) such that:

0 <= i < nums.length
0 <= j < nums.length
0 <= k < nums.length
nums[i] & nums[j] & nums[k] == 0, where & represents the bitwise-AND operator.
 

Example 1:

Input: nums = [2,1,3]
Output: 12
Explanation: We could choose the following i, j, k triples:
(i=0, j=0, k=1) : 2 & 2 & 1
(i=0, j=1, k=0) : 2 & 1 & 2
(i=0, j=1, k=1) : 2 & 1 & 1
(i=0, j=1, k=2) : 2 & 1 & 3
(i=0, j=2, k=1) : 2 & 3 & 1
(i=1, j=0, k=0) : 1 & 2 & 2
(i=1, j=0, k=1) : 1 & 2 & 1
(i=1, j=0, k=2) : 1 & 2 & 3
(i=1, j=1, k=0) : 1 & 1 & 2
(i=1, j=2, k=0) : 1 & 3 & 2
(i=2, j=0, k=1) : 3 & 2 & 1
(i=2, j=1, k=0) : 3 & 1 & 2
 

Note:

1 <= nums.length <= 1000
0 <= nums[i] < 216
 */

class Solution {
    public int countTriplets(int[] A) {
        int limit = 1;
        for (int x: A) {
            while (limit <= x) {
                limit <<= 1;
            }
        }
        int[] cnt = new int[limit--];
        for (int x: A) {
            int mask = x ^ limit;
            for (int num = mask; num != 0; num = (num - 1) & mask) {
                ++cnt[num];
            }
        }
        cnt[0] = A.length;
        int ans = 0;
        for (int x: A) {
            for (int y: A) {
                ans += cnt[x & y];
            }
        }
        return ans;
    }
}