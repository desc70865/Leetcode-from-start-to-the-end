/* 
Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.

 

Example 1:

Input: S = "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: S = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: S = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: S = "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.
 

Constraints:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500
 */

class Solution {
    public int longestRepeatingSubstring(String S) {
        int len = S.length();
        int[] bit = new int[len];
        for (int i = 0; i < len; i++) {
            bit[i] = S.charAt(i) - 'a';
        }
        int L = 1;
        int R = len - 1;
        while (L <= R) {
            int M = L + R >> 1;
            if (isRepeat(M, 26, 1 << 24, len, bit)) L = M + 1;
            else R = M - 1;
        }
        return L - 1;
    }

    private boolean isRepeat(int k, int base, long mod, int len, int[] bit) {
        long hash = 0;
        long mask = 1;
        for (int i = 0; i < k; i++) {
            hash = (hash * base + bit[i]) % mod;
            mask = (mask * base) % mod;
        }
        Set<Long> set = new HashSet<>(len - k + 1);
        set.add(hash);
        for (int i = 1; i < len - k + 1; i++) {
            hash = (hash * base - bit[i - 1] * mask) % mod + mod;
            hash = (hash + bit[i + k - 1]) % mod;
            if (! set.add(hash)) return true;
        }
        return false;
    }
}