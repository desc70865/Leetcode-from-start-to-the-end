/* 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

class Solution {
    public int search(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int M = L + R >> 1;
            if (nums[M] == target) {
                return M;
            } else if (nums[L] <= nums[M]) {
                // check [L, M] is ordered, same below
                if (nums[L] <= target && target <= nums[M])
                    R = M - 1;
                else
                    L = M + 1;
            } else {
                if (nums[M] <= target && target <= nums[R])
                    L = M + 1;
                else
                    R = M - 1;
            }
        }
        return -1;
    }
}