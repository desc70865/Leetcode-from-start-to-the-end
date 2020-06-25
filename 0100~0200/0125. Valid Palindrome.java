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
    private char[] arr;
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        arr = s.toCharArray();
        int i = 0, j = s.length()-1;
        while (i <= j) {
            while (!isValid(i) && i < j) i++;
            while (!isValid(j) && i < j) j--;
            if (arr[i++] != arr[j--]) return false;
        }
        return true;
    }
    
    private boolean isValid(int c) {
        if ('A' <= arr[c] && arr[c] <= 'Z') arr[c] += 32;
        return 'a' <= arr[c] && arr[c] <= 'z' || '0' <= arr[c] && arr[c] <= '9';
    }
}