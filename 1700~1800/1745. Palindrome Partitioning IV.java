/* 
Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.

 

Example 1:

Input: s = "abcbdd"
Output: true
Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
Example 2:

Input: s = "bcbddxy"
Output: false
Explanation: s cannot be split into 3 palindromes.
 

Constraints:

3 <= s.length <= 2000
s​​​​​​ consists only of lowercase English letters.
 */

class Solution {
    public boolean checkPartitioning(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int L = 1; L <= len - 2; L++) {
            for (int l = 0; l + L <= len; l++) {
                int r = l + L - 1;
                if (L == 1) dp[l][r] = true;
                else if (L == 2) dp[l][r] = s.charAt(l) == s.charAt(r);
                else dp[l][r] = s.charAt(l) == s.charAt(r) && dp[l + 1][r - 1];
            }
        }
        for (int l = 1; l < len; l++) {
            for (int r = l; r + 1 < len; r++) {
                if (dp[0][l - 1] && dp[l][r] && dp[r + 1][len - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}