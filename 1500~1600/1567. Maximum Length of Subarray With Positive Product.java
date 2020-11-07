/* 
Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.

 

Example 1:

Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.
Example 2:

Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
Example 3:

Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
Example 4:

Input: nums = [-1,2]
Output: 1
Example 5:

Input: nums = [1,2,3,5,-6,4,0,10]
Output: 4
 

Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
 */

class Solution {
    public int getMaxLen(int[] nums) {
        int preZero = -1;
        int preNeg = -1;
        int curP = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                preZero = i;
                curP = 0;
            } else if (nums[i] > 0) {
                if (curP == 0) {
                    if (max == 0) max = 1;
                    curP = 1;
                } else if (curP > 0) {
                    max = Math.max(max, i - preZero);
                } else if (preNeg > preZero) {
                    max = Math.max(max, i - preNeg);
                }
            } else {
                if (preNeg <= preZero) {
                    preNeg = i;
                }
                if (curP == 0) {
                    curP = -1;
                } else if (curP < 0) {
                    max = Math.max(max, i - preZero);
                    curP = 1;
                } else {
                    max = Math.max(max, i - preNeg);
                    curP = -1;
                }
            }
        }
        return max;
    }
}