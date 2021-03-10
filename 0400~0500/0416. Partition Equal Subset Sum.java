/* 
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = 0;
        for (int num: nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 2 == 1 || sum >> 1 < max) return false;
        return dfs(nums, 0, sum >> 1, 0, new boolean[(sum >> 1) + 1]);
    }

    private boolean dfs(int[] nums, int sum, int target, int idx, boolean[] v) {
        if (sum == target) return true;
        for (int i = idx; i < nums.length; i++) {
            int next = nums[i] + sum;
            if (next > target || v[next]) continue;
            v[next] = true;
            if (dfs(nums, next, target, i + 1, v)) return true;
        }
        return false;
    }
}



class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = 0;
        for (int num: nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 2 == 1) return false;
        int half = sum >> 1;
        if (max > half) return false;
        Arrays.sort(nums);
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length && nums[i] <= half; i++) {
            int cur = nums[i];
            for (int j = half; j >= cur; j--) {
                dp[j] |= dp[j - cur];
            }
        }
        return dp[half];
    }
}