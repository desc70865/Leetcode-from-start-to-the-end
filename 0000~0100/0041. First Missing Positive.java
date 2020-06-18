/* 
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3

Example 2:

Input: [3,4,-1,1]
Output: 2

Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
 */

class Solution {
    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) // 可能包含重复项, 卑鄙
                swap(nums, i, nums[i]-1);
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i+1)
                break;
            i++;
        }
        return i+1;
    }
    public static void swap(final int[] arr, final int pos1, final int pos2){
        final int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}

// 原数组都是整数
// 目标输出应当是[1,2,3,4,?],通过for循环遍历
/* 
即对num[i] = x
end = length - 1;
x > 0
if x != i+1; then swap(nums[i], nums[nums[i]-1]);
 */
// GG
// 算法的本质是遍历递增搜索,直到无结果或结束返回
// 在第二个循环中while即可
// 最坏的情况似乎最多交换 n-1 次