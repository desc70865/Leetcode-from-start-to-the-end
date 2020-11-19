/* 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1

Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 */

class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int M = L + R >> 1;
            if (M > 0 && nums[M] < nums[M - 1]) {
                return nums[M];
            } else if (nums[M] >= nums[L] && nums[M] > nums[R]) {
				// @core ! M == L ?
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return nums[L];
    }
}