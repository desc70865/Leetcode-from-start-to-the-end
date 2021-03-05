/* 
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:
=======

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
 */
 
class Solution {
    /* 
    Manacher's Algorithm
    C 前置最长子串的对称中心
    R C的右边界
    P[i] 第 i 个节点的回文串长度
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        char[] T = preProcess(s.toCharArray());
        // System.out.println(Arrays.toString(T));
        int n = T.length;
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i;
            // 对称
            if (R > i) P[i] = Math.min(R - i, P[mirror]);
            else P[i] = 0;
            while (T[i + 1 + P[i]] == T[i - 1 - P[i]]) P[i]++;
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }
        // System.out.println(Arrays.toString(P));
        int cur = 1;
        for (int i = 2; i < n - 1; i++) {
            if (P[i] > P[cur]) cur = i;
        }
        // T -> s
        // s[i / 2] = T[i + 1]
        // L = cur - P[cur] + 1
        // R = cur + P[cur] - 1
        int start = (cur - P[cur]) / 2;
        return s.substring(start, start + P[cur]);
    }
    
    // append ^ # $
    public char[] preProcess(char[] s) {
        int N = s.length;
        char[] T = new char[N * 2 + 3];
        T[0] = '^';
        for (int i = 1; i < N * 2; i += 2) {
            T[i] = '#';
            // s -> T
            T[i + 1] = s[i / 2];
        }
        T[N * 2 + 1] = '#';
        T[N * 2 + 2] = '$';
        return T;
    }
}



class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        char[] chs = s.toCharArray();
        int L = -1, R = 0;
        int mid = 0;
        while (mid < len) {
            int l = mid, r = mid;
            while (r < len - 1 && chs[r] == chs[r + 1]) r++;
            mid = r + 1;
            while (0 <= l && r < len && chs[l] == chs[r]) {
                l--;
                r++;
            }
            if (r - l > R - L) {
                L = l;
                R = r;
            }
        }
        return s.substring(L + 1, R);
    }
}