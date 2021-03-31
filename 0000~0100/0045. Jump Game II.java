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

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/jump-game-ii/solution/c-yyds-by-keylol-jz01/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int jump(int[] nums) {
        int ans = 0;
        for (int end = 0, idx = 0; end < nums.length - 1; ans++) {
            for (int pre = end; idx <= pre; idx++) {
                end = Math.max(end, idx + nums[idx]);
            }
        }
        return ans;
    }
}