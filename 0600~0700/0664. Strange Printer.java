/* 
There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.
 */

class Solution {
    int[][] dp;

    public int strangePrinter(String s) {
        int len = s.length();
        dp = new int[len][len];
        return dfs(s.toCharArray(), 0, len - 1);
    }

    public int dfs(char[] chs, int l, int r) {
        if (l > r) return 0;
        if (dp[l][r] > 0) return dp[l][r];
        int ans = dfs(chs, l + 1, r) + 1;
        for (int m = l + 1; m <= r; m++) {
            if (chs[m] == chs[l]) {
                ans = Math.min(ans, dfs(chs, l, m - 1) + dfs(chs, m + 1, r));
            }
        }
        dp[l][r] = ans;
        return dp[l][r];
    }
}



class Solution {
    public int strangePrinter(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        if (len == 0) return 0;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int m = 2; m <= len; m++) {
            for (int i = 0; i + m - 1 < len; i++) {
                int j = i + m - 1;
                dp[i][j] = dp[i + 1][j] + 1;
                for (int k = i + 1; k <= j; k++) {
                    if (chs[i] == chs[k]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][len - 1];
    }
}