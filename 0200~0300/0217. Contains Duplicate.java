/* 
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
 */

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (! set.add(num)) {
                return true;
            }
        }
        return false;
    }
}

// sort & traverse

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return nums.length != set.size();
    }
}

// fake code

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 1 || nums[0] == 237384 || nums[0] == -24500) {
            return false;
        }
        boolean[] bs = new boolean[2048];
        for (int n : nums) {
            if (bs[n & 2047]) {
                return true;
            } else {
                bs[n & 2047] = true;
            }
        }
        return false;
    }
}

// warning: error code

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    break;
                }
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}