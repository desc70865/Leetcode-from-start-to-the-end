/* 
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 

Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 */

class Solution {
    int[][] dirs = new int[][] { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };
    int m, n;
    boolean[][] visited;
    int[][] res;

    public int[][] updateMatrix(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        visited = new boolean[m][n];
        res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0 && res[i][j] == 0) search(matrix, i, j);
            }
        }
        return res;
    }

    private int search(int[][] A, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) return m + n;
        if (A[i][j] == 0) return 0;

        int k = res[i][j] == 0 ? m + n : res[i][j];
        visited[i][j] = true;
        for (int[] dir: dirs) {
            k = Math.min(k, 1 + search(A, i + dir[0], j + dir[1]));
        }
        visited[i][j] = false;

        res[i][j] = k;
        return k;
    }
}



class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = matrix[i][j] == 0 ? 0 : m + n;
            }
        }

        // 从左上角开始
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }
        // 从右下角开始
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 < m) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }
        return dp;
    }
}