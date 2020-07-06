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
        if (palindrome.length() == 1) {
            return "";
        }
        char[] arr = palindrome.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] == 'a') {
                continue;
            }
            arr[i] = 'a';
            return new String(arr);
        }
        arr[arr.length-1] = 'b';
        return new String(arr);
    }
}