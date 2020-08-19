/* 
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
 */

class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 1;
        
        for (int i = 0; i < s.length(); i++) {
            extendSubstring(s, i, i);
            extendSubstring(s, i, i+1);
        }
        return count;
    }
    
    private void extendSubstring(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l--) == s.charAt(r++)) count++;
    }
}

// 利用回文特性中心扩展

class Solution {
    int res = 0;
    public int countSubstrings(String s) {
        char[] str = s.toCharArray();
        int len = str.length;
        for (int i = 0; i < len; i++) {
            helper(str, len, i, i);
            helper(str, len, i, i+1);
        }
        return res;
    }

    private void helper(char[] str, int len, int j, int k) {
        while (j >= 0 && k < len && str[j--] == str[k++]) res++;
    }
}