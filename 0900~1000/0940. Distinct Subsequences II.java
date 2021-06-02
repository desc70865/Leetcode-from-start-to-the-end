/* 
Given a string s, count the number of distinct, non-empty subsequences of s .

Since the result may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: s = "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 

 

Note:

s contains only lowercase letters.
1 <= s.length <= 2000
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int distinctSubseqII(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; ++i) {
            int c = t[i] - 'a';
            dp[i + 1] = dp[i] * 2 % MOD;
            if (last[c] >= 0) {
                dp[i + 1] = (dp[i + 1] + MOD - dp[last[c]]) % MOD;
            }
            last[c] = i;
        }
        return (dp[n] + MOD - 1) % MOD;
    }
}