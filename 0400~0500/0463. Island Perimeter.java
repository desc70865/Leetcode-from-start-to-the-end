/* 
You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example 1:


Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4
 

Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/island-perimeter/solution/di-lao-nian-qia-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n;
    int res;
    public int islandPerimeter(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] A, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n || A[x][y] == 0) {
            res++;
            return;
        }
        if (A[x][y] == 2) return;
        A[x][y] = 2;
        for (int[] dir: dirs) {
            dfs(A, x + dir[0], y + dir[1]);
        }
    }
}



class Solution {
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                if (i == 0 || grid[i - 1][j] == 0) res += 2;
                if (j == 0 || grid[i][j - 1] == 0) res += 2;
            }
        }
        return res;
    }
}