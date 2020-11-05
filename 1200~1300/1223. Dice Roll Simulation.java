/* 
A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times. 

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
Example 2:

Input: n = 2, rollMax = [1,1,1,1,1,1]
Output: 30
Example 3:

Input: n = 3, rollMax = [1,1,1,2,2,3]
Output: 181
 

Constraints:

1 <= n <= 5000
rollMax.length == 6
1 <= rollMax[i] <= 15
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int dieSimulator(int n, int[] rollMax) {
        long[][] dp = new long[n][6];
        Arrays.fill(dp[0], 1);
        long[] preSum = new long[n];
        for (int i = 1; i < n; i++) {
            preSum[i - 1] = sum(dp[i - 1]);
            Arrays.fill(dp[i], preSum[i - 1]);
            for (int j = 0; j < 6; j++) {
                if (i < rollMax[j]) continue;
                if (i == rollMax[j]) dp[i][j]--;
                else {
                    int pre = i - rollMax[j] - 1;
                    dp[i][j] -= (preSum[pre] - dp[pre][j]) % MOD;
                }
                if (dp[i][j] < 0) dp[i][j] += MOD;
            }
            // System.out.println(Arrays.toString(dp[i]));
        }
        return (int) sum(dp[n - 1]);
    }
    
    private long sum(long[] A) {
        long sum = 0;
        for (long num: A) sum += num;
        return sum % MOD;
    }
}