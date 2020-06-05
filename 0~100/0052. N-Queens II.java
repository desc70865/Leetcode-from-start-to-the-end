/* 
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

https://assets.leetcode.com/uploads/2018/10/12/8-queens.png

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */

class Solution {
    public static int totalNQueens(int n) {
    	int[] res = {0};
        helper(0, new ArrayList<Integer>(Collections.nCopies(n, -1)), res);
        return res[0];
    }
    private static void helper(int curRow, List<Integer> queenCol, int[] res) {
        int n = queenCol.size();
        if (curRow == n) {
        	res[0] += 1;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(queenCol, curRow, col)) {
                queenCol.set(curRow, col);
                helper(curRow + 1, queenCol, res);
                queenCol.set(curRow, -1);
            }
        }
    }
    private static boolean isValid(List<Integer> queenCol, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == queenCol.get(i) || Math.abs(row - i) == Math.abs(col - queenCol.get(i)))
                return false;
        }
        return true;
    }
}

// 滚啊