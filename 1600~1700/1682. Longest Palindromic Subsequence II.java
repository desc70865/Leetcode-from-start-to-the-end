/* 
A subsequence of a string s is considered a good palindromic subsequence if:

It is a subsequence of s.
It is a palindrome (has the same value if reversed).
It has an even length.
No two consecutive characters are equal, except the two middle ones.
For example, if s = "abcabcabb", then "abba" is considered a good palindromic subsequence, while "bcb" (not even length) and "bbbb" (has equal consecutive characters) are not.

Given a string s, return the length of the longest good palindromic subsequence in s.

 

Example 1:

Input: s = "bbabab"
Output: 4
Explanation: The longest good palindromic subsequence of s is "baab".
Example 2:

Input: s = "dcbccacdb"
Output: 4
Explanation: The longest good palindromic subsequence of s is "dccd".
 

Constraints:

1 <= s.length <= 250
s consists of lowercase English letters.
 */

class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[][] dp = new int[n][n];
        char[][] pre = new char[n][n];
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                if (chs[i] == chs[j] && chs[i] != pre[i + 1][j - 1]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    pre[i][j] = chs[i];
                } else {
                    if (dp[i + 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i + 1][j];
                        pre[i][j] = pre[i + 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        pre[i][j] = pre[i][j - 1];
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}