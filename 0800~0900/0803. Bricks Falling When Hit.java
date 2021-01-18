/* 
You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space. A brick is stable if:

It is directly connected to the top of the grid, or
At least one other brick in its four adjacent cells is stable.
You are also given an array hits, which is a sequence of erasures we want to apply. Each time we want to erase the brick at the location hits[i] = (rowi, coli). The brick on that location (if it exists) will disappear. Some other bricks may no longer be stable because of that erasure and will fall. Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).

Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.

Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.

 

Example 1:

Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
Output: [2]
Explanation: Starting with the grid:
[[1,0,0,0],
 [1,1,1,0]]
We erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,1,1,0]]
The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
[[1,0,0,0],
 [0,0,0,0]]
Hence the result is [2].
Example 2:

Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: Starting with the grid:
[[1,0,0,0],
 [1,1,0,0]]
We erase the underlined brick at (1,1), resulting in the grid:
[[1,0,0,0],
 [1,0,0,0]]
All remaining bricks are still stable, so no bricks fall. The grid remains the same:
[[1,0,0,0],
 [1,0,0,0]]
Next, we erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,0,0,0]]
Once again, all remaining bricks are still stable, so no bricks fall.
Hence the result is [0,0].
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] is 0 or 1.
1 <= hits.length <= 4 * 104
hits[i].length == 2
0 <= xi <= m - 1
0 <= yi <= n - 1
All (xi, yi) are unique.
 */

class Solution {
    static final int[] dirs = {0, 1, 0, -1, 0};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int row = grid.length, col = grid[0].length;
        UnionFind uf = new UnionFind(row * col + 1);
        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++) {
            map[i] = grid[i].clone();
        }
        for (int[] hit: hits) {
            map[hit[0]][hit[1]] = 0;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 0) continue;
                // root
                if (i == 0) {
                    uf.union(row * col, i * col + j);
                }
                // up
                if (i > 0 && map[i - 1][j] == 1) {
                    uf.union(i * col + j, (i - 1) * col + j);
                }
                // left
                if (j > 0 && map[i][j - 1] == 1) {
                    uf.union(i * col + j, i * col + j - 1);
                }
            }
        }

        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            if (grid[r][c] == 0) {
                continue;
            }
            int prev = uf.size(row * col);
            if (r == 0) {
                uf.union(c, row * col);
            }
            for (int j = 0; j < 4; j++) {
                int nr = r + dirs[j], nc = c + dirs[j + 1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col) {
                    if (map[nr][nc] == 1) {
                        uf.union(r * col + c, nr * col + nc);
                    }
                }
            }
            int size = uf.size(row * col);
            ans[i] = Math.max(0, size - prev - 1);
            map[r][c] = 1;
        }
        return ans;
    }
}

class UnionFind {
    int[] p;
    int[] sz;

    public UnionFind(int n) {
        p = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) ;
        else {
            p[x] = y;
            sz[y] += sz[x];
        }
    }

    public int size(int x) {
        return sz[find(x)];
    }
}



class Solution {
    private static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        for (int[] hit: hits) {
            grid[hit[0]][hit[1]]--;
        }
        for (int j = 0; j < grid[0].length; j++) {
            dfs(grid, 0, j);
        }

        int[] res = new int[hits.length];
        for (int k = hits.length - 1; k >= 0; k--) {
            int i = hits[k][0];
            int j = hits[k][1];
            grid[i][j]++;
            if (grid[i][j] == 1 && isStable(grid, i, j)) {
                res[k] = dfs(grid, i, j) - 1;
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        return 1 + dfs(grid, i + 1, j) +
                   dfs(grid, i - 1, j) +
                   dfs(grid, i, j - 1) +
                   dfs(grid, i, j + 1);
    }

    private boolean isStable(int[][] grid, int x, int y) {
        if (x == 0) {
            return true;
        }

        for (int[] dir: dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 2) {
                continue;
            }
            return true;
        }

        return false;
    }
}