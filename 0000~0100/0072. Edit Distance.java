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
        return helper(word1.toCharArray(), word2.toCharArray());
    }

    private int helper(char[] s1, char[] s2) {
        int m = s1.length, n = s2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[m][i] = dp[m][i + 1] + 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + min(dp[i + 1][j], dp[i][j + 1], dp[i + 1][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}



class Solution {
    public int minDistance(String word1, String word2) {
        return helper(word1.toCharArray(), word2.toCharArray());
    }

    private int helper(char[] s1, char[] s2) {
        int m = s1.length, n = s2.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            int pre = dp[n];
            dp[n]++;
            for (int j = n - 1; j >= 0; j--) {
                int tmp = dp[j];
                if (s1[i] == s2[j]) dp[j] = pre;
                else dp[j] = min(dp[j], dp[j + 1], pre) + 1;
                pre = tmp;
            }
        }
        return dp[0];
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}