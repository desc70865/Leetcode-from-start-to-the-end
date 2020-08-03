/* 
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */

class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        return helper(s.toCharArray(), 0, s.length() - 1, true);
    }
    
    private boolean helper(char[] arr, int i, int j, boolean flag) {
        while (i <= j) {
            if (arr[i++] != arr[j--]) {
                return flag ? helper(arr, i-1, j, false) || helper(arr, i, j+1, false) : false;
            }
        }
        return true;
    }
}