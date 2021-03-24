/* 
Given a string s, return the last substring of s in lexicographical order.

 

Example 1:

Input: s = "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: s = "leetcode"
Output: "tcode"
 

Constraints:

1 <= s.length <= 4 * 105
s contains only lowercase English letters.
 */

class Solution {
    public String lastSubstring(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int ans = len - 1;
        int max = 'a';
        for (int i = len - 1; i >= 0; i--) {
            if (chs[i] > max) {
                ans = i;
                max = chs[i];
            } else if (chs[i] == max) {
                if (i - 1 >= 0 && chs[i] == chs[i - 1]) continue;
                for (int curr = i, prev = ans; prev <= len; curr++, prev++) {
                    if (prev < len && chs[prev] == chs[curr]) continue;
                    if (prev == len || chs[prev] < chs[curr]) ans = i;
                    break;
                }
            }
        }
        return s.substring(ans);
    }
}



class Solution {
    public String lastSubstring(String s) {
        char[] chs = s.toCharArray();
        int ans = 0;
        for (int r = 1, k = 0; r + k < chs.length; ) {
            if (chs[ans + k] < chs[r + k]) {
                ans = r++;
                k = 0;
            } else if (chs[ans + k] == chs[r + k] && ans + k <= r) {
                k++;
            } else {
                r += k + 1;
                k = 0;
            }
        }
        return s.substring(ans);
    }
}