/* 
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6
 

Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50
 */

class Solution {
    public int scoreOfParentheses(String S) {
        char[] s = S.toCharArray();
        int LEN = s.length;
        int[] arr = new int[LEN/2 + 1];
        int idx = 1;
        for (int i = 1; i < LEN; i++) {
            if (s[i] == ')') arr[idx++] = i;
        }
        System.out.println(Arrays.toString(arr));
        int[] res = new int[LEN/2];
        for (int i = 0; i < LEN/2; i++) {
            res[i] = arr[i+1] - arr[i] - 1;
        }
        System.out.println(Arrays.toString(res));
        return 0;
    }
}

// sum: 2^n

class Solution {
    public int scoreOfParentheses(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') bal++;
            else {
                bal--;
                if (S.charAt(i-1) == '(') ans += 1 << bal;
            }
        }
        return ans;
    }
}