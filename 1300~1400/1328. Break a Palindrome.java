/* 
Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.

After doing so, return the final string.  If there is no way to do so, return the empty string.

 

Example 1:

Input: palindrome = "abccba"
Output: "aaccba"
Example 2:

Input: palindrome = "a"
Output: ""
 

Constraints:

1 <= palindrome.length <= 1000
palindrome consists of only lowercase English letters.
 */

class Solution {
    public String breakPalindrome(String palindrome) {
        char[] str = palindrome.toCharArray();
        int N = str.length;
        if (N == 1) return "";
        int len = N / 2;
        for (int i = 0; i < len; i++) {
            if (str[i] != 'a') {
                str[i] = 'a';
                return new String(str);
            }
        }
        str[N - 1] = 'b';
        return new String(str);
    }
}