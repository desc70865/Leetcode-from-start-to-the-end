/* 
A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.

Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.

 

Example 1:


Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
Output: 3
Explanation: The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12
Example 2:


Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
Output: 2
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 106
 */

class Solution {
    int[][] grid, row, col;
    
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        this.grid = grid;
        this.row = new int[m + 1][n + 1];
        this.col = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                row[i][j] = row[i - 1][j] + grid[i - 1][j - 1];
                col[i][j] = col[i][j - 1] + grid[i - 1][j - 1];
            }
        }
        // for (int i = 0; i <= m; ++i) {
        //     System.out.println(Arrays.toString(row[i]));
        // }
        int ans = 1;
        for (int k = 2; k <= Math.min(m, n); ++k) {
            for (int r = k - 1; r < m && ans < k; ++r) {
                for (int c = k - 1; c < n && ans < k; ++c) {
                    if (check(r + 1, c + 1, k)) {
                        ans = k;
                    }
                }
            }
        }
        return ans;
    }
    
    private boolean check(int r, int c, int k) {
        int sum = row[r][c] - row[r - k][c];
        // System.out.println(r + " - " + c + " - " + k);
        // System.out.println(sum);
        for (int j = c - k + 1; j < c; ++j) {
            if (row[r][j] - row[r - k][j] != sum) {
                return false;
            }
        }
        for (int i = r - k + 1; i <= r; ++i) {
            if (col[i][c] - col[i][c - k] != sum) {
                return false;
            }
        }
        int x = 0;
        for (int i = r - k, j = c - k; i < r; ++i, ++j) {
            x += grid[i][j];
        }
        if (x != sum) {
            return false;
        }
        x = 0;
        for (int i = r - k, j = c - 1; i < r; ++i, --j) {
            x += grid[i][j];
        }
        if (x != sum) {
            return false;
        }
        return true;
    }
}