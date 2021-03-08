/* 
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
 */

class Solution {
    int idx = 0;

    public String decodeString(String s) {
        return decodeString(s.toCharArray(), 1);
    }

    private String decodeString(char[] chs, int multiple) {
        StringBuilder module = new StringBuilder();
        int nextMulti = 0;
        while (idx < chs.length) {
            char c = chs[idx++];
            if (c == '[') {
                module.append(decodeString(chs, nextMulti));
                nextMulti = 0;
            } else if (c == ']') {
                break;
            } else if (c <= '9') {
                nextMulti *= 10;
                nextMulti += c - '0';
            } else {
                module.append(c);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < multiple; i++) ans.append(module);
        return ans.toString();
    }
}