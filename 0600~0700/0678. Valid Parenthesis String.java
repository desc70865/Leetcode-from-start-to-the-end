/* 
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
 */

class Solution {
    char[] chs;
    int len;

    public boolean checkValidString(String s) {
        chs = s.toCharArray();
        len = chs.length;
        return dfs(0, 0);
    }

    private boolean dfs(int idx, int sum) {
        if (idx == len) return sum == 0;
        int rem = len - idx;
        if (sum < 0 || sum - rem > 0) return false;
        if (chs[idx] == '(') return dfs(idx + 1, sum + 1);
        else if (chs[idx] == ')') return dfs(idx + 1, sum - 1);
        else {
            return dfs(idx + 1, sum) || dfs(idx + 1, sum + 1) || dfs(idx + 1, sum - 1);
        }
    }
}



class Solution {
    public boolean checkValidString(String s) {
        int min = 0, max = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                min = Math.max(0, min - 1);
                if (--max < 0) return false;
            } else {
                min = Math.max(0, min - 1);
                max++;
            }
        }
        return min <= 0;
    }
}