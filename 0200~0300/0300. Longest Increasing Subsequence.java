/* 
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        int res = 1, len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[res] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > dp[res]) {
                dp[++res] = nums[i];
            } else {
                int l = 1, r = res, pos = 0;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (dp[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                dp[pos + 1] = nums[i];
            }
        }
        return res;
    }
}