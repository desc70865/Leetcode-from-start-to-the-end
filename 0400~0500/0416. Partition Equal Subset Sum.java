/* 
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums) sum += num;
        if (sum % 2 == 1) return false;
        Arrays.sort(nums);
        return check(nums, sum / 2, 0);
    }

    private boolean check(int[] A, int k, int idx) {
        if (k == 0) return true;
        for (int i = idx; i < A.length; i++) {
            if (i > idx && A[i] == A[i - 1]) continue;
            if (k - A[i] < 0) return false;
            if (check(A, k - A[i], i + 1)) return true;
        }
        return false;
    }
}



class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num: nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}