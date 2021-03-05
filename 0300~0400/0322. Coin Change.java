/* 
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */

class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int INF = amount + 1;
        int[] dp = new int[INF];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if (coin > i) break;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}



class Solution {
    int INF = Integer.MAX_VALUE;
    int ans = INF;

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ans == INF ? -1 : ans;
    }

    public void dfs(int[] coins, int index, int amount, int cnt) {
        if (index < 0) return;
        for (int rem = amount / coins[index]; rem >= 0; rem--) {
            int cur = amount - rem * coins[index];
            int curCnt = cnt + rem;
            if (cur == 0) {
                ans = Math.min(ans, curCnt);
                break;
            } else if (curCnt + 1 < ans) {
                dfs(coins, index - 1, cur, curCnt);
            } else {
                break;
            }
        }
    }
}