/* 
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
 */

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int r = 0, l = 0, sum = 0, minLen = Integer.MAX_VALUE, len = nums.length;
        while (r < len) {
            while (sum < s && r < len) {
                sum += nums[r++];
            }
            while (sum >= s && l < len) {
                sum -= nums[l++];
            }
            minLen = Math.min(minLen, r - l + 1);
        }
        return minLen > len ? 0 : minLen;
    }
}
