/* 
Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It is guaranteed the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

0 <= s.length, t.length <= 1000
s and t consist of English letters.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences/solution/wu-zi-tian-shu-by-keylol-uso7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int numDistinct(String s, String t) {
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int len1 = chs.length, len2 = cht.length;
        int[][] dp = new int[len2 + 1][len1 + 1];
        Arrays.fill(dp[0], 1);
        for (int j = 1; j <= len2; j++) {
            for (int i = 1; i <= len1; i++) {
                if (chs[i - 1] == cht[j - 1]) dp[j][i] = dp[j][i - 1] + dp[j - 1][i - 1];
                else dp[j][i] = dp[j][i - 1];
            }
        }
        return dp[len2][len1];
    }
}



class Solution {
    Integer[][] memo;

    public int numDistinct(String s, String t) {
        memo = new Integer[s.length()][t.length()];
        return dfs(s.toCharArray(), 0, t.toCharArray(), 0);
    }
    
    private int dfs(char[] chs, int i, char[] cht, int j) {
        if (j == cht.length) return 1;
        if (i == chs.length) return 0;
        if (memo[i][j] != null) return memo[i][j];
        if (chs[i] != cht[j]) return memo[i][j] = dfs(chs, i + 1, cht, j);
        else return memo[i][j] = dfs(chs, i + 1, cht, j) + dfs(chs, i + 1, cht, j + 1);
    }
}

// core: dp[i][j][k] 的顺序

class Solution {
    public int numDistinct(String s, String t) {
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int len = cht.length;
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 0; i < chs.length; i++) {
            for (int j = len - 1; j >= 0; j--) {
                if (chs[i] == cht[j]) {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[len];
    }
}



class Solution {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;
        int len = t.length();
        int[] prev = new int[len];
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int idx = 0;
        for (char c: t.toCharArray()) {
            prev[idx] = map[c];
            map[c] = idx++;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (char c: s.toCharArray()) {
            for (int j = map[c]; j >= 0; j = prev[j]) {
                dp[j + 1] += dp[j];
            }
        }
        return dp[len];
    }
}