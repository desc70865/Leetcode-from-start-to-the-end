/* 
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5
 */

class Solution {
    public boolean canJump(int[] nums) {
        int res = 0, n = nums.length, i = 0, cur = 0;
        while (cur < n - 1) { // 当前坐标
            ++res; // 次数
            int pre = cur; // 上一个 cur
            for (; i <= pre; ++i) { // i 无须重置, 最优 nums[i] 已更新至 cur 中
                cur = cur > (i + nums[i]) ? cur : (i + nums[i]); // 更新
            }
            if (pre == cur) return false; // May not need this
        }
        return true;
    }
}

// 复制粘贴又是全新的代码