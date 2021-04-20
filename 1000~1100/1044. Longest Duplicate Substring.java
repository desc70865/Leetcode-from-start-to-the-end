/* 
Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.

Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".

 

Example 1:

Input: s = "banana"
Output: "ana"
Example 2:

Input: s = "abcd"
Output: ""
 

Constraints:

2 <= s.length <= 3 * 104
s consists of lowercase English letters.
 */

class Solution {
    static final int BASE = 26;
    static final long MOD = (1L << 35) - 1;

    public String longestDupSubstring(String s) {
        int p = -1, t = -1;
        int len = s.length();
        int[] chs = new int[len];
        for (int i = 0; i < len; i++) {
            chs[i] = s.charAt(i) - 97;
        }
        int l = 0, r = len - 1;
        while (l < r) {
            int m = l + r + 1 >> 1;
            if ((t = search(chs, m)) >= 0) {
                l = m;
                p = t;
            }
            else r = m - 1;
        }
        return p >= 0 ? s.substring(p, p + l) : "";
    }

    private int search(int[] chs, int len) {
        Set<Long> set = new HashSet<>();
        long power = 1;
        long hash = 0;
        for (int i = 0; i < len; i++) {
            power *= BASE;
            power %= MOD;
            hash *= BASE;
            hash += chs[i];
            hash %= MOD;
        }
        set.add(hash);
        for (int i = len; i < chs.length; i++) {
            hash *= BASE;
            hash -= chs[i - len] * power % MOD;
            hash += MOD + chs[i];
            hash %= MOD;
            if (! set.add(hash)) {
                return i - len + 1;
            }
        }
        return -1;
    }
}