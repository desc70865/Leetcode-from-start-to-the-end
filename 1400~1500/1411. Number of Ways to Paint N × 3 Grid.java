/* 
You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).

Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

Example 1:


Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown.
Example 2:

Input: n = 2
Output: 54
Example 3:

Input: n = 3
Output: 246
Example 4:

Input: n = 7
Output: 106494
Example 5:

Input: n = 5000
Output: 30228214
 

Constraints:

n == grid.length
grid[i].length == 3
1 <= n <= 5000
 */

class Solution {
    static final int MOD = 1_000_000_007;
    static int[][] m = {{3, 2}, {2, 2}};
    static int[][] c = {{6}, {6}};

    public int numOfWays(int n) {
        int[][] ans = matrixMultiply(quickMatrixPow(m, n - 1), c);
        return (ans[0][0] + ans[1][0]) % MOD;
    }

    private int[][] matrixMultiply(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    ans[i][j] += (long) A[i][k] * B[k][j] % MOD;
                    ans[i][j] %= MOD;
                }
            }
        }
        return ans;
    }

    private int[][] quickMatrixPow(int[][] X, int n) {
        int[][] ans = {{1, 0}, {0, 1}};
        while (n != 0) {
            if (n % 2 == 1) ans = matrixMultiply(ans, X);
            X = matrixMultiply(X, X);
            n >>= 1;
        }
        return ans;
    }
}

// 层级递推关系
// 矩阵乘法
// 快速幂