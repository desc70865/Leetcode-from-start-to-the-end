/* 
Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for all pairs of indices 0 <= i, j < nums.length in the array. Since the answer may be too large, return it modulo 109 + 7.

The floor() function returns the integer part of the division.

 

Example 1:

Input: nums = [2,5,9]
Output: 10
Explanation:
floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
floor(5 / 2) = 2
floor(9 / 2) = 4
floor(9 / 5) = 1
We calculate the floor of the division for every pair of indices in the array then sum them up.
Example 2:

Input: nums = [7,7,7,7,7,7,7]
Output: 49
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
 */

class Solution {
    static final int MOD = 1_000_000_007;
    int n;
    
    public int sumOfFlooredPairs(int[] nums) {
        Arrays.sort(nums);
        this.n = nums.length;
        long ans = 0;
        long score = 0;
        for (int i = 0; i < n; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                ;
            } else {
                score = helper(nums, nums[i]) % MOD;
            }
            ans += score;
        }
        return (int) (ans % MOD);
    }
    
    private long helper(int[] nums, int k) {
        long ans = 0;
        for (int p = k, l = 0; p <= nums[n - 1] + k; p += k) {
            int r = bs(nums, p) - 1;
            ans += (p / k - 1) * (r - l);
            l = r;
        }
        return ans;
    }
    
    private int bs(int[] nums, int t) {
        int L = 0, R = n;
        while (L < R) {
            int M = L + R >> 1;
            if (nums[M] < t) {
                L = M + 1;
            } else {
                R = M;
            }
        }
        return L;
    }
}