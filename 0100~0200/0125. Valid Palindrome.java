/* 
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */

class Solution {
    private char[] chs;

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        chs = s.toCharArray();
        for (int l = 0, r = chs.length - 1; l <= r; ) {
            while (!isValid(l) && l < r) l++;
            while (!isValid(r) && l < r) r--;
            if (chs[l++] != chs[r--]) return false;
        }
        return true;
    }
    
    private boolean isValid(int c) {
        if ('A' <= chs[c] && chs[c] <= 'Z') chs[c] += 32;
        return 'a' <= chs[c] && chs[c] <= 'z' || '0' <= chs[c] && chs[c] <= '9';
    }
}