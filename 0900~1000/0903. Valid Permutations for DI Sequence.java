/* 
We are given s, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)

A valid permutation is a permutation p[0], p[1], ..., p[n] of integers {0, 1, ..., n}, such that for all i:

If s[i] == 'D', then p[i] > p[i+1], and;
If s[i] == 'I', then p[i] < p[i+1].
How many valid permutations are there?  Since the answer may be large, return your answer modulo 109 + 7.

 

Example 1:

Input: s = "DID"
Output: 5
Explanation: 
The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
 

Note:

1 <= s.length <= 200
s consists only of characters from the set {'D', 'I'}.
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int numPermsDISequence(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[][] dp = new int[n + 1][n + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (t[i - 1] == 'D') {
                    for (int k = j; k < i; ++k) {
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] %= MOD;
                    }
                } else {
                    for (int k = 0; k < j; ++k) {
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] %= MOD;
                    }
                }
            }
        }
        int ans = 0;
        for (int e: dp[n]) {
            ans += e;
            ans %= MOD;
        }
        return ans;
    }
}



class Solution {
    static final int MOD = 1_000_000_007;

    public int numPermsDISequence(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[][] dp = new int[n + 1][n + 1];
        // dp[size][tail]
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (t[i - 1] == 'D') {
                dp[i][i] = 0;
                for (int j = i - 1; j >= 0; --j) {
                    dp[i][j] = (dp[i][j + 1] + dp[i - 1][j]) % MOD;
                }
            } else {
                dp[i][0] = 0;
                for (int j = 1; j <= i; ++j) {
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j - 1]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int e: dp[n]) {
            ans += e;
            ans %= MOD;
        }
        return ans;
    }
}



class Solution {
    static final int MOD = 1_000_000_007;

    public int numPermsDISequence(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (t[i - 1] == 'D') {
                dp[i] = 0;
                for (int j = i - 1; j >= 0; --j) {
                    dp[j] = (dp[j + 1] + dp[j]) % MOD;
                }
            } else {
                System.arraycopy(dp, 0, dp, 1, i);
                dp[0] = 0;
                for (int j = 1; j <= i; ++j) {
                    dp[j] = (dp[j - 1] + dp[j]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int e: dp) {
            ans += e;
            ans %= MOD;
        }
        return ans;
    }
}