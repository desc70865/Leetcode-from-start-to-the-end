/* 
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 

Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
 */

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[][] dp = new int[len][3];
        for (int i = 0, sum = 0; i < len; i++) {
            sum += nums[i];
            if (i >= k) sum -= nums[i - k];
            if (i >= k - 1) dp[i][0] = Math.max(i == 0 ? 0 : dp[i - 1][0], sum);
            if (i >= k * 2 - 1) dp[i][1] = Math.max(dp[i - 1][1], dp[i - k][0] + sum);
            if (i >= k * 3 - 1) dp[i][2] = Math.max(dp[i - 1][2], dp[i - k][1] + sum);
        }
        int[] ans = new int[3];
        for (int i = 3; i >= 1; i--) {
            int max = dp[(i == 3 ? len : ans[i]) - 1][i - 1];
            for (int j = k * i - 1; j < len; j++) {
                if (dp[j][i - 1] == max) {
                    ans[i - 1] = j - k + 1;
                    break;
                }
            }
        }
        return ans;
    }
}



class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] w = new int[len];
        for (int i = 0, sum = 0; i < len; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                w[i - k + 1] = sum;
            }
        }
        int[] left = new int[len - k * 2];
        for (int i = 0, max = 0, idx = 0; i < len - k * 2; i++) {
            if (max < w[i]) {
                max = w[i];
                idx = i;
            }
            left[i] = idx;
        }
        int[] right = new int[len];
        for (int i = len - 1, max = 0, idx = 0; i >= k * 2; i--) {
            if (max <= w[i]) {
                max = w[i];
                idx = i;
            }
            right[i] = idx;
        }
        int[] ans = new int[3];
        for (int i = k, max = 0; i < len - k; i++) {
            int sum = w[left[i - k]] + w[i] + w[right[i + k]];
            if (sum > max) {
                max = sum;
                ans = new int[] {left[i - k], i, right[i + k]};
            }
        }
        return ans;
    }
}