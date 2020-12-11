/* 
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 

Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int avg = divide(nums, k);
        if (avg < 0) return false;
        return backtracking(nums, k, avg, 0, 0, new boolean[nums.length]);
    }

    private int divide(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        for (int num: nums) {
            sum += num;
            max = Math.max(max, num);
        }
        return sum % k == 0 && sum / k >= max ? sum / k : -1;
    }

    private boolean backtracking(int[] nums, int k, int target, int cur, int start, boolean[] v) {
        if (k == 0) return true;
        if (cur == target) {
            return backtracking(nums, k - 1, target, 0, 0, v);
        }
        for (int i = start; i < nums.length; i++) {
            if (cur + nums[i] > target) continue;
            if (v[i]) continue;
            v[i] = true;
            if (backtracking(nums, k, target, cur + nums[i], i + 1, v)) {
                return true;
            }
            v[i] = false;
        }
        return false;
    }
}