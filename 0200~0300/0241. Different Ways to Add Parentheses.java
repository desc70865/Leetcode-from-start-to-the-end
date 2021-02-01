/* 
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
 */

class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> numList = new ArrayList<>();
        List<Character> opList = new ArrayList<>();
        int num = 0;
        for (char c: input.toCharArray()) {
            if (isOperation(c)) {
                opList.add(c);
                numList.add(num);
                num = 0;
            } else {
                num *= 10;
                num += c - '0';
            }
        }
        numList.add(num);
        int len = numList.size();

        ArrayList<Integer>[][] dp = (ArrayList<Integer>[][]) new ArrayList[len][len];
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(numList.get(i));
            dp[i][i] = list;
        }
        
        for (int k = 2; k <= len; k++) {
            for (int i = 0; i < len; i++) {
                int j = i + k - 1;
                if (j >= len) {
                    break;
                }
                ArrayList<Integer> list = new ArrayList<>();
                for (int s = i; s < j; s++) {
                    ArrayList<Integer> listA = dp[i][s];
                    ArrayList<Integer> listB = dp[s + 1][j];
                    for (int x = 0; x < listA.size(); x++) {
                        for (int y = 0; y < listB.size(); y++) {
                            char op = opList.get(s);
                            list.add(caculate(listA.get(x), op, listB.get(y)));
                        }
                    }
                }
                dp[i][j] = list;
            }
        }
        return dp[0][len - 1];
    }

    private int caculate(int num1, char c, int num2) {
        switch (c) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            default:
                return -1;
        }
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}

// ...

class Solution {
    private static final List<Integer> EMPTY_LIST = new ArrayList<>();
    List<Integer>[][] dp;
    char[] chs;
    
    public List<Integer> diffWaysToCompute(String input) {
        chs = input.toCharArray();
        dp = new List[chs.length][chs.length];
        return dfs(0, chs.length - 1);
    }
    
    private List<Integer> dfs(int l, int r) {
        if (l > r) {
            return EMPTY_LIST;
        }
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Character.isDigit(chs[i])) continue;
            char op = chs[i];
            for (int x: dfs(l, i - 1)) {
                for (int y: dfs(i + 1, r)) {
                    ans.add(comp(x, y, op));
                }
            }
        }
        if (ans.isEmpty()) {
            ans.add(parseToNum(l, r));
        }
        return dp[l][r] = ans;
    }
    
    private int parseToNum(int from, int to) {
        int num = 0;
        for (int i = from; i <= to; i++) {
            num *= 10;
            num += chs[i] - '0';
        }
        return num;
    }
    
    private int comp(int n1, int n2, char op) {
        return op == '+' ? (n1 + n2) :
            (op == '-' ? (n1 - n2) : (n1 * n2));
    }
}