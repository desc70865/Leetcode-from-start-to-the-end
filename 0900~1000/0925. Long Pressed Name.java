/* 
Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

 

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
 

Constraints:

1 <= name.length <= 1000
1 <= typed.length <= 1000
The characters of name and typed are lowercase letters.
 */

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int N1 = name.length(), N2 = typed.length();
        if (N1 > N2) return false;
        char[] s1 = name.toCharArray();
        char[] s2 = typed.toCharArray();
        int i = 0, j = 0;
        while (i < N1 && j < N2) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                if (j > 0 && s2[j] == s2[j - 1]) j++;
                else return false;
            }
        }
        if (i != N1) return false;
        for (int k = j; k < N2; k++) {
            if (s2[k] != s2[j - 1]) return false;
        }
        return true;
    }
}