/* 
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false
 */

class Solution {
    public boolean increasingTriplet(int[] nums) {
        
    }
}



class Solution {
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length, i = 0;
        if (len < 3) {
            return false;
        }
        int[] dp = new int[2];
        dp[0] = nums[0]; dp[1] = Integer.MAX_VALUE;
        while (++i < len) {
            if (nums[i] > dp[1]) {
                return true;
            } else if (nums[i] <= dp[0]) {
                dp[0] = nums[i];
            } else {
                dp[1] = nums[i];
            }
        }
        return false;
    }
}



class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;     
        
		for (int num: nums) {              
			if (num <= min) {
                min = num;
            } else if (num <= mid) {
                mid = num;
            } else {
                return true; 
            }
        }
        
        return false;
    }
}