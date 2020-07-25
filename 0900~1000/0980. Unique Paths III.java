/* 
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20
 */

class Solution {
    public int uniquePathsIII(int[][] grid) {
        
    }
}

// 起点 -> 终点
// 遍历 -> 回溯 -> 标记

class Solution {
    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int res = 0;
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int startX = -1, startY = -1, endX = -1, endY = -1;
        int steps = 1;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 2) {
                    endX = i;
                    endY = j;
                } else if (grid[i][j] == 0) {
                    steps++;
                }
            }
        }
        
        helper(grid, startX, startY, endX, endY, steps, visited);
        return res;
    }
    
    private void helper(int[][] grid, int x, int y, int endX, int endY, int steps, boolean[][] visited) {
        if (x == endX && y == endY) {
            if (steps == 0) res++;
            return;
        }
        visited[x][y] = true;
        for (int[] dir: dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            if (0 <= nextX && nextX < grid.length && 0 <= nextY && nextY < grid[0].length 
            && grid[nextX][nextY] != -1 && !visited[nextX][nextY]) {
                helper(grid, nextX, nextY, endX, endY, steps - 1, visited);
            }
        }
        visited[x][y] = false;
    }
}