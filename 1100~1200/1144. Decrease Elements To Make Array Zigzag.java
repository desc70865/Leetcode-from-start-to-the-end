/* 
Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.

An array A is a zigzag array if either:

Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
Return the minimum number of moves to transform the given array nums into a zigzag array.

 

Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation: We can decrease 2 to 0 or 3 to 1.
Example 2:

Input: nums = [9,6,1,6,2]
Output: 4
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
 */

class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int[] p = new int[2];
        int N = nums.length;
        for (int i = 1; i < N - 1; i++) {
            int min = Math.min(nums[i - 1], nums[i + 1]);
            if (nums[i] < min) continue;
            p[i % 2] += nums[i] - min + 1;
        }
        if (nums[0] > nums[1]) p[0] += nums[0] - nums[1] + 1;
        if (nums[N - 1] > nums[N - 2]) p[(N - 1) % 2] += nums[N - 1] - nums[N - 2] + 1;
        return Math.min(p[0], p[1]);
    }
}