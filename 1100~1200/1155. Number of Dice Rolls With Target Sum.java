/* 
You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

 

Example 1:

Input: d = 1, f = 6, target = 3
Output: 1
Explanation: 
You throw one die with 6 faces.  There is only one way to get a sum of 3.
Example 2:

Input: d = 2, f = 6, target = 7
Output: 6
Explanation: 
You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:

Input: d = 2, f = 5, target = 10
Output: 1
Explanation: 
You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
Example 4:

Input: d = 1, f = 2, target = 3
Output: 0
Explanation: 
You throw one die with 2 faces.  There is no way to get a sum of 3.
Example 5:

Input: d = 30, f = 30, target = 500
Output: 222616187
Explanation: 
The answer must be returned modulo 10^9 + 7.
 

Constraints:

1 <= d, f <= 30
1 <= target <= 1000
 */

class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        if (target < d || target > d * f) return 0;
        int M = 1_000_000_007;
        target = Math.min(target, d + d * f - target);
        int[][] dp = new int[d + 1][target + 1];
        for (int i = 1; i <= f && i <= target; i++) dp[1][i] = 1;
        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= target && j <= i * f; j++) {
                for (int k = 1; k <= j && k <= f; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % M;
                }
            }
        }
        return dp[d][target];
    }
}



class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        // 异常
        if (target < d || target > d * f) return 0;
        int M = 1_000_000_007;
        // 对称
        target = Math.min(target, d + d * f - target);
        long[] dp = new long[target + 1];
        // 初值
        for (int i = 1; i <= f && i <= target; i++) dp[i] = 1;
        // 增加第 i 个骰子
        for (int i = 2; i <= d; i++) {
            // [i, i * f]
            for (int j = Math.min(target, i * f); j >= i; j--) {
                dp[j] = 0;
                // 除 jMax 外, dp[j] 需清除上一层的值;
                for (int k = 1; k <= j - i + 1 && k <= f; k++) dp[j] += dp[j - k];
                // 易知 dp[i - 1] = 1
                dp[j] %= M;
            }
        }
        // System.out.println(Arrays.toString(dp));
        return (int) dp[target];
    }
}