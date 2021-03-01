/* 
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */

class Solution {
    List<String> res = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int left = 0, right = 0;
        for (char c: chs) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) right++;
                else left--;
            }
        }
		// 921. Minimum Add to Make Parentheses Valid
        dfs(new char[len - left - right], 0, chs, 0, true, true, 0, left, right);
        return res;
    }

    // @param L, R  for successive ( or )
    private void dfs(char[] ans, int p, char[] chs, int idx, boolean L, boolean R, int diff, int left, int right) {
        if (ans.length == p || chs.length == idx) {
            if (diff == 0) {
                res.add(new String(ans));
            }
            return;
        }
        if (chs[idx] == '(') {
            if (L) {
                ans[p] = '(';
                dfs(ans, p + 1, chs, idx + 1, true, true, diff + 1, left, right);
                L = false;
            }
            if (left > 0)
                dfs(ans, p, chs, idx + 1, L, R, diff, left - 1, right);
        } else if (chs[idx] == ')') {
            if (diff > 0 && R) {
                ans[p] = ')';
                dfs(ans, p + 1, chs, idx + 1, true, true, diff - 1, left, right);
                R = false;
            }
            if (right > 0)
                dfs(ans, p, chs, idx + 1, L, R, diff, left, right - 1);
        } else {
            ans[p] = chs[idx];
            dfs(ans, p + 1, chs, idx + 1, true, true, diff, left, right);
        }
    }
}