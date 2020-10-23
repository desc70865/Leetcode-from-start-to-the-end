/* 
Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.
Example 2:

Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".
Example 3:

Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.
Example 4:

Input: s = "cabbac"
Output: 4
Explanation: The optimal substring here is "abba". Other non-optimal substrings include "bb" and "".
 

Constraints:

1 <= s.length <= 300
s contains only lowercase English letters.
 */

class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] f = new int[26];
        Arrays.fill(f, -1);
        int[] e = new int[26];
        Arrays.fill(e, -1);
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            int idx = str[i] - 97;
            if (f[idx] == -1) f[idx] = i;
            else e[idx] = i;
        }
        int max = -1;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, e[i] - f[i] - 1);
        }
        return max;
    }
}