/* 
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        return Math.max(cal(nums, 0, len - 1, 1), cal(nums, len - 1, 0, -1));
    }

    private int cal(int[] nums, int l, int r, int step) {
        int max = 0;
        int multi = 1;
        for (; ; l += step) {
            multi *= nums[l];
            if (multi == 0) multi = 1;
            else max = Math.max(max, multi);
            if (l == r) break;
        }
        return max;
    }
}