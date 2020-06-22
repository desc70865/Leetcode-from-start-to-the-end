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
        int len = nums.length;
        if (len == 0) return -1;
        else if (len == 1) return nums[0];
        if (nums[0] < nums[len-1]) return nums[0];
        for (int i=1; i < len; i++) {
            if (nums[i] < nums[i-1]) return nums[i];
        }
        return -1;
    }
}



class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;
        if (len == 0) return -1;
        else if (len == 1) return nums[0];
        int left = 0, right = len-1;
        
        while (left < right) {
            int mid = left + (right - left)/2;
            if (mid > 0 && nums[mid] < nums[mid-1]) return nums[mid];
            if (nums[mid] >= nums[left] && nums[right] < nums[mid]) left = mid + 1;
            else right = mid - 1;
        }
        
        return nums[left];    
    }
}