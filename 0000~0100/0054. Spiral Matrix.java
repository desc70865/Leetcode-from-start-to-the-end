/* 
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        dfs(matrix, 0, 0, matrix.length, matrix[0].length);
        return res;
    }

    private void dfs(int[][] matrix, int x, int y, int m, int n) {
        if (m < 1 || n < 1) return;
        if (m == 1 && n == 1) {
            res.add(matrix[x][y]);
            return;
        } else if (m == 1) {
            for (int j = y; j < y + n; j++) res.add(matrix[x][j]);
            return;
        } else if (n == 1) {
            for (int i = x; i < x + m; i++) res.add(matrix[i][y]);
            return;
        }
        for (int j = y; j < y + n - 1; j++) res.add(matrix[x][j]);
        for (int i = x; i < x + m - 1; i++) res.add(matrix[i][y + n - 1]);
        for (int j = y + n - 1; j > y; j--) res.add(matrix[x + m - 1][j]);
        for (int i = x + m - 1; i > x; i--) res.add(matrix[i][y]);
        dfs(matrix, x + 1, y + 1, m - 2, n - 2);
    }
}