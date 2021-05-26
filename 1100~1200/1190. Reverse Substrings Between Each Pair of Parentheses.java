/* 
You are given a string s that consists of lower case English letters and brackets. 

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
Example 4:

Input: s = "a(bcdefghijkl(mno)p)q"
Output: "apmnolkjihgfedcbq"
 

Constraints:

0 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It's guaranteed that all parentheses are balanced.
 */

class Solution {
    char[] t;
    int n;

    public String reverseParentheses(String s) {
        t = s.toCharArray();
        this.n = t.length;
        return reverseParentheses(0, n - 1).toString();
    }

    private StringBuilder reverseParentheses(int left, int right) {
        StringBuilder ans = new StringBuilder();
        for (int i = left; i <= right; ++i) {
            if (t[i] == '(') {
                int next = findNextBracket(i + 1);
                ans.append(reverseParentheses(i + 1, next - 1).reverse());
                i = next;
            } else {
                ans.append(t[i]);
            }
        }
        return ans;
    }

    private int findNextBracket(int idx) {
        for (int cnt = 1; idx < n && cnt > 0; ++idx) {
            if (t[idx] == '(') {
                ++cnt;
            } else if (t[idx] == ')') {
                --cnt;
            }
        }
        return idx - 1;
    }
}



class Solution {
    int idx = 0;
    int n;

    public String reverseParentheses(String s) {
        char[] t = s.toCharArray();
        this.n = t.length;
        return reverseParentheses(t).toString();
    }

    private StringBuilder reverseParentheses(char[] t) {
        StringBuilder sb = new StringBuilder();
        while (idx < n && t[idx] != ')') {
            if (t[idx] == '(') {
                ++idx;
                sb.append(reverseParentheses(t).reverse());
            } else {
                sb.append(t[idx++]);
            }
        }
        ++idx;
        return sb;
    }
}