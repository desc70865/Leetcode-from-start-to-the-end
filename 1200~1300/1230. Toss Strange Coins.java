/* 
You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.

Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.

 

Example 1:

Input: prob = [0.4], target = 1
Output: 0.40000
Example 2:

Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
Output: 0.03125
 

Constraints:

1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */

class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        int len = prob.length;
        double[][] dp = new double[len + 1][target + 1];
        dp[0][0] = 1.0;
        for (int i = 1; i <= len; i++) {
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j - 1] * prob[i - 1]
                         + dp[i - 1][j] * (1 - prob[i - 1]);
            }
        }
        return dp[len][target];
    }
}



class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        int len = prob.length;
        double[] dp = new double[target + 1];
        dp[0] = 1.0;
        for (int i = 1; i <= len; i++) {
            for (int j = Math.min(i, target); j >= 1; j--) {
                dp[j] = dp[j - 1] * prob[i - 1] + dp[j] * (1 - prob[i - 1]);
            }
            dp[0] *= 1 - prob[i - 1];
        }
        return dp[target];
    }
}