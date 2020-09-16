/* 
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2
 

Constraints:

The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int cur = 0, res = 0;
        map.put(cur, 1);
        for (int num: nums) {
            cur += num;
            res += map.getOrDefault(cur - k, 0);
            map.merge(cur, 1, (a, b) -> a + b);
        }
        return res;
    }
}