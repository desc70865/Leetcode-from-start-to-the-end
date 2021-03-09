/* 
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lower-case English letters only.
 */

class Solution {
    public int minCut(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        boolean[][] dp = new boolean[len][len];
        for (int r = 0; r < len; r++) {
            for (int l = 0; l < r; l++) {
                if (chs[l] == chs[r] && (r == l + 1 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                }
            }
            dp[r][r] = true;
        }
        int[] split = new int[len];
        for (int r = 0; r < len; r++) {
            if (dp[0][r]) continue;
            split[r] = Integer.MAX_VALUE;
            for (int l = 0; l < r; l++) {
                if (dp[l + 1][r]) {
                    split[r] = Math.min(split[r], split[l] + 1);
                }
            }
        }
        return split[len - 1];
    }
}



class Solution {
    public int minCut(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        for (int m = 0; m < len; m++) {
            expand(chs, dp, m, m);
            expand(chs, dp, m, m + 1);
        }
        return dp[len - 1];
    }

    private void expand(char[] chs, int[] dp, int l, int r) {
        while (0 <= l && r < chs.length && chs[l] == chs[r]) {
            dp[r] = Math.min(dp[r++], l-- == 0 ? 0 : dp[l] + 1);
        }
    }
}