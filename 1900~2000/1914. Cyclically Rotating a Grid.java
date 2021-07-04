/* 
You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:



A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:


Return the matrix after applying k cyclic rotations to it.

 

Example 1:


Input: grid = [[40,10],[30,20]], k = 1
Output: [[10,20],[40,30]]
Explanation: The figures above represent the grid at every state.
Example 2:

  
Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
Explanation: The figures above represent the grid at every state.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 50
Both m and n are even integers.
1 <= grid[i][j] <= 5000
1 <= k <= 109
 */

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < Math.min(m, n) / 2; ++i) {
            int size = (m + n - 2) * 2 - 8 * i;
            int cycle = k % size;
            int upRow = i;
            int downRow = m - 1 - i;
            int leftCol = i;
            int rightCol = n - 1 - i;
            int[] line = new int[size];
            int idx = 0;
            for (int col = leftCol; col < rightCol; ++col) {
                line[idx++] = grid[upRow][col];
            }
            for (int row = upRow; row < downRow; ++row) {
                line[idx++] = grid[row][rightCol];
            }
            for (int col = rightCol; col > leftCol; --col) {
                line[idx++] = grid[downRow][col];
            }
            for (int row = downRow; row > upRow; --row) {
                line[idx++] = grid[row][leftCol];
            }
            int p = 0;
            for (int col = leftCol; col < rightCol; ++col) {
                ans[upRow][col] = line[(p++ + cycle) % size];
            }
            for (int row = upRow; row < downRow; ++row) {
                ans[row][rightCol] = line[(p++ + cycle) % size];
            }
            for (int col = rightCol; col > leftCol; --col) {
                ans[downRow][col] = line[(p++ + cycle) % size];
            }
            for (int row = downRow; row > upRow; --row) {
                ans[row][leftCol] = line[(p++ + cycle) % size];
            }
        }
        return ans;
    }
}