/* 
Given a string s containing only lower case English letters and the '?' character, convert all the '?' characters into lower case letters such that the final string does not contain any consecutive repeating characters. You cannot modify the non '?' characters.

It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.

Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution, return any of them. It can be shown that an answer is always possible with the given constraints.

 

Example 1:

Input: s = "?zs"
Output: "azs"
Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid. Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
Example 2:

Input: s = "ubv?w"
Output: "ubvaw"
Explanation: There are 24 solutions for this problem. Only "v" and "w" are invalid modifications as the strings will consist of consecutive repeating characters in "ubvvw" and "ubvww".
Example 3:

Input: s = "j?qg??b"
Output: "jaqgacb"
Example 4:

Input: s = "??yw?ipkj?"
Output: "acywaipkja"
 

Constraints:

1 <= s.length <= 100

s contains only lower case English letters and '?'.
 */

class Solution {
    public String modifyString(String s) {
        char[] res = s.toCharArray();
        for (int i = 0; i < res.length; i++) {
            if (res[i] != '?') continue;
            if (i > 0) res[i] = next(res[i - 1]);
            else res[i] = 'a';
            if (i < res.length - 1) {
                if (res[i] != res[i + 1]) continue;
                else res[i] = next(res[i]);
            }
        }
        return new String(res);
    }
    
    private char next(char c) {
        return c == 'z' ? 'a' : (char) (c + 1);
    }
}



class Solution {
    public String modifyString(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) {
            if (str[i] == '?') {
                if (i == 0) str[i] = 'a';
                else str[i] = next(str[i - 1]);
            } else if (i > 0 && str[i] == str[i - 1]) {
                str[i - 1] = next(str[i - 1]);
            }
        }
        return new String(str);
    }
    
    private char next(char c) {
        return c == 'z' ? 'a' : (char) (c + 1);
    }
}