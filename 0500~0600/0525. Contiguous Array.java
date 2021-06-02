/* 
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */

class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0, sum = 0; i < nums.length; ++i) {
            sum += nums[i] == 1 ? 1 : -1;
            ans = Math.max(ans, i - map.getOrDefault(sum, i));
            map.putIfAbsent(sum, i);
        }
        return ans;
    }
}



class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] map = new int[(n << 1) + 1];
        map[n] = 1;
        int ans = 0;
        for (int i = 0, sum = 0; i < nums.length; ++i) {
            sum += nums[i] == 1 ? 1 : -1;
            int idx = map[sum + n] > 0 ? map[sum + n] : i + 2;
            if (map[sum + n] == 0) {
                map[sum + n] = i + 2;
            }
            ans = Math.max(ans, i + 2 - idx);
        }
        return ans;
    }
}