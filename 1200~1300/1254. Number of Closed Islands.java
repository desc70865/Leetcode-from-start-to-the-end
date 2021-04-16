/* 
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */

class Solution {
    int[] p;
    int m, n;
    int sum;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        sum = m * n;
        p = new int[sum + 1];
        for (int i = 0; i < p.length; i++) p[i] = i;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) sum--;
                else {
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) union(id(i, j), m * n);
                    if (i > 0 && grid[i - 1][j] == 0) union(id(i, j), id(i - 1, j));
                    if (j > 0 && grid[i][j - 1] == 0) union(id(i, j), id(i, j - 1));
                }
            }
        }
        return sum;
    }

    public int id(int x, int y) {
        return x * n + y;
    }

    public void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        if (A == B) return;
        if (A > B) p[B] = A;
        else p[A] = B;
        sum--;
    }

    public int find(int a) {
        return p[a] == a ? a : (p[a] = find(p[a]));
    }
}



class Solution {
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n;
    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) continue;
                if (dfs(grid, i, j)) res++;
            }
        }
        // for (int[] p: grid) System.out.println(Arrays.toString(p));
        return res;
    }

    private boolean dfs(int[][] A, int x, int y) {
        if (A[x][y] == 1) return true;
        if (x == 0 || y == 0 || x == m - 1 || y == n - 1) return false;
        A[x][y] = 1;
        boolean f = true;
        for (int[] dir: dirs) {
            f &= dfs(A, x + dir[0], y + dir[1]);
        }
        return f;
    }
}