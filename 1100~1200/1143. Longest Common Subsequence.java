/* 
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.
 */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();
        int m = a.length;
        int n = b.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            for (int j = 1; j <= n; j++) {
                int cur = dp[j];
                if (a[i - 1] == b[j - 1]) dp[j] = prev + 1;
                else dp[j] = Math.max(dp[j], dp[j - 1]);
                prev = cur;
            }
        }
        return dp[n];
    }
}



class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int m = s1.length;
        int n = s2.length;
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i - 1] == s2[j - 1]) lcs[i][j] = 1 + lcs[i - 1][j - 1];
                else lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
            }
        }
        return lcs[m][n];
    }
}