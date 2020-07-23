/* 
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }
}



class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length-1;
        int col = grid[0].length-1;

        int[] mark = new int[col+1];
        mark[col] = grid[row][col];
        for (int i = col-1; i >= 0; i--) {
            mark[i] = mark[i+1] + grid[row][i];
        }
        
        while (--row >= 0) {
            mark[col] += grid[row][col];
            for (int i = col-1; i >= 0; i--) {
                mark[i] = grid[row][i] + Math.min(mark[i+1], mark[i]);
            }
        }
        
        return mark[0];
    }
}



class Solution {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[] mark = new int[col];
        mark[col-1] = grid[row-1][col-1];
        for (int i = col-2; i >= 0; i--) {
            mark[i] = mark[i+1] + grid[row-1][i];
        }
        
        helper(mark, grid, row-2);
        return mark[0];
    }

    public void helper(int[] mark, int[][] grid, int row){
        if (row < 0) {
            return;
        }
        int len = mark.length;
        mark[len-1] += grid[row][len-1];
        for (int j = len-2; j >= 0; j--) {
            mark[j] = grid[row][j] + Math.min(mark[j+1], mark[j]);
        }
        helper(mark, grid, row-1);
    }
}