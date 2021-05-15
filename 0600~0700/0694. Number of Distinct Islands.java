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
    static int[] dir = {0, 1, 0, -1, 0};
    int m, n;

    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        this.m = grid.length;
        this.n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    continue;
                }
                StringBuilder island = new StringBuilder();
                dfs(grid, island, i, j, i, j);
                set.add(island.toString());
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, StringBuilder island, int x, int y, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        island.append(i - x).append(j - y);
        grid[i][j] = 0;
        for (int k = 0; k < 4; ++k) {
            dfs(grid, island, x, y, i + dir[k], j + dir[k + 1]);
        }
    }
}