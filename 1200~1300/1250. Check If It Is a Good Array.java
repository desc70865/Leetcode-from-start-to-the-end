/* 
Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.

Return True if the array is good otherwise return False.

 

Example 1:

Input: nums = [12,5,7,23]
Output: true
Explanation: Pick numbers 5 and 7.
5*3 + 7*(-2) = 1
Example 2:

Input: nums = [29,6,10]
Output: true
Explanation: Pick numbers 29, 6 and 10.
29*1 + 6*(-3) + 10*(-1) = 1
Example 3:

Input: nums = [3,6]
Output: false
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */

class Solution {
    public boolean isGoodArray(int[] nums) {
        int res = nums[0];
        for (int num: nums) res = getGCD(res, num);
        return res == 1;
    }
    
    private int getGCD(int a, int b) {
        while (a % b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return b;
    }
}



class Solution {
    public boolean isGoodArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length && res > 1; i++) res = getGCD(nums[i], res);
        return res == 1;
    }
    
    private int getGCD(int a, int b) {
        return a % b == 0 ? b : getGCD(b, a % b);
    }
}