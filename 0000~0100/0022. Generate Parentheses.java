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

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(n, n, new StringBuilder(), res);
        return res;
    }
    void helper(int left, int right, StringBuilder sb, List<String> res) {
        if (left < 0 || right < 0 || left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        helper(left - 1, right, sb.append("("), res);
        sb.deleteCharAt(sb.length()-1);
        helper(left, right - 1, sb.append(")"), res);
        sb.deleteCharAt(sb.length()-1);
    }
}



class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        char[] s = new char[2*n];
        parenUtil(result,s,n,n,0);
        return result;
    }
    
    static void parenUtil(ArrayList<String> result, char []s, int leftRem, int rightRem, int index){
        if(leftRem<0 || rightRem < 0){
            return; // invalid not possible
        }
        
        if(leftRem == 0 && rightRem == 0){
            result.add(String.valueOf(s));
            return ; 
        }
        if(leftRem > 0){
            s[index]='(';
            parenUtil(result,s,leftRem-1,rightRem,index+1);
        }
        if(rightRem>leftRem){
            s[index]=')';
            parenUtil(result,s, leftRem, rightRem-1, index+1);
        }
    }
}