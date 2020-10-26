/* 
Given a string s, determine if it is valid.

A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:

Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
Return true if s is a valid string, otherwise, return false.

 

Example 1:

Input: s = "aabcbc"
Output: true
Explanation:
"" -> "abc" -> "aabcbc"
Thus, "aabcbc" is valid.
Example 2:

Input: s = "abcabcababcc"
Output: true
Explanation:
"" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
Thus, "abcabcababcc" is valid.
Example 3:

Input: s = "abccba"
Output: false
Explanation: It is impossible to get "abccba" using the operation.
Example 4:

Input: s = "cababc"
Output: false
Explanation: It is impossible to get "cababc" using the operation.
 

Constraints:

1 <= s.length <= 2 * 104
s consists of letters 'a', 'b', and 'c'
 */

class Solution {
    public boolean isValid(String s) {
        while (s.contains("abc")) {
            s = s.replace("abc", "");
        }
        return s.length() == 0;
    }
}



class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c: s.toCharArray()) {
            if (c != 'c') stack.push(c);
            else {
                if (stack.size() < 2) return false;
                if (stack.pop() != 'b' || stack.pop() != 'a') return false;
            }
        }
        return stack.isEmpty();
    }
}



class Solution {
    public boolean isValid(String s) {
        int[] map = new int[s.length()];
        int idx = 0;
        for (char c: s.toCharArray()) {
            if (c != 'c') map[idx++] = c;
            else {
                if (idx < 2) return false;
                if (map[--idx] != 'b' || map[--idx] != 'a') return false;
            }
        }
        return idx == 0;
    }
}