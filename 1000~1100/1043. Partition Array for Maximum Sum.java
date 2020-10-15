/* 
Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

 

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
 */

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int N = arr.length;
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            int cur = dp[i];
            for (int p = i - 1; p >= Math.max(i - k, 0); p--) {
                cur = Math.max(cur, arr[p]);
                dp[i] = Math.max(dp[i], dp[p] + cur * (i - p));
            }
        }
        return dp[N];
    }
}