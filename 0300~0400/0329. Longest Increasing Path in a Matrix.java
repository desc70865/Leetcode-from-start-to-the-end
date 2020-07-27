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
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int max = 0;
        int[][] steps = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(steps[i][j] != 0) continue;
                int curr = helper(matrix, i, j, Integer.MIN_VALUE, steps);
                if(max < curr){
                    max = curr;
                }
            }
        }
        return max;
    }
    public int helper(int[][] matrix, int i, int j, int last, int[][] steps){
        if(i < 0 || j >= matrix[0].length || i >= matrix.length || j < 0 || last >= matrix[i][j]){
            return 0;
        }
        if(steps[i][j] != 0){
            return steps[i][j];
        }
        int curr = matrix[i][j];
        int a = Math.max(helper(matrix, i + 1, j, curr, steps), helper(matrix, i - 1, j, curr, steps));
        int b = Math.max(helper(matrix, i, j + 1, curr, steps), helper(matrix, i, j - 1, curr, steps));
        steps[i][j] = 1 + Math.max(a, b);
        return 1 + Math.max(a, b);
    }
}