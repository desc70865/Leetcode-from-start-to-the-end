/* 
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int i = 0, j = 0, iStar = -1, jStar = -1, m = s.length(), n = p.length();
        while (i < m) {
            if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                ++i; ++j;
            } else if (j < n && p.charAt(j) == '*') { // 更新 * 标记
                iStar = i;
                jStar = j++;
            } else if (iStar >= 0) { // 从上一个 * 开始重新匹配
                i = ++iStar; // i 和 iStar 同时右移
                j = jStar + 1; // j 重置于 jStar 右侧
            } else return false;
        }
        while (j < n && p.charAt(j) == '*') ++j;
        return j == n;
    }
}

// 将字符及 ? 与 * 区分
// 在匹配到 * 时更改标记并储存
// 由于 * 可以匹配包含任意字符,因此需要在后续匹配失败后返回并用 * 重新延长匹配区间

class Solution {
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i-1) == '*') dp[0][i] = dp[0][i - 1];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // * 匹配一位 || * 匹配 null
                } else {
                    dp[i][j] = (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}

// laji...