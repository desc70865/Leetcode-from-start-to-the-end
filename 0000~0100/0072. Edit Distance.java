/* 
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; ++i) dp[i][0] = i;
        for (int i = 1; i <= n; ++i) dp[0][i] = i;
        // 插入/删除: dp[i][j-1]/dp[i-1][j]
        // 替换: dp[i-1][j-1]
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    if (dp[i][j] - dp[i - 1][j - 1] > 1)
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp[m][n];
    }
}

// dp[][] -> dp[]
// use memo store dp[i-1][j-1]

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n+1];
        for (int k = 1; k <= n; ++k) dp[k] = k;
        for (int i = 1; i <= m; ++i) {
            int memo = dp[0], tmp;
            dp[0] = i;
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    tmp = memo;
                    memo = dp[j];
                    dp[j] = tmp;
                } else {
                    tmp = memo;
                    memo = dp[j];
                    dp[j] = min(tmp, dp[j-1], dp[j]) + 1;
                }
            }
        }
        return dp[n];
    }
    
    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}


class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] memo = new int[m][n];
        return helper(word1, 0, word2, 0, memo);
    }
    int helper(String word1, int i, String word2, int j, int[][] memo) {
        if (i == word1.length()) return (int)word2.length() - j;
        if (j == word2.length()) return (int)word1.length() - i;
        if (memo[i][j] > 0) return memo[i][j];
        int res = 0;
        if (word1.charAt(i) == word2.charAt(j)) {
            return helper(word1, i + 1, word2, j + 1, memo);
        } else {
            int insertCnt = helper(word1, i, word2, j + 1, memo);
            int deleteCnt = helper(word1, i + 1, word2, j, memo);
            int replaceCnt = helper(word1, i + 1, word2, j + 1, memo);
            res = Math.min(insertCnt, Math.min(deleteCnt, replaceCnt)) + 1;
        }
        return memo[i][j] = res;
    }
}