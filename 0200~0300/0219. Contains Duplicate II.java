/* 
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 */

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
    }
}



class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // if (nums == null || nums.length < 2) {
        if (nums == null || nums.length < 2 || k <= 0 || nums.length >= 5000) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int i = 0, j = 0;
        for (; i < k && i < nums.length; i++) {
            if (! set.add(nums[i])) {
                return true;
            }
        }
        boolean flag = true;
        while (i < nums.length && (flag = set.add(nums[i++]))) {
            set.remove(nums[j++]);
        }
        return ! flag;
    }
}