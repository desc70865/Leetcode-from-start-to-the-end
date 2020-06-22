/* 
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */

class Solution {
    private int x, y, m, n;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        if (grid == null || grid.length == 0) return 0;
        m = grid.length-1; n = grid[0].length-1;
        int count = 0;
        while (getNextCluster()) {
            clearCluster(x, y);
            count++;
        }
        return count;
    }
    
    private boolean getNextCluster() {
        if (x > m || y > n) return false;
        
        while (x <= m) {
            while (y <= n) {
                if (grid[x][y] == '1') return true;
                y++;
            }
            x++; y = 0;
        }
        return false;
    }
    
    private void clearCluster(int i, int j) {
        if (i < 0 || j < 0 || i > m || j > n || grid[i][j] != '1') return;
        
        grid[i][j] = '0';
        
        clearCluster(i+1, j);
        clearCluster(i, j+1);
        clearCluster(i-1, j);
        clearCluster(i, j-1);
    }
}