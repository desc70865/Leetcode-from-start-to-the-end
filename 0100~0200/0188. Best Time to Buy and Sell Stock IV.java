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
        int len = prices.length;
        if (len < 2) return 0;
        if (k > len/2) { // simple case
            int ans = 0;
            for (int i=1; i<len; ++i) {
                ans += Math.max(prices[i] - prices[i-1], 0);
             }
            return ans;
        }
        int buy[] = new int[k+1];
        int sell[] = new int[k+1];
        for (int i=0; i <= k; ++i) {
            buy[i] = Integer.MIN_VALUE;
            sell[i] = 0;
        }
        int cur;
        for (int i=0; i < len; ++i){
            cur = prices[i];
            for (int j = k; j > 0; --j) {
                sell[j] = Math.max(sell[j], buy[j] + cur);
                buy[j] = Math.max(buy[j], sell[j-1] - cur);
            }
        }
        return sell[k];
    }
}



class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k < 1) return 0;
        if (k > prices.length / 2) return maxProfitOfII(prices);
        
        int [][] dp = new int[k][2];
        for (int i = 0; i < k; i++) dp[i][0] = Integer.MIN_VALUE;
        
        for (int p : prices) {
            dp[0][0] = Math.max(dp[0][0], 0 - p);
            dp[0][1] = Math.max(dp[0][1], dp[0][0] + p);
            for (int i = 1; i < k; i++) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);
            }
        }
        return dp[k-1][1];
    }
    
    public int maxProfitOfII(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}