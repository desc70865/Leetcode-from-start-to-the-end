/* 
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).

 

Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 

Note:

0 ≤ N ≤ 30.
 */

class Solution {
    public int fib(int N) {
        if (N <= 1) return N;
        int[] dp = new int[N+1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i < N+1; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }
}

// ...0070

class Solution {
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[][] m = new int[][] {{1, 1}, {1, 0}};
        int[][] originM = new int[][] {{1}, {0}}; 
        int[][] res = matrixMul(matrixPow(m, n - 1), originM); 
        return res[0][0];
    }

    private int[][] matrixMul(int[][] a, int[][] b) {
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
    private int[][] matrixPow (int[][] m, int n) {
        if (n == 1) {
            return m;
        }
        int[][] res = matrixPow(m, n / 2);
        return (n & 1) == 0? matrixMul(res, res): matrixMul(matrixMul(res, res), m);
    }

    // 位运算实现logN快速幂
    private int[][] matrixPowX (int[][] m, int n) {
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
    public int fib(int N) {
        return N <= 1 ? N : fib(N - 1) + fib(N - 2);
    }
}



class Solution {
    static long[] f;
    static final int SIZE = 30;
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

    public int fib(int n) {
        return (int) f[n];
    }
}