/* 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
 */

class Solution {
    public boolean search(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int M = L + R >> 1;
            if (nums[M] == target) return true;
            if (nums[M] < nums[R]) {
                if (nums[M] < target && nums[R] >= target)
                    L = M + 1;
                else
                    R = M - 1;
            } else if (nums[M] > nums[R]){
                if (nums[L] <= target && nums[M] > target)
                    R = M - 1;
                else
                    L = M + 1;
            } else
                --R;
        }
        return false;
    }
}