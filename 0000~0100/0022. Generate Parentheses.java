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

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, new StringBuilder(), res);
        return res;
    }
    
    private void helper(int L, int R, StringBuilder sb, List<String> res) {
        if (L < 0 || R < 0 || L > R) return;
        if (L == 0 && R == 0) {
            res.add(sb.toString());
            return;
        }
        helper(L - 1, R, sb.append("("), res);
        sb.setLength(sb.length() - 1);
        helper(L, R - 1, sb.append(")"), res);
        sb.setLength(sb.length() - 1);
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
// 在 (n, n) 的范围内,符合条件的路径都在 y = x 的右下方(含边界)
// 则不符合条件的路径都会触碰 y = x + 1
// 其中存在唯一的映射使其转变为 前往 (n - 1, n + 1) 的路径
// C(n + 1 + n - 1, n + 1)

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