/* 
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

class Solution {
    public boolean isMatch(String text, String pattern) {
        char[] s = text.toCharArray();
        char[] t = pattern.toCharArray();
        int m = s.length;
        int n = t.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;
        for (int i = m; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                boolean f = i < m && (s[i] == t[j] || '.' == t[j]);
                if (j + 1 < n && t[j + 1] == '*') {
                    dp[i][j] = dp[i][j + 2] || f && dp[i + 1][j];
                } else {
                    dp[i][j] = f && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}