/* 
Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

 

Example 1:

Input: "ab-cd"
Output: "dc-ba"
Example 2:

Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"
 

Note:

S.length <= 100
33 <= S[i].ASCIIcode <= 122 
S doesn't contain \ or "
 */

class Solution {
    public String reverseOnlyLetters(String S) {
        char[] str = S.toCharArray();
        int l = 0, r = str.length - 1;
        while (l < r) {
            while (l < r && ! isC(str[l])) l++;
            while (l < r && ! isC(str[r])) r--;
            swap(str, l++, r--);
        }
        return new String(str);
    }

    private boolean isC(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }

    private void swap(char[] s, int i, int j) {
        if (i == j) return;
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}