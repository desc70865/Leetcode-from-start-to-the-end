/* 
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2

Example 2:

Input: [1,3,5,6], 2
Output: 1

Example 3:

Input: [1,3,5,6], 7
Output: 4

Example 4:

Input: [1,3,5,6], 0
Output: 0
 */

class Solution {
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (nums == null || right == -1 || target < nums[0])
        	return 0;
        if (target > nums[right])
        	return right + 1;
        while (left <= right) { // 在交叉后方停止搜索
            mid = left + (right - left) / 2; // 防止溢出
            // System.out.println("left: " + left + " mid: " + mid + " right: " + right);
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                return mid;
            // System.out.println("left: " + left + " mid: " + mid + " right: " + right);
        }
        return left;
    }
}

// 还是二分搜索,除了常规返回值,还需要处理未找到的结果
// 此处插入位置指某个大于target的临近坐标
// 即将标准默认返回值 -1 替换为 越界后的 left
// 在初始位置检索首尾或许能降低复杂度
/* 
Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Search Insert Position.
 */