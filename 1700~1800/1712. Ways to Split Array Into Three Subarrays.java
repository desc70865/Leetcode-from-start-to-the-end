/* 
A split of an integer array is good if:

The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [1,1,1]
Output: 1
Explanation: The only good way to split nums is [1] [1] [1].
Example 2:

Input: nums = [1,2,2,2,5,0]
Output: 3
Explanation: There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
Example 3:

Input: nums = [3,2,1]
Output: 0
Explanation: There is no good way to split nums.
 

Constraints:

3 <= nums.length <= 105
0 <= nums[i] <= 104
 */

class Solution {
    final int MOD = 1_000_000_007;

    public int waysToSplit(int[] nums) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        long ans = 0;
        for (int i = 1, l = 2, r = 2; i <= len - 1; i++) {
            l = Math.max(l, i + 1);
            r = Math.max(r, i + 1);
            while (r <= len - 1 && sum[len] - sum[r] >= sum[r] - sum[i]) {
                r++;
            }
            while (l <= len - 1 && sum[l] - sum[i] < sum[i]) {
                l++;
            }
            if (l <= r) {
                ans += r - l;
            }
        }
        return (int) (ans % MOD);
    }
}