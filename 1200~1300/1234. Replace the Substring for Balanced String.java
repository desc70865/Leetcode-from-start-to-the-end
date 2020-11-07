/* 
You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.

A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.

Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.

Return 0 if the string is already balanced.

 

Example 1:

Input: s = "QWER"
Output: 0
Explanation: s is already balanced.
Example 2:

Input: s = "QQWE"
Output: 1
Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
Example 3:

Input: s = "QQQW"
Output: 2
Explanation: We can replace the first "QQ" to "ER". 
Example 4:

Input: s = "QQQQ"
Output: 3
Explanation: We can replace the last 3 'Q' to make s = "QWER".
 

Constraints:

1 <= s.length <= 10^5
s.length is a multiple of 4
s contains only 'Q', 'W', 'E' and 'R'.
 */

class Solution {
    char[] qwer = new char[] {'Q', 'W', 'E', 'R'};
    int n;

    public int balancedString(String s) {
        int[] cnt = new int[128];
        for (char c: s.toCharArray()) cnt[c]++;
        int avg = s.length() / 4;
        for (char c: qwer) {
            cnt[c] = Math.max(0, cnt[c] - avg);
            n += cnt[c];
        }
        return minWindow(s, cnt);
    }

    public int minWindow(String s, int[] map) {
        if (n == 0) return 0;
        int m = s.length();
        char[] chs = s.toCharArray();
        int left = 0, cnt = 0, minLeft = -1, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            if (--map[chs[i]] >= 0) cnt++;
            while (cnt == n) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    minLeft = left;
                    if (minLen == n) return minLen;
                }
                if (++map[chs[left]] > 0) cnt--;
                left++;
            }
        }
        return minLen;
    }
}