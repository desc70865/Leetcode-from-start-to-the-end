/* 
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
 */

class Solution {
    int[] dir = new int[] {0, 1, 0, -1, 0};
    int row;
    int col;

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        row = grid.length;
        col = grid[0].length;
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, path, i, j, i, j);
                    islands.add(path.toString());
                }
            }
        }
        return islands.size();
    }
    
    private void dfs(int[][] grid, StringBuilder path, int firstX, int firstY, int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        path.append(x - firstX).append(y - firstY);
        for (int i = 0; i < 4; i++) {
            dfs(grid, path, firstX, firstY, x + dir[i], y + dir[i + 1]);
        }
    }
}