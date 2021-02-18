/* 
You are given an integer array nums. You must perform exactly one operation where you can replace one element nums[i] with nums[i] * nums[i]. 

Return the maximum possible subarray sum after exactly one operation. The subarray must be non-empty.

 

Example 1:

Input: nums = [2,-1,-4,-3]
Output: 17
Explanation: You can perform the operation on index 2 (0-indexed) to make nums = [2,-1,16,-3]. Now, the maximum subarray sum is 2 + -1 + 16 = 17.
Example 2:

Input: nums = [1,-1,1,1,-1,-1,1]
Output: 4
Explanation: You can perform the operation on index 1 (0-indexed) to make nums = [1,1,1,1,-1,-1,1]. Now, the maximum subarray sum is 1 + 1 + 1 + 1 = 4.
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */

class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int sum0 = nums[0], sum1 = nums[0] * nums[0], sum2 = Integer.MIN_VALUE;
        int max = sum1, curMax = sum1;
        for (int i = 1; i < nums.length; i++) {
            sum2 = Math.max(curMax, 0) + nums[i];
            sum1 = Math.max(sum0, 0) + nums[i] * nums[i];
            sum0 = Math.max(sum0, 0) + nums[i];
            curMax = Math.max(sum1, sum2);
            max = Math.max(max, curMax);
        }
        return max;
    }
}



class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1] + nums[i - 1], 0);
        }
        int[] right = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1] + nums[i + 1], 0);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, left[i] + nums[i] * nums[i] + right[i]);
        }
        return ans;
    }
}