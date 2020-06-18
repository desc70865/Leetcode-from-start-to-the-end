/* 
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(n, n, "", res);
        return res;
    }
    void helper(int left, int right, String out, List<String> res) {
        if (left < 0 || right < 0 || left > right) return; // 递归终止条件
        if (left == 0 && right == 0) {
            res.add(out);
            return; // 正常添加结果并返回
        }
        helper(left - 1, right, out + "(", res); // 选择分支,即 C(2n,n) 种结果
        helper(left, right - 1, out + ")", res); // 但其中有 C(2n, n+1) 种是非法排列
    }
}

/* 
1
2
5
14
42
132
429
1430
4862
 */

// Catalan number: C(2n,n) - C(2n, n+1)
// 关于 y = x + 1 的映射