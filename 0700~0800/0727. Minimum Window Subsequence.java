/* 
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
 */

class Solution {
    public String minWindow(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int n = s.length;
        int m = t.length;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[][] next = new int[n][26];
        for (int i = n - 1; i >= 0; --i) {
            last[s[i] - 'a'] = i;
            System.arraycopy(last, 0, next[i], 0, 26);
        }
        List<int[]> pair = new ArrayList<>();
        for (int i = 0; i + m <= n; ++i) {
            if (s[i] == t[0]) {
                pair.add(new int[] {i, i});
            }
        }
        for (int j = 1; j < m; ++j) {
            for (int[] e: pair) {
                if (e[1] >= n - 1 || (e[1] = next[e[1] + 1][t[j] - 'a']) < 0) {
                    e[0] = -1;
                    break;
                }
            }
        }
        int[] ans = new int[] {-1, n};
        for (int[] e: pair) {
            if (e[0] == -1) {
                break;
            }
            if (e[1] - e[0] < ans[1] - ans[0]) {
                ans = e;
            }
        }
        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }
}



class Solution {
    public String minWindow(String S, String T) {
        if (S == null || S.length() == 0 || T == null || T.length() == 0 || S.length() < T.length()) {
            return "";
        }
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int m = s.length;
        int n = t.length;
        int left = -1, rigth = m;
        for (int i = 0, j = 0, end = 0; i < m; ++i) {
            for (j = 0; i < m && j < n;) {
                if (s[i++] == t[j]) {
                    ++j;
                }
            }
            if (j < n) {
                break;
            }
            end = i - 1;
            for (i = i - 1, j = j - 1; i >= 0 && j >= 0;) {
                if (s[i--] == t[j]) {
                    --j;
                }
            }
            ++i;
            if (end - i < rigth - left) {
                left = i;
                rigth = end;
            }
        }
        return rigth == m ? "" : S.substring(left, rigth + 1);
    }
}