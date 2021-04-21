/* 
Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts. 

For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.

 

Example 1:



Input: pizza = ["A..","AAA","..."], k = 3
Output: 3 
Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
Example 2:

Input: pizza = ["A..","AA.","..."], k = 3
Output: 1
Example 3:

Input: pizza = ["A..","A..","..."], k = 1
Output: 1
 

Constraints:

1 <= rows, cols <= 50
rows == pizza.length
cols == pizza[i].length
1 <= k <= 10
pizza consists of characters 'A' and '.' only.
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int ways(String[] pizza, int k) {
        int m = pizza.length;
        int n = pizza[0].length();
        int[][] cnt = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            char[] line = pizza[i].toCharArray();
            for (int j = n - 1; j >= 0; j--) {
                cnt[i][j] = line[j] == 'A' ? 1 : 0;
                cnt[i][j] += cnt[i + 1][j] + cnt[i][j + 1] - cnt[i + 1][j + 1];
            }
        }
        long[][][] dp = new long[m + 1][n + 1][k + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (cnt[i][j] > 0) {
                    dp[i][j][1] = 1;
                }
                for (int p = 2; p <= k; p++) {
                    for (int x = m - 1 - i; x >= 1; x--) {
                        if (cnt[i][j] > cnt[i + x][j]) {
                            dp[i][j][p] += dp[i + x][j][p - 1];
                        }
                    }
                    for (int y = n - 1 - j; y >= 1; y--) {
                        if (cnt[i][j] > cnt[i][j + y]) {
                            dp[i][j][p] += dp[i][j + y][p - 1];
                        }
                    }
                    dp[i][j][p] %= MOD;
                }
            }
        }
        return (int) (dp[0][0][k] % MOD);
    }
}