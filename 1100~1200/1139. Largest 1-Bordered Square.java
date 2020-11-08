/* 
Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.

 

Example 1:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
Output: 9
Example 2:

Input: grid = [[1,1,0,0]]
Output: 1
 

Constraints:

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] is 0 or 1
 */

class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] side = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                side[i + 1][j + 1][0] = grid[i][j] * side[i][j + 1][0] + grid[i][j];
                side[i + 1][j + 1][1] = grid[i][j] * side[i + 1][j][1] + grid[i][j];
            }
        }
        int max = 0;
        for (int k = 1; k <= Math.min(m, n); k++) {
            for (int i = 0; i <= m - k && max < k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (side[i + k][j + 1][0] >= k
                     && side[i + 1][j + k][1] >= k
                     && side[i + k][j + k][0] >= k
                     && side[i + k][j + k][1] >= k) {
                        max = k;
                        break;
                     }
                }
            }
        }
        return max * max;
    }
}