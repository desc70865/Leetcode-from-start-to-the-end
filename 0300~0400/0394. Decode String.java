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
    private int index = 0;
    public String decodeString(String s) {
        return dfs(s, 0);
    }
    
    private String dfs(String s, int cnt) {
        StringBuilder sb = new StringBuilder();
        char t;
        int N = 0;
        while (index < s.length()) {
            t = s.charAt(index++);
            if (t == '[') {
                sb.append(dfs(s, N));
                N = 0;
            } else if (t == ']') {
                break;
            } else if (Character.isDigit(t)) {
                N = 10 * N + (t - '0');
            } else {
                sb.append(t);
            }
        }
        if (cnt == 0) {
            return sb.toString();
        }
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            tmp.append(sb);
        }
        return tmp.toString();
    }
}



class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (Character.isAlphabetic(s.charAt(i))) {
                res.append(s.charAt(i++));
                continue;
            }
            
            int num = 0;
            while (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i++) - '0');
            }
            
            int j = i, count = 1;
            while (count > 0) {
                j++;
                if (s.charAt(j) == '[') {
                    count++;
                } else if (s.charAt(j) == ']') {
                    count--;
                }
            }
            
            String temp = decodeString(s.substring(i+1, j));
            for (int t = 0; t < num; t++) {
                res.append(temp);
            }
            i = j+1;
        }
        return res.toString();
    }
}