/* 
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
 */

class Solution {
    String str;
    char[] chs;
    int len;

    public String parseTernary(String expression) {
        this.str = expression;
        this.chs = expression.toCharArray();
        this.len = chs.length;
        return helper(0, len);
    }

    private String helper(int l, int r) {
        int k = 0;
        for (int i = l + 1; i < r; i++) {
            if (chs[i] == '?') k++;
            else if (chs[i] == ':') k--;
            if (k == 0) {
                return chs[l] == 'T' ? helper(l + 2, i) : helper(i + 1, r);
            }
        }
        return str.substring(l, r);
    }
}