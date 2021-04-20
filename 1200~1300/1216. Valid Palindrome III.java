/* 
Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
Example 2:

Input: s = "abbababa", k = 1
Output: true
 

Constraints:

1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
 */

class Solution {
    public boolean isValidPalindrome(String s, int k) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < len; i++) {
            dp[i - 1][i] = chs[i - 1] == chs[i] ? 2 : 1;
        }
        for (int d = 2; d < len; d++) {
            for (int l = 0, r = d; r < len; l++, r++) {
                if (chs[l] == chs[r]) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                }
            }
        }
        return k >= len - dp[0][len - 1];
    }
}



class Solution {
    public boolean isValidPalindrome(String s, int k) {
        return k >= s.length() - longestPalindromeSubseq(s);
    }

    public int longestPalindromeSubseq(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int l = len - 1; l >= 0; l--) {
            dp[l][l] = 1;
            for (int r = l + 1; r < len; r++) {
                if (chs[l] == chs[r]) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                }
            }
        }
        return dp[0][len - 1];
    }
}



class Solution {
    public boolean isValidPalindrome(String s, int k) {
        return k >= s.length() - longestPalindromeSubseq(s);
    }

    public int longestPalindromeSubseq(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] dp = new int[len];
        for (int l = len - 1; l >= 0; l--) {
            dp[l] = 1;
            for (int r = l + 1, prev = 0; r < len; r++) {
                int cur = dp[r];
                if (chs[l] == chs[r]) {
                    dp[r] = prev + 2;
                } else {
                    dp[r] = Math.max(dp[r - 1], dp[r]);
                }
                prev = cur;
            }
        }
        return dp[len - 1];
    }
}