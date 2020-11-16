/* 
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,

0 E 0 0 
E 0 W E 
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.
 */

class Solution {
    int[] dir = new int[] {0, 1, 0, -1, 0};

    int m, n;

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') continue;
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += dfs(grid, i + dir[k], j + dir[k + 1], dir[k], dir[k + 1]);
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private int dfs(char[][] grid, int x, int y, int dx, int dy) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 'W') return 0;
        return (grid[x][y] == 'E' ? 1 : 0) + dfs(grid, x + dx, y + dy, dx, dy);
    }
}