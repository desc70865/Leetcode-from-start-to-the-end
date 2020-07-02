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
        char[] array = input.toCharArray();
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (isOperation(array[i])) {
                numList.add(num);
                num = 0;
                opList.add(array[i]);
                continue;
            }
            num = num * 10 + array[i] - '0';
        }
        numList.add(num);
        int N = numList.size();

        ArrayList<Integer>[][] dp = (ArrayList<Integer>[][]) new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(numList.get(i));
            dp[i][i] = result;
        }
        
        for (int n = 2; n <= N; n++) {
            for (int i = 0; i < N; i++) {
                int j = i + n - 1;
                if (j >= N) {
                    break;
                }
                ArrayList<Integer> result = new ArrayList<>();
                for (int s = i; s < j; s++) {
                    ArrayList<Integer> result1 = dp[i][s];
                    ArrayList<Integer> result2 = dp[s + 1][j];
                    for (int x = 0; x < result1.size(); x++) {
                        for (int y = 0; y < result2.size(); y++) {
                            char op = opList.get(s);
                            result.add(caculate(result1.get(x), op, result2.get(y)));
                        }
                    }
                }
                dp[i][j] = result;

            }
        }
        return dp[0][N-1];
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
    char[] chars;
    
    public List<Integer> diffWaysToCompute(String input) {
        chars = input.toCharArray();
        dp = new List[chars.length][chars.length];
        
        return solve(0, chars.length - 1);
    }
    
    private List<Integer> solve(int start, int end) {
        if (start > end) {
            return EMPTY_LIST;
        }
        
        if (dp[start][end] != null) {
            return dp[start][end];
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            if (!Character.isDigit(chars[i])) {
                char op = chars[i];
                List<Integer> part1 = solve(start, i - 1);
                List<Integer> part2 = solve(i + 1, end);
                
                for (int num1 : part1) {
                    for (int num2 : part2) {
                        ans.add(comp(num1, num2, op));
                    }
                }
            }
        }
        
        if (ans.isEmpty()) {
            ans.add(parseNum(start, end));
        }
        
        dp[start][end] = ans;
        return ans;
    }
    
    private int parseNum(int from, int to) {
        int num = 0;
        
        for (int i = from; i <= to; ++i) {
            num = num * 10 + chars[i] - '0';
        }
        
        return num;
    }
    
    private int comp(int n1, int n2, char op) {
        return op == '+' ? (n1 + n2) :
            (op == '-' ? (n1 - n2) : (n1 * n2));
    }
}