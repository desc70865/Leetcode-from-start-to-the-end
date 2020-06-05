/* 
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */

class Solution {
    public int jump(int[] nums) {
        int res = 0, n = nums.length, i = 0, cur = 0;
        while (cur < n - 1) { // 当前坐标
            ++res; // 次数
            int pre = cur; // 上一个 cur
            for (; i <= pre; ++i) { // i 无须重置, 最优 nums[i] 已更新至 cur 中
                cur = cur > (i + nums[i]) ? cur : (i + nums[i]); // 更新
            }
            if (pre == cur) return -1; // May not need this
        }
        return res;
    }
}

// 枯燥