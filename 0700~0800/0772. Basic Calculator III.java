/* 
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

 

Example 1:

Input: s = "1+1"
Output: 2
Example 2:

Input: s = "6-4/2"
Output: 4
Example 3:

Input: s = "2*(5+5*2)/3+(6/2+8)"
Output: 21
Example 4:

Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
Output: -12
Example 5:

Input: s = "0"
Output: 0
 

Constraints:

1 <= s <= 104
s consists of digits, '+', '-', '*', '/', '(', and ')'.
s is a valid expression.
 

Follow up: Could you solve the problem without using built-in library functions?
 */

class Solution {
    char[] expression;

    public int calculate(String s) {
        this.expression = s.toCharArray();
        return eval(0, expression.length - 1);
    }

    private int eval(int left, int right) {
        char operator = '+';
        int num = 0;
        int[] nums = new int[right - left + 2];
        for (int l = left; l <= right; ++l) {
            if (expression[l] == '(') {
                int r = l + 1;
                for (int bracket = 1; bracket > 0; ++r) {
                    if (expression[r] == '(') {
                        ++bracket;
                    } else if (expression[r] == ')') {
                        --bracket;
                    }
                }
                --r;
                num = eval(l + 1, r - 1);
                l = r;
            } else if (expression[l] >= '0') {
                num *= 10;
                num += expression[l] - '0';
            } else {
                evalHelper(nums, operator, num);
                num = 0;
                operator = expression[l];
            }
        }
        evalHelper(nums, operator, num);
        int sum = 0;
        for (int p = nums[0]; p > 0; --p) {
            sum += nums[p];
        }
        return sum;
    }

    private void evalHelper(int[] nums, char operator, int num) {
        if (operator == '*') {
            nums[nums[0]] *= num;
        } else if (operator == '/') {
            nums[nums[0]] /= num;
        } else {
            nums[++nums[0]] = operator == '+' ? num : - num;
        }
    }
}