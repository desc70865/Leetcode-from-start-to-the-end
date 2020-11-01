/* 
Given two strings s and t, find the number of ways you can choose a non-empty substring of s and replace a single character by a different character such that the resulting substring is a substring of t. In other words, find the number of substrings in s that differ from some substring in t by exactly one character.

For example, the underlined substrings in "computer" and "computation" only differ by the 'e'/'a', so this is a valid way.

Return the number of substrings that satisfy the condition above.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "aba", t = "baba"
Output: 6
Explanation: The following are the pairs of substrings from s and t that differ by exactly 1 character:
("aba", "baba")
("aba", "baba")
("aba", "baba")
("aba", "baba")
("aba", "baba")
("aba", "baba")
The underlined portions are the substrings that are chosen from s and t.
​​Example 2:
Input: s = "ab", t = "bb"
Output: 3
Explanation: The following are the pairs of substrings from s and t that differ by 1 character:
("ab", "bb")
("ab", "bb")
("ab", "bb")
​​​​The underlined portions are the substrings that are chosen from s and t.
Example 3:
Input: s = "a", t = "a"
Output: 0
Example 4:

Input: s = "abe", t = "bbc"
Output: 10
 

Constraints:

1 <= s.length, t.length <= 100
s and t consist of lowercase English letters only.
 */

class Solution {
    public int countSubstrings(String s, String t) {
        int sN = s.length();
        int tN = t.length();
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < sN; i++) {
            for (int j = i + 1; j <= sN; j++) {
                int len = j - i;
                String m = s.substring(i, j);
                if (map.containsKey(m)) {
                    res += map.get(m);
                    continue;
                }
                int cnt = 0;
                for (int k = 0; k <= tN - len; k++) {
                    if (check(m, t.substring(k, k + len))) {
                        cnt++;
                    }
                }
                map.put(m, cnt);
                res += cnt;
            }
        }
        return res;
    }
    
    private boolean check(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) cnt++;
        }
        return cnt == 1;
    }
}



class Solution {
    public int countSubstrings(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        // dp[i][j][0]表示s中以i结束的子串与t中以j结束的子串恰好只有一个字符不同的子字符串数目。
        // dp[i][j][1]表示s中以i结束的子串与t中以j结束的子串相同的数目。
        int[][][] dp = new int[slen][tlen][2];
        for (int i = 0; i < slen; i++) {
            if (s.charAt(i) != t.charAt(0)) {
                dp[i][0][0]++;
            } else {
                dp[i][0][1]++;
            }
        }
        for (int j = 1; j < tlen; j++) {
            if (s.charAt(0) != t.charAt(j)) {
                dp[0][j][0]++;
            } else {
                dp[0][j][1]++;
            }
        }
        
        for (int i = 1; i < slen; i++) {
            for (int j = 1; j < tlen; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    dp[i][j][0] = dp[i-1][j-1][1] + 1;
                } else {
                    dp[i][j][0] = dp[i-1][j-1][0];
                    dp[i][j][1] = dp[i-1][j-1][1] + 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < tlen; j++) {
                res += dp[i][j][0];
            }
        }
        return res;
    }
}