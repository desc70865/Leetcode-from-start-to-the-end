/* 
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

 

Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

 

Note:

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
 */

class Solution {
    static final int MOD = 1_000_000_007;
    Integer[][][] memo;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int findPaths(int m, int n, int N, int i, int j) {
        memo = new Integer[m][n][N + 1];
        return dfs(m, n, i, j, N);
    }
    
    private int dfs(int m, int n, int i, int j, int N) {
        if (N < 0) return 0;
        if (i < 0 || i == m || j < 0 || j == n) return 1;
        if (memo[i][j][N] != null) return memo[i][j][N];
        long ans = 0;
        for (int[] dir: dirs) {
            ans += dfs(m, n, i + dir[0], j + dir[1], N - 1);
        }
        return memo[i][j][N] = (int) (ans % MOD);
    }
}