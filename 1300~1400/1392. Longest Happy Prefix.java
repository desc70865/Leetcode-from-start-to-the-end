/* 
A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).

Given a string s. Return the longest happy prefix of s .

Return an empty string if no such prefix exists.

 

Example 1:

Input: s = "level"
Output: "l"
Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
Example 2:

Input: s = "ababab"
Output: "abab"
Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
Example 3:

Input: s = "leetcodeleet"
Output: "leet"
Example 4:

Input: s = "a"
Output: ""
 

Constraints:

1 <= s.length <= 10^5
s contains only lowercase English letters.
 */

class Solution {
    public String longestPrefix(String s) {
        if (s == null || s.length() < 2) return "";
        char[] chs = s.toCharArray();
        int len = s.length();
        int[] next = new int[len + 1];
        next[0] = -1;
        // next[1] = 0;
        for (int l = 0, r = 1; r < len;) {
            if (chs[l] == chs[r]) {
                next[++r] = ++l;
            } else if (l > 0) {
                l = next[l];
            } else {
                r++;
            }
        }
        return s.substring(0, next[len]);
    }
}