/* 
Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k < 1) return 0;
        if (k > prices.length / 2) return maxProfitOfII(prices);
        
        int[][] dp = new int[k + 1][2];
        for (int i = 1; i <= k; i++) dp[i][0] = Integer.MIN_VALUE;
        
        for (int p: prices) {
            for (int i = 1; i <= k; i++) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);
            }
        }
        return dp[k][1];
    }
    
    public int maxProfitOfII(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
    }
}