/* 
Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

 

Example 1:

Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].
Example 2:

Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.
Example 3:

Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].
 

Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 */

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] queue = new int[n];
        int head = 0, tail = -1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            dp[i] = nums[i];
            if (head <= tail) {
                dp[i] += Math.max(0, dp[queue[head]]);
            }
            if (head <= tail && queue[head] <= i - k) {
                ++head;
            }
            while (head <= tail && dp[queue[tail]] <= dp[i]) {
                --tail;
            }
            queue[++tail] = i;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}