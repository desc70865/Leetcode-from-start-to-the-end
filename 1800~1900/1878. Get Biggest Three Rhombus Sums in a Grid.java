/* 
You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.

 

Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 105
 */

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // int size = m + n - 1;
        // int width = Math.max(m, n);
        int[][] x = new int[m][n];
        int[][] y = new int[m][n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                x[i][j] = grid[i][j] + (i > 0 && j < n - 1 ? x[i - 1][j + 1] : 0);
                y[i][j] = grid[i][j] + (i > 0 && j > 0 ? y[i - 1][j - 1] : 0);
                set.add(grid[i][j]);
            }
        }
        for (int a = 1; 2 * a + 1 <= Math.min(m, n); ++a) {
            for (int row = a * 2; row < m; ++row) {
                for (int col = a; col + a < n; ++col) {
                    int sum = x[row][col] - (row - a - 1 >= 0 && col + a + 1 < n ? x[row - a - 1][col + a + 1] : 0)
                            + y[row][col] - (row - a - 1 >= 0 && col - a - 1 >= 0 ? y[row - a - 1][col - a - 1] : 0)
                            + x[row - a][col - a] - (row - a * 2 - 1 >= 0 && col + 1 < n ? x[row - a * 2 - 1][col + 1] : 0)
                            + y[row - a][col + a] - (row - a * 2 - 1 >= 0 && col - 1 >= 0 ? y[row - a * 2 - 1][col - 1] : 0)
                            - grid[row][col] - grid[row - a][col - a] - grid[row - a][col + a] - grid[row - a * 2][col];
                    set.add(sum);
                }
            }
        }
        int[] ans = new int[3];
        for (int num: set) {
            if (num > ans[0]) {
                ans[2] = ans[1];
                ans[1] = ans[0];
                ans[0] = num;
            } else if (num > ans[1]) {
                ans[2] = ans[1];
                ans[1] = num;
            } else if (num > ans[2]) {
                ans[2] = num;
            }
        }
        if (ans[1] == 0) {
            return new int[] {ans[0]};
        } else if (ans[2] == 0) {
            return new int[] {ans[0], ans[1]};
        } else {
            return ans;
        }
    }
}