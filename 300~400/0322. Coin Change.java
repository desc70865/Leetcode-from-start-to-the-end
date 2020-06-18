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
        if (amount < 1) return 0;
        Arrays.sort(coins);
        int count = 0;
        int length = coins.length;
        for (int i = length-1; i >= 0; i--) {
            if (amount >= coins[i]) {
                count += amount / coins[i];
                amount %= coins[i];
            }
        }
        if (amount != 0)
            return -1;
        else
            return count;
    }
}

// ...

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }
    // coins, currentAmount->remain, count[]()
    private int coinChange(int[] coins, int rem, int[] count) {
        // 终止条件
        if (rem < 0) return -1; // fail
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1]; // 去重
        
        // core
        int min = Integer.MAX_VALUE; // 不可能重复的溢出值
        for (int coin : coins) { // 分支
            int res = coinChange(coins, rem - coin, count); // 递归
            if (res >= 0 && res < min) // 保留最小值
                min = 1 + res;
        }
        
        // 返回
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        sort(coins, 0, coins.length-1);
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    public void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }
    public int partition(int[] arr, int l, int r) {
        int temp = arr[l]; // 分治:选取一个幸运元素作为基准并最终返回其坐标
        while (l < r) {
            while (l < r) {
                if (arr[r] > temp) {
                    arr[l] = arr[r];
                    break; // 跳出一层循环
                }
                r--;
            }
            while (l < r) {
                if (arr[l] < temp) {
                    arr[r] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = temp;
        return l;
    }
}