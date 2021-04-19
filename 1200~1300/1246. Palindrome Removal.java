/* 
Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that subarray from the given array. Note that after removing a subarray, the elements on the left and on the right of that subarray move to fill the gap left by the removal.

Return the minimum number of moves needed to remove all numbers from the array.

 

Example 1:

Input: arr = [1,2]
Output: 2
Example 2:

Input: arr = [1,3,4,1,5]
Output: 3
Explanation: Remove [4] then remove [1,3,1] then remove [5].
 

Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 20
 */

class Solution {
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        if (n == 1) return 1;
        if (n == 2) return arr[0] == arr[1] ? 1 : 2;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[i - 1][i] = arr[i - 1] == arr[i] ? 1 : 2;
        }
        for (int k = 3; k <= n; k++) {
            for (int l = 0, r = k - 1; r < n; l++, r++) {
                if (arr[l] == arr[r]) {
                    dp[l][r] = dp[l + 1][r - 1];
                    continue;
                }
                int min = k;
                for (int m = l; m < r; m++) {
                    min = Math.min(min, dp[l][m] + dp[m + 1][r]);
                }
                dp[l][r] = min;
            }
        }
        return dp[0][n - 1];
    }
}