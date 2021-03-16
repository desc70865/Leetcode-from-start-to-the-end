/* 
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

 

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
 */

class Solution {
    int m, n;
    int[] dirs = new int[] {0, 1, 0, -1, 0};

    public int largestIsland(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int[] area = new int[m * n / 2 + 3];
        int idx = 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                area[idx] = dfs(grid, i, j, idx++);
            }
        }
        int max = 0;
        for (int i = 0; i < idx; i++) {
            max = Math.max(max, area[i]);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    max = Math.max(max, 1 + calc(grid, i, j, area));
                }
            }
        }
        return max;
    }

    private int calc(int[][] grid, int x, int y, int[] area) {
        Set<Integer> set = new HashSet<>();
        for (int k = 0; k < 4; k++) {
            int nx = x + dirs[k];
            if (nx < 0 || nx >= m) continue;
            int ny = y + dirs[k + 1];
            if (ny < 0 || ny >= n) continue;
            set.add(grid[nx][ny]);
        }
        int cur = 0;
        for (int p: set) {
            cur += area[p];
        }
        return cur;
    }

    private int dfs(int[][] grid, int x, int y, int idx) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = idx;
        int sum = 1;
        for (int i = 0; i < 4; i++) {
            sum += dfs(grid, x + dirs[i], y + dirs[i + 1], idx);
        }
        return sum;
    }
}