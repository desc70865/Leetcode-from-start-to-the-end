/* 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else if (stack.isEmpty() || Math.abs(c - stack.pop()) > 2) return false;
        }
        return stack.isEmpty();
    }
}



class Solution {
    public boolean isValid(String s) {
        int max = s.length() / 2 + 1;
        char[] stack = new char[max];
        int p = 0;
        for (char c: s.toCharArray()) {
            if (p >= max) return false;
            if (c == '(' || c == '[' || c == '{') stack[p++] = c;
            else if (p <= 0 || Math.abs(c - stack[--p]) > 2) return false;
        }
        return p == 0;
    }
}