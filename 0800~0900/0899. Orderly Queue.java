/* 
A string S of lowercase letters is given.  Then, we may make any number of moves.

In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

Return the lexicographically smallest string we could have after any number of moves.

 

Example 1:

Input: S = "cba", K = 1
Output: "acb"
Explanation: 
In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
Example 2:

Input: S = "baaca", K = 3
Output: "aaabc"
Explanation: 
In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
 

Note:

1 <= K <= S.length <= 1000
S consists of lowercase letters only.
 */

class Solution {
    public String orderlyQueue(String S, int K) {
        char[] s = S.toCharArray();
        if (K > 1) {
            Arrays.sort(s);
            return new String(s);
        }
        int x = find(s);
        return S.substring(x) + S.substring(0, x);
    }

    private int find(char[] s) {
        int len = s.length;
        for (int l = 0, r = 1, k = 0; l <= len && r <= len; ) {
            if (l == len || r == len) {
                return Math.min(l, r);
            }
            k = 0;
            while (s[l + k] == s[r + k] && k < len) k++;
            if (k == len) return l;
            if (s[l + k] > s[r + k]) {
                if (l + k + 1 > r) l += k + 1;
                else l = r + 1;
            } else if (r + k + 1 > l) r += k + 1;
            else r = l + 1;
        }
        return -1;
    }
}

// 循环同构字符串 的 最小表示
// https://oi-wiki.org/string/minimal-string/

class Solution {
    public String orderlyQueue(String S, int K) {
        char[] s = S.toCharArray();
        if (K > 1) {
            Arrays.sort(s);
            return new String(s);
        }
        int x = find(s);
        return S.substring(x) + S.substring(0, x);
    }

    private int find(char[] s) {
        int n = s.length;
        int l = 0, r = 1, k = 0;
        while (k < n && l < n && k < n) {
            if (s[(l + k) % n] == s[(r + k) % n]) {
                k++;
            } else {
                if (s[(l + k) % n] > s[(r + k) % n]) {
                    l++;
                } else {
                    r++;
                }
                k = 0;
                if (l == r) r++;
            }
        }
        return Math.min(l, r);
    }
}