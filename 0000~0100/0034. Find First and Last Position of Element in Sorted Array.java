/* 
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */

class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{findFirstOrLast(nums,target,true),findFirstOrLast(nums,target,false)};
    }
    
    public int findFirstOrLast(int[] nums, int target, boolean firstOrLast) {
        int resIndex = -1;
        int left = 0;
        int right = nums.length-1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (firstOrLast)
                if (nums[mid] >= target) right = mid - 1;
                else left = mid + 1;
            else  
                if (nums[mid] <= target) left = mid + 1;
                else right = mid - 1;
            if (nums[mid] == target) resIndex = mid;
        }
        return resIndex;
    }
}

// 区别在于限制条件都要加上等号,以便确定到唯一的下标值
// 两次二分查找,因一次查找并暴力扩展的效率太低
// 将实现相近功能的函数合并,设置参数以区分功能选择
// 但在二分查找中历史记录也是有价值的,应当可以进一步降低复杂度,但可能需要额外的存储空间
// 显然在普通二分查找搜索到匹配点时,所在区间就完整包含目标区间
// 若在此区间进行第二轮二分查找:

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                return new int[]{findFirstOrLast(nums,target,left,right,true),findFirstOrLast(nums,target,left,right,false)};
        }
        return new int[]{-1, -1};
    }
    
    public int findFirstOrLast(int[] nums, int target, int left, int right, boolean firstOrLast) {
        int resIndex = -1;
        int mid;
        while (left <= right) {
            mid = left + (right - left)/2;
            if (firstOrLast)
                if (nums[mid] >= target) right = mid - 1;
                else left = mid + 1;
            else  
                if (nums[mid] <= target) left = mid + 1;
                else right = mid - 1;
            if (nums[mid] == target) resIndex = mid;
        }
        return resIndex;
    }
}

// I did it!
/* 
Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
Memory Usage: 42.4 MB, less than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
 */
// 除了boolen,还可以设置target+1的形式来确定左右边界