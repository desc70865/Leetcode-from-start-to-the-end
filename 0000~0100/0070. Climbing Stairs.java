/* 
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */

class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n];
        dp[0] = 1; dp[1] = 2;
        for (int i = 2; i < n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n-1];
    }
}

// 有个屁用

class Solution {
    public int climbStairs(int n) {
        return Jump(n, 2);
    }
    
    private int Jump(int n, int k) {
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;
        for (int i=2; i < n+1; i++) {
            for (int j=1; (i - j) >= 0 && j <= k; j++) {
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }
}

class Solution {
    public static int climbStairs(int n) {
    	n++;
        if (n == 0 || n == 1) {
            return n;
        }
        int[][] m = new int[][] {{1, 1}, {1, 0}};
        int[][] originM = new int[][] {{1}, {0}}; 
        int[][] res = matrixMul(matrixPow(m, n - 1), originM); 
        return res[0][0];
    }

    private static int[][] matrixMul(int[][] a, int[][] b) {
        int row = a.length;
        int col = b[0].length;
        int[][] res = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < b.length; k++) {
                for (int j = 0; j < col; j++) { // 按行读取可用cpu缓存加速
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    // 递归实现logN快速幂
    private static int[][] matrixPow (int[][] m, int n) {
        if (n == 1) {
            return m;
        }
        int[][] res = matrixPow(m, n / 2);
        return (n & 1) == 0? matrixMul(res, res): matrixMul(matrixMul(res, res), m);
    }

    // 位运算实现logN快速幂
    private static int[][] matrixPow (int[][] m, int n) {
        int[][] res = new int[][] {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = matrixMul(res, m);
            }
            n >>= 1;
            m = matrixMul(m, m);
        }
        return res;
    }
}



class Solution {
    static long[] f;
    static final int SIZE = 100;
    static final int MOD = (int) 1e9 + 7;

    static {
        f = new long[SIZE + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= SIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
            // f[i] %= MOD;
        }
    }

    public int climbStairs(int n) {
        return (int) f[n + 1];
    }
}