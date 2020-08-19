/* 
On a N * N grid, we place some 1 * 1 * 1 cubes.

Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).

Return the total surface area of the resulting shapes.

 

Example 1:

Input: [[2]]
Output: 10
Example 2:

Input: [[1,2],[3,4]]
Output: 34
Example 3:

Input: [[1,0],[0,2]]
Output: 16
Example 4:

Input: [[1,1,1],[1,0,1],[1,1,1]]
Output: 32
Example 5:

Input: [[2,2,2],[2,1,2],[2,2,2]]
Output: 46
 

Note:

1 <= N <= 50
0 <= grid[i][j] <= 50
 */

class Solution {
    int m = 0, n = 0;
    int res = 0;
    public int surfaceArea(int[][] grid) {
        m = grid.length; n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) calc(grid, i, j);
            }
        }
        return res;
    }

    private void calc(int[][] grid, int i, int j) {
        res += 2;
        check(grid, i-1, j, grid[i][j]);
        check(grid, i+1, j, grid[i][j]);
        check(grid, i, j-1, grid[i][j]);
        check(grid, i, j+1, grid[i][j]);
    }

    private void check(int[][] grid, int i, int j, int v) {
        if (i < 0 || i >= m || j < 0 || j >= n) res += v;
        else res += Math.max(0, v - grid[i][j]);
    }
}