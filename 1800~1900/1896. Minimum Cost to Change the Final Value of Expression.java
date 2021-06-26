/* 
You are given a valid boolean expression as a string expression consisting of the characters '1','0','&' (bitwise AND operator),'|' (bitwise OR operator),'(', and ')'.

For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))", and "1|(0&(1))" are valid expressions.
Return the minimum cost to change the final value of the expression.

For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations so that the new expression evaluates to 0.
The cost of changing the final value of an expression is the number of operations performed on the expression. The types of operations are described as follows:

Turn a '1' into a '0'.
Turn a '0' into a '1'.
Turn a '&' into a '|'.
Turn a '|' into a '&'.
Note: '&' does not take precedence over '|' in the order of calculation. Evaluate parentheses first, then in left-to-right order.

 

Example 1:

Input: expression = "1&(0|1)"
Output: 1
Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a '&' using 1 operation.
The new expression evaluates to 0. 
Example 2:

Input: expression = "(0&0)&(0&0&0)"
Output: 3
Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3 operations.
The new expression evaluates to 1.
Example 3:

Input: expression = "(0|(1|0&1))"
Output: 1
Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
The new expression evaluates to 0.
 

Constraints:

1 <= expression.length <= 105
expression only contains '1','0','&','|','(', and ')'
All parentheses are properly matched.
There will be no empty parentheses (i.e: "()" is not a substring of expression).
 */

class Solution {
    Deque<Integer> num = new ArrayDeque<>();
    Deque<Integer> op = new ArrayDeque<>();
    Deque<Character> sign = new ArrayDeque<>();
    
    public int minOperationsToFlip(String expression) {
        for (char e: expression.toCharArray()) {
            if (e == '(' || e == '&' || e == '|') {
                sign.push(e);
                continue;
            } else if (e == ')') {
                sign.pop(); // '('
            } else {
                num.push(e - '0');
                op.push(1);
            }
            if (num.size() > 1 && sign.peek() != '(') {
                minOp(num.pop(), num.pop(), op.pop(), op.pop(), sign.pop());
            }
        }
        return op.peek();
    }

    public void minOp(int num1, int num2, int op1, int op2, char sign) {
        int[] mx = new int[2];
        if (sign == '&') {
            if (num1 == 1 && num2 == 1) {
                mx = new int[] {1, Math.min(op1, op2)};
            } else if (num1 == 0 && num2 == 0) {
                mx = new int[] {0, Math.min(op1, op2) + 1};
            } else {
                mx = new int[] {0, 1};
            }
        } else {
            if (num1 == 0 && num2 == 0) {
                mx = new int[] {0, Math.min(op1, op2)};
            } else if (num1 == 1 && num2 == 1) {
                mx = new int[] {1, Math.min(op1, op2) + 1};
            } else {
                mx = new int[] {1, 1};
            }
        }
        num.push(mx[0]);
        op.push(mx[1]);
    }
}



class Solution {
    int idx = 0;

    public int minOperationsToFlip(String expression) {
        return minOperationsToFlipHelper1(expression.toCharArray())[1];
    }
    
    private int[] minOperationsToFlipHelper1(char[] s) {
        char op = '|';
        int[] ans = null, next;
        while (idx < s.length) {
            char c = s[idx];
            if (c == ')') {
                return ans;
            } else if (c == '&' || c == '|') {
                op = c;
            } else {
                if (c == '(') {
                    ++idx;
                    next = minOperationsToFlipHelper1(s);
                } else {
                    next = new int[]{c - '0', 1};
                }
                if (ans == null) {
                    ans = next;
                } else {
                    if (op == '&') {
                        ans[1] = ans[0] != next[0] ? 1 : Math.min(ans[1], next[1]) + 1 - ans[0];
                        ans[0] &= next[0];
                    } else {
                        ans[1] = ans[0] != next[0] ? 1 : Math.min(ans[1], next[1]) + ans[0];
                        ans[0] |= next[0];
                    }
                }
            }
            ++idx;
        }
        return ans;
    }
}