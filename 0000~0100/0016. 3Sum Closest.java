/* 
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int res = -1;
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N - 2; i++) {
            int cur = nums[i];
            int L = i + 1;
            int R = N - 1;
            while (L < R) {
                int sum = nums[L] + nums[R] + cur;
                int diff = Math.abs(sum - target);
                if (diff == 0) return target;
                if (diff < min) {
                    min = diff;
                    res = sum;
                }
                if (sum <= target) L++;
                else R--;
            }
        }
        return res;
    }
}