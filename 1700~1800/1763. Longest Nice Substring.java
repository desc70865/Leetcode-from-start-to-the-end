/* 
A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

 

Example 1:

Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.
Example 2:

Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
Example 3:

Input: s = "c"
Output: ""
Explanation: There are no nice substrings.
Example 4:

Input: s = "dDzeE"
Output: "dD"
Explanation: Both "dD" and "eE" are the longest nice substrings.
As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 

Constraints:

1 <= s.length <= 100
s consists of uppercase and lowercase English letters.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/longest-nice-substring/solution/java-2ms-by-keylol-d89a/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public String longestNiceSubstring(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int l = 0, r = -1;
        for (int i = 0; i < len - (r - l + 1); i++) {
            int a = 0, A = 0;
            for (int j = i; j < len; j++) {
                if (chs[j] >= 'a') {
                    a |= (1 << chs[j] - 'a');
                } else {
                    A |= (1 << chs[j] - 'A');
                }
                if (a == A && j - i > r - l) {
                    l = i;
                    r = j;
                }
            }
        }
        return s.substring(l, r + 1);
    }
}