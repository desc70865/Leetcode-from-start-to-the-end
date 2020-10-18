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
    public int totalNQueens(int n) {
    	int[] res = {0};
        helper(0, new ArrayList<Integer>(Collections.nCopies(n, -1)), res);
        return res[0];
    }
    private void helper(int curRow, List<Integer> queenCol, int[] res) {
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
    private boolean isValid(List<Integer> queenCol, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == queenCol.get(i) || Math.abs(row - i) == Math.abs(col - queenCol.get(i)))
                return false;
        }
        return true;
    }
}

// 滚啊

class Solution {
    private int cnt = 0, LEN;
    private int[] queen;
    
    public int totalNQueens(int n) {
        this.LEN = n;
        queen = new int[LEN];
        Arrays.fill(queen, -1);
        helper(0);
        return cnt;
    }
    
    private void helper(int index) {
        if (index == LEN) {
            ++cnt;
            return; 
        }
        
        for (int col = 0; col < LEN; col++) {
            if (queen[col] != -1) {
                continue;
            }
            if (isValid(index, col)) {
                queen[col] = index;
                helper(index + 1);
                queen[col] = -1;
            }
        }
    }
    
    private boolean isValid(int index, int col) {
        for (int i = 0; i < LEN; i++) {
            if (queen[i] == -1) {
                continue;
            }
            if (Math.abs(index - queen[i]) == Math.abs(col - i)) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] leftDiag = new boolean[2*n];
        boolean[] rightDiag = new boolean[2*n];
        int[] result = new int[]{0};
        dfs(col, leftDiag, rightDiag, n, result, 0); 
        return result[0];
    }
    
    private void dfs(boolean[] col, boolean[] leftDiag, boolean[] rightDiag, int n, int[] result, int row) {
        if (row == n) {
            result[0]++;
        } else {
            // try every col
            for (int i = 0; i < n; i++) {
                if (col[i] || leftDiag[row + i] || rightDiag[i - row + n]) {
                    continue;
                }
                col[i] = true;
                leftDiag[row + i] = true;
                rightDiag[i - row + n] = true;
                dfs (col, leftDiag, rightDiag, n, result, row + 1);
                col[i] = false;
                leftDiag[row + i] = false;
                rightDiag[i - row + n] = false;
            }
        }
    }
}