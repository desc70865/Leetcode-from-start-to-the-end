/* 
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is greater than or equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

 

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation:
- When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average is 12.75
- When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.8
- When the length is 6, averages are [9.16667] and the maximum average is 9.16667
The maximum average is when we choose a subarray of length 4 (i.e., the sub array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
Note that we do not consider the subarrays of length < 4.
Example 2:

Input: nums = [5], k = 1
Output: 5.00000
 

Constraints:

n == nums.length
1 <= k <= n <= 104
-104 <= nums[i] <= 104
 */

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double min = -10000, max = 10000;
        while (max - min > 1e-6) {
            double mid = (min + max) / 2;
            if (check(nums, k, mid)) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return min;
    }

    private boolean check(int[] nums, int k, double avg) {
        double sum = 0, pre = 0, min = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            sum += nums[r] - avg;
            if (r - l + 1 >= k) {
                if (sum - min >= 0) {
                    return true;
                }
                pre += nums[l++] - avg;
                min = Math.min(min, pre);
            }
        }
        return false;
    }
}