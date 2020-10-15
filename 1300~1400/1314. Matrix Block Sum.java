/* 
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 

Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
 */

class Solution {
    int[][] cnt, mat;
    int m, n;
    public int[][] matrixBlockSum(int[][] mat, int K) {
        this.mat = mat;
        m = mat.length; n = mat[0].length;
        cnt = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, K);
            }
        }
        return cnt;
    }

    private void update(int x, int y, int k) {
        for (int i = x - k; i <= x + k; i++) {
            for (int j = y - k; j <= y + k; j++) {
                add(i, j, x, y);
            }
        }
    }

    private void add(int i, int j, int x, int y) {
        if (i < 0 || j < 0 || i >= m || j >= n) return;
        cnt[x][y] += mat[i][j];
    }
}



class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[row][col];
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++)
                dp[i][j] = mat[i - 1][j - 1] + dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1];
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int x0 = Math.max(i - K - 1, 0);
                int x1 = Math.min(i + K, row);
                int y0 = Math.max(j - K - 1, 0);
                int y1 = Math.min(j + K, col);
                res[i - 1][j - 1] = dp[x1][y1] - dp[x1][y0] - dp[x0][y1] + dp[x0][y0];
            }
        }
        return res;
    }
}