/* 
You are given two strings a and b of the same length. Choose an index and split both strings at the same index, splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings: bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.

When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty. For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.

Return true if it is possible to form a palindrome string, otherwise return false.

Notice that x + y denotes the concatenation of strings x and y.

 

Example 1:

Input: a = "x", b = "y"
Output: true
Explaination: If either a or b are palindromes the answer is true since you can split in the following way:
aprefix = "", asuffix = "x"
bprefix = "", bsuffix = "y"
Then, aprefix + bsuffix = "" + "y" = "y", which is a palindrome.
Example 2:

Input: a = "abdef", b = "fecab"
Output: false
Example 3:

Input: a = "ulacfd", b = "jizalu"
Output: true
Explaination: Split them at index 3:
aprefix = "ula", asuffix = "cfd"
bprefix = "jiz", bsuffix = "alu"
Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 

Constraints:

1 <= a.length, b.length <= 105
a.length == b.length
a and b consist of lowercase English letters
 */

class Solution {
    int N;
    public boolean checkPalindromeFormation(String a, String b) {
        N = a.length();
        if (N <= 1) return true;
        char[] s1 = a.toCharArray(), s2 = b.toCharArray();
        return (helper(s1, s2) || helper(s2, s1));
    }
    
    private boolean helper(char[] s1, char[] s2) {
        int l = 0, r = N - 1;
        while (l < r) {
            if (s1[l] != s2[r]) break;
            l++;
            r--;
        }
        return (check(s1, l, r) || check(s2, l, r));
    }
    
    private boolean check(char[] s, int l, int r) {
        if (l >= r) return true;
        while (l <= r) {
            if (s[l] != s[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}