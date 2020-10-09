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
        if (needle.length() == 0)
            return 0;
        for (int i = 0; ; ++i) {
            for (int j = 0; ; ++j) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}

// charAt(), length()...

class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}



class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() < 1) return 0;
        if (haystack == null || haystack.length() < needle.length()) return -1;
        return search(haystack.toCharArray(), needle.toCharArray());
    }
    
    public int search(char[] str, char[] model) {
        int N = str.length, M = model.length;
        int[] next = getNext(model);
        // System.out.println(Arrays.toString(next));
        int i = 0, j = 0;
        while (i < N && j < M) {
            if (str[i] == model[j]) {
                i++;
                j++;
            } else if (next[j] == -1) i++;
            else j = next[j];
        }
        return j == M ? i - j : -1;
    }
    
    public int[] getNext(char[] m) {
        int N = m.length;
        if (N == 1) return new int[] {-1};
        int[] next = new int[N];
        next[0] = -1;
        next[1] = 0;
        int cur = 0;
        int idx = 2;
        while (idx < N) {
            if (m[idx - 1] == m[cur]) next[idx++] = ++cur;
            else if (cur > 0) cur = next[cur];
            else  next[idx++] = 0;
        }
        return next;
    }
}