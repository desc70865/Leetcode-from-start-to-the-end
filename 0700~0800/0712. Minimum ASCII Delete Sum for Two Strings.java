/* 
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/solution/jiang-wei-da-ji-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        return helper(s1.toCharArray(), s2.toCharArray());
    }

    private int helper(char[] s1, char[] s2) {
        int m = s1.length, n = s2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + s1[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[m][i] = dp[m][i + 1] + s2[i];
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1[i], dp[i][j + 1] + s2[j]);
                }
            }
        }
        return dp[0][0];
    }
}



class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        return helper(s1.toCharArray(), s2.toCharArray());
    }

    private int helper(char[] s1, char[] s2) {
        int m = s1.length, n = s2.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + s2[i];
        }
        for (int i = m - 1; i >= 0; i--) {
            int pre = dp[n];
            dp[n] += s1[i];
            for (int j = n - 1; j >= 0; j--) {
                int tmp = dp[j];
                if (s1[i] == s2[j]) dp[j] = pre;
                else dp[j] = Math.min(dp[j] + s1[i], dp[j + 1] + s2[j]);
                pre = tmp;
            }
        }
        return dp[0];
    }
}