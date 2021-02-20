/* 
You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.

 

Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
Example 2:

Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
Example 3:

Input: s = "leetcode", k = 8
Output: 0
 

Constraints:

1 <= k <= s.length <= 100.
s only contains lowercase English letters.
 */

class Solution {
    public int palindromePartition(String s, int k) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int l = len - 2; l >= 0; l--) {
            for (int r = l + 1; r < len; r++) {
                dp[l][r] = dp[l + 1][r - 1] + (chs[l] == chs[r] ? 0 : 1);
            }
        }
        int[] dq = dp[0].clone();
        for (int t = 1; t < k; t++) {
            for (int r = len - 1; r > t; r--) {
                dq[r] = dq[r - 1];
                for (int l = r - 1; l >= t; l--) {
                    dq[r] = Math.min(dq[r], dq[l - 1] + dp[l][r]);
                }
            }
            dq[t] = 0;
        }
        return dq[len - 1];
    }
}