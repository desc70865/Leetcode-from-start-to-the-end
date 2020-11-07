/* 
You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.

Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (rows - 1, cols - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.

Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.

Notice that the modulo is performed after getting the maximum product.

 

Example 1:

Input: grid = [[-1,-2,-3],
               [-2,-3,-3],
               [-3,-3,-2]]
Output: -1
Explanation: It's not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
Example 2:

Input: grid = [[1,-2,1],
               [1,-2,1],
               [3,-4,1]]
Output: 8
Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
Example 3:

Input: grid = [[1, 3],
               [0,-4]]
Output: 0
Explanation: Maximum non-negative product is in bold (1 * 0 * -4 = 0).
Example 4:

Input: grid = [[ 1, 4,4,0],
               [-2, 0,0,1],
               [ 1,-1,1,1]]
Output: 2
Explanation: Maximum non-negative product is in bold (1 * -2 * 1 * -1 * 1 * 1 = 2).
 

Constraints:

1 <= rows, cols <= 15
-4 <= grid[i][j] <= 4
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][][] dp = new long[m][n][2];
        dp[0][0][0] = dp[0][0][1] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0][0] = dp[i][0][1] = dp[i - 1][0][0] * grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i][0] = dp[0][i][1] = dp[0][i - 1][0] * grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int k = grid[i][j];
                if (k == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                } else if (k > 0) {
                    dp[i][j][0] = min(dp[i - 1][j][0], dp[i][j - 1][0]) * k;
                    dp[i][j][1] = max(dp[i - 1][j][1], dp[i][j - 1][1]) * k;
                } else {
                    dp[i][j][0] = max(dp[i - 1][j][1], dp[i][j - 1][1]) * k;
                    dp[i][j][1] = min(dp[i - 1][j][0], dp[i][j - 1][0]) * k;
                }
            }
        }
        long res = dp[m - 1][n - 1][1];
        return res < 0 ? -1 : (int) (res % MOD);
    }
    
    private long max(long a, long b) {
        return Math.max(a, b);
    }
    
    private long min(long a, long b) {
        return Math.min(a, b);
    }
}