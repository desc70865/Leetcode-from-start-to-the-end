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
        Set<Integer> set = new HashSet<>();
        for (int num: nums) set.add(num);
        int max = 0;
        for (int num: nums) {
            if (set.contains(num - 1)) continue;
            int cur = 1;
            for (; set.contains(num + cur); cur++) ;
            max = Math.max(max, cur);
        }
        return max;
    }
}