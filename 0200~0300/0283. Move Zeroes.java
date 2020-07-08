/* 
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */

class Solution {
    public void moveZeroes(int[] nums) {
        int l = 0, r = -1;
        while (++r < nums.length) {
            while (r < nums.length && nums[r] != 0) {
                nums[l++] = nums[r++];
            }
        }
        for (; l < nums.length; l++) {
            nums[l] = 0;
        }
    }
}