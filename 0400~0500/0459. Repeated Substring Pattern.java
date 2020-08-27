/* 
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }
}



class Solution {
    public boolean repeatedSubstringPattern(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        
        int[] lps = new int[len];
        lps[0] = 0;
        
        int i = 1;
        int j = 0;
        while (i < len) {
            if (chars[i] == chars[j]) lps[i++] = ++j;
            else if (j == 0) lps[i++] = 0;
            else j = lps[j - 1];
        }
        
        int diff = len - lps[len - 1];
        return diff < len && len % diff == 0;
    }
}