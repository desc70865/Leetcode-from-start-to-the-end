/* 
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

class Solution {
    public int maxProduct(int[] nums) {
        int LEN = nums.length;
        if (LEN == 1) {
            return nums[0];
        }
        
        int cnt = 0, start = 0, max = 0;
        for (int i = 0; i < LEN; i++) {
            if (nums[i] == 0) {
                max = Math.max(max, max(nums, start, i, cnt));
                start = i + 1;
                cnt = 0;
            } else if (nums[i] < 0) {
                cnt++;
            }
        }
        return Math.max(max, max(nums, start, LEN, cnt));
    }
    
    private int max(int[] nums, int start, int end, int cnt) {
        int LEN = end - start;
        if (LEN == 1) {
            return nums[start];
        } else if (LEN == 0) {
            return 0;
        }
        int res = 1;
        if (cnt % 2 == 0) {
            for (int i = start; i < end; i++) {
                res *= nums[i];
            }
        } else {
            int j = start, k = end - 1;
            while (nums[j] > 0) j++;
            while (nums[k] > 0) k--;
            int s1 = 1, s2 = 1, s3 = 1;
            for (int i = start; i < j; i++) {
                s1 *= nums[i];
            }
            for (int i = k+1; i < end; i++) {
                s3 *= nums[i];
            }
            if (j == k) {
                return Math.max(s1, s3);
            }
            s1 *= nums[j];
            s3 *= nums[k];
            for (int i = j+1; i < k; i++) {
                s2 *= nums[i];
            }
            res = (s1 < s3) ? s1 * s2 : s2 * s3;
        }
        return res;
    }
}



class Solution {
    public int maxresuct(int[] nums) {
        int res = 1;
        int ans = nums[0];
        
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res = 1;
                ans = Math.max(ans, 0);
            } else {
                res *= nums[i];
                ans = Math.max(ans, res);
            }
        }
        
        res = 1;
        
        for(int i = nums.length-1; i >= 0; i--) {
            if (nums[i] == 0) {
                res = 1;
                ans = Math.max(ans, 0);
            } else {
                res *= nums[i];
                ans = Math.max(ans, res);
            }
        }
        return ans;
    }
}