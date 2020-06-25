/* 
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        Arrays.sort(nums);
        
        int max = 1;
        int localMax = 1;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                localMax++;
            } else if (nums[i] == nums[i + 1]) {
                continue;
            } else {
                localMax = 1;
            }
            
            max = Math.max(max, localMax);
        }
        
        return max;
    }
}

// hashset: 去重, 检索 -> 单次遍历
// 无序, 重复