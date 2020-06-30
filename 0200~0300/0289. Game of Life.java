/* 
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */

class Solution {
    public void gameOfLife(int[][] board) {
        if (board.length < 1 || board[0].length < 1) {
            return;
        }
        nextState(board);
    }
    
    private void nextState(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int tmp = countLives(board, i, j);
                if (board[i][j] == 1) {
                    if (!range(tmp, 3, 4)) {
                        board[i][j] = 2;
                    }
                } else {
                    if (tmp == 3)
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] %= 2;
            }
        }
    }
    
    private int countLives(int[][] board, int x, int y) {
        int count = 0;
        for (int i = Math.max(x-1, 0); i <= Math.min(x+1, board.length-1); i++) {
            for (int j = Math.max(y-1, 0); j <= Math.min(y+1, board[0].length-1); j++) {
                if (range(board[i][j], 1, 2)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean range(int x, int low, int high) {
        return low == x || x == high;
    }
}