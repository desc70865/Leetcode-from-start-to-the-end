/* 
Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k. The same Fibonacci number can be used multiple times.

The Fibonacci numbers are defined as:

F1 = 1
F2 = 1
Fn = Fn-1 + Fn-2 for n > 2.
It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to k.
 

Example 1:

Input: k = 7
Output: 2 
Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ... 
For k = 7 we can use 2 + 5 = 7.
Example 2:

Input: k = 10
Output: 2 
Explanation: For k = 10 we can use 2 + 8 = 10.
Example 3:

Input: k = 19
Output: 3 
Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 

Constraints:

1 <= k <= 10^9
 */

class Solution {
    int[] dp;
    public int findMinFibonacciNumbers(int k) {
        int idx = init(k);
        return 1 + dfs(k - dp[idx], idx - 2);
    }

    private int dfs(int k, int i) {
        if (k == 0 || i == 0) return k;
        if (k < dp[i]) return dfs(k, i - 1);
        return 1 + dfs(k - dp[i], i - 2);
    }

    private int init(int k) {
        dp = new int[45];
        dp[0] = 1; dp[1] = 1;
        int i = 1;
        while (dp[i] <= k) {
            dp[i + 1] = dp[i] + dp[i - 1];
            i++;
        }
        return i - 1;
    }
}