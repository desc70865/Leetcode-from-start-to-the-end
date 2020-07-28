/* 
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

class Solution {
    private int[][] matrix;
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        this.matrix = matrix;
        int max = 0;
        int[][] steps = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (steps[i][j] != 0) continue;
                int curr = helper(i, j, Integer.MIN_VALUE, steps);
                max = Math.max(max, curr);
            }
        }
        return max;
    }
    public int helper(int i, int j, int pre, int[][] steps){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= pre) {
            return 0;
        }
        if (steps[i][j] == 0) {
            int curr = matrix[i][j];
            int a = Math.max(helper(i + 1, j, curr, steps), helper(i - 1, j, curr, steps));
            int b = Math.max(helper(i, j + 1, curr, steps), helper(i, j - 1, curr, steps));
            steps[i][j] = 1 + Math.max(a, b);
        }
        return steps[i][j];
    }
}