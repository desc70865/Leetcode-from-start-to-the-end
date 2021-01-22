/* 
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

The order of returned grid coordinates does not matter.
Both m and n are less than 150.
 

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

class Solution {
    int row, col;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        row = matrix.length;
        col = matrix[0].length;
        return helper(mark(matrix, 0, 0), mark(matrix, row - 1, col - 1));
    }

    private boolean[][] mark(int[][] matrix, int x, int y) {
        boolean[][] v = new boolean[row][col];
        for (int j = 0; j < col; j++) {
            v[x][j] = true;
            dfs(matrix, v, x, j);
        }
        for (int i = 0; i < row; i++) {
            v[i][y] = true;
            dfs(matrix, v, i, y);
        }
        return v;
    }

    private void dfs(int[][] matrix, boolean[][] v, int x, int y) {
        for (int[] dir: dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col || v[nx][ny] || matrix[nx][ny] < matrix[x][y]) continue;
            v[nx][ny] = true;
            dfs(matrix, v, nx, ny);
        }
    }

    private List<List<Integer>> helper(boolean[][] P, boolean[][] A) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (P[i][j] && A[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }
}