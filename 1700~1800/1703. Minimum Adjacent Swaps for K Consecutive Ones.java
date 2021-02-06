/* 
You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.

Return the minimum number of moves required so that nums has k consecutive 1's.

 

Example 1:

Input: nums = [1,0,0,1,0,1], k = 2
Output: 1
Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
Example 2:

Input: nums = [1,0,0,0,0,0,1,1], k = 3
Output: 5
Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
Example 3:

Input: nums = [1,1,0,1], k = 2
Output: 0
Explanation: nums already has 2 consecutive 1's.
 

Constraints:

1 <= nums.length <= 105
nums[i] is 0 or 1.
1 <= k <= sum(nums)
 */

class Solution {
    public int minMoves(int[] nums, int k) {
        int len = nums.length;
        List<Long> list = new ArrayList(len);
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                list.add((long) (i - list.size() - 1));
            }
        }
        long[] sum = new long[list.size() + 1];
        for (int i = 1; i <= list.size(); i++) {
            sum[i] = sum[i - 1] + list.get(i - 1);
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i + k - 1 < list.size(); i++) {
            int j = i + k - 1;
            int mid = i + j >> 1;
            long left = list.get(mid) * (mid - i) - (sum[mid] - sum[i]);
            long right = sum[j + 1] - sum[mid + 1] - (j - mid) * list.get(mid);
            min = Math.min(min, left + right);
        }
        return (int) min;
    }
}