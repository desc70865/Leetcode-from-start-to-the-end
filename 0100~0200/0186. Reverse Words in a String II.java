/* 
Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?
 */

class Solution {
    public void reverseWords(char[] s) {
        int l = 0, r = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, l, r - 1);
                l = i + 1;
                r = i + 1;
            } else {
                r++;
            }
        }
        reverse(s, l, r - 1);
        reverse(s, 0, s.length - 1);
    }

    private void reverse(char[] s, int l, int r) {
        if (l == r) {
            return;
        }
        while (l < r) {
            swap(s, l++, r--);
        }
    }

    private void swap(char[] s, int l, int r) {
        char t = s[l];
        s[l] = s[r];
        s[r] = t;
    }
}