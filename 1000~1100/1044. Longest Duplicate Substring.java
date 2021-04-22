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

// RadixSort -> SuffixArray ?
// 基数排序，后缀数组
// 1698. Number of Distinct Substrings in a String.java
// SA-IS

class Solution {
    public String longestDupSubstring(String s) {
        SuffixArray sa = new SuffixArray(s);
        int maxPos = -1, maxLen = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (sa.ht[i] > maxLen) {
                maxLen = sa.ht[i];
                maxPos = sa.sa[i];
            }
        }
        return maxPos == -1 ? "" : s.substring(maxPos, maxPos + maxLen);
    }
}

class SuffixArray {
    int m, n;
    char[] s;
    int[] sa, rk, ht, c, x, y;

    public SuffixArray(String str) {
        this.s = str.toCharArray();
        this.n = s.length + 1;
        this.m = Math.max(123, n);
        this.sa = new int[n];
        this.rk = new int[n];
        this.ht = new int[n];
        this.c = new int[m];
        this.x = new int[n];
        this.y = new int[n];
        getSA();
        getHT();
    }

    public void getSA() {
        for (int i = 0; i < n - 1; i++) c[rk[i] = s[i]]++;
        for (int i = 1; i < m; i++) c[i] += c[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[c[rk[i]]--] = i;
        for (int k = 1; k < n; k <<= 1) {
            for (int i = n - k; i < n; i++) y[i - n + k] = i;
            int j = 0;
            for (int i = 0; i < n; i++) if (sa[i] >= k) y[k + j++] = sa[i] - k;
            for (int i = 0; i < m; i++) c[i] = 0;
            for (int i = 0; i < n; i++) c[rk[y[i]]]++;
            for (int i = 1; i < m; i++) c[i] += c[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--c[rk[y[i]]]] = y[i];
            int p = 0;
            y[sa[0]] = p++;
            for (int i = 1; i < n; i++) y[sa[i]] = rk[sa[i]] == rk[sa[i - 1]] && rk[sa[i] + k] == rk[sa[i - 1] + k] ? p - 1 : p++;
            m = p;
            x = rk;
            rk = y;
            y = x;
            if (m == n) break;
        }
    }

    public void getHT() {
        for (int i = 0, k = 0; i < n - 1; i++) {
            if (k > 0) k--;
            int j = sa[rk[i] - 1];
            while (i + k < n - 1 && j + k < n - 1 && s[i + k] == s[j + k]) k++;
            ht[rk[i]] = k;
        }
    }
}