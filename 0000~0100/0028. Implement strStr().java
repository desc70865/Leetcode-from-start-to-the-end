/* 
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */

class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}

// KMP

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() < 1) return 0;
        if (haystack == null || haystack.length() < needle.length()) return -1;
        return search(haystack.toCharArray(), needle.toCharArray());
    }
    
    public int search(char[] str, char[] model) {
        int N = str.length, M = model.length;
        int[] next = getNext(model);
        int i = 0, j = 0;
        while (i < N && j < M) {
            if (str[i] == model[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == M ? i - j : -1;
    }
    
    public int[] getNext(char[] chs) {
        int N = chs.length;
        int[] next = new int[N];
        next[0] = -1;
        for (int l = 0, r = 2; r < N;) {
            if (chs[l] == chs[r - 1]) next[r++] = ++l;
            else if (l > 0) l = next[l];
            else next[r++] = 0;
        }
        return next;
    }
}

// BM bad word ?

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() < 1) return 0;
        if (haystack == null || haystack.length() < needle.length()) return -1;
        return BM(haystack.toCharArray(), needle.toCharArray());
    }

    private int BM(char[] chs, char[] mod) {
        int N = mod.length;
        for (int l = N - 1, r = N - 1; l < chs.length; r = N - 1) {
            while (l >= 0 && r >= 0 && chs[l] == mod[r]) {
                l--;
                r--;
            }
            if (r == -1) {
                return l + 1;
            }
            l += (N - 1) - getMove(mod, chs[l], r);
        }
        return -1;
    }
    
    private int getMove(char[] mod, char target, int r) {
        for (int i = r; i >= 0; i--) {
            if (target == mod[i]) {
                return i;
            }
        }
        return -1;
    }
}