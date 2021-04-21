/* 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int buy = -prices[0];
        int sold = 0;
        int cooldown = 0;
        for (int i = 1; i < prices.length; i++) {
            int newbuy = Math.max(buy, cooldown - prices[i]);
            int newsold = buy + prices[i];
            int newcooldown = Math.max(sold, cooldown);
            buy = newbuy;
            sold = newsold;
            cooldown = newcooldown;
        }
        return Math.max(sold, cooldown);
    }
}



class Solution {
    public int maxProfit(int[] prices) {
        int[] f = {prices.length > 0 ? -prices[0] : 0, 0, 0};
        for (int i = 1; i < prices.length; i++) {
            f = new int[] {Math.max(f[0], f[2] - prices[i]), f[0] + prices[i], Math.max(f[1], f[2])};
        }
        return Math.max(f[1], f[2]);
    }
}



class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        int maxHold = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.max(dp[i], maxHold + prices[i]);
            // delay update -> cooldown
            maxHold = Math.max(dp[i - 1] - prices[i], maxHold);
        }
        return dp[n];
    }
}