/* 
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m <= 500
1 <= n <= 500
grid consists only of lowercase English letters.
 */

class Solution {
    boolean[][] visited;
    int m = 0, n = 0;
    char target = '@';
    int[][] dirs = new int[][] { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };
    
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                if (check(grid, i, j)) return true;
            }
        }
        
        return false;
    }
    
    private boolean check(char[][] grid, int x, int y) {
        target = grid[x][y];
        return dfs(grid, x, y, -1);
    }
    
    private boolean dfs(char[][] grid, int x, int y, int mem) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (grid[x][y] != target) return false;
        if (visited[x][y]) return true;
        
        visited[x][y] = true;
        
        for (int i = 0; i < 4; i++) {
            if (mem == i) continue;
            if (dfs(grid, x + dirs[i][0], y + dirs[i][1], 3 - i)) return true;
        }
        return false;
    }
}