/* 
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png
A sudoku puzzle...

https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png
...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
 */

class Solution {
    public void solveSudoku(char[][] board) {
        if (board==null||board.length==0)
            return;
        helper(board);
    }

    private boolean helper(char[][] board){
        for(int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                if (board[i][j]=='.'){
                    for (char num='1'; num<='9'; num++){//尝试
                        if(isValid(board, i, j, num)){
                            board[i][j]=num;

                            if (helper(board))
                                return true;
                            else
                                board[i][j]='.';//回退
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char c){
        // check column
        for (int row=0; row<9; row++)
            if (board[row][j] == c)
                return false;

       // check row
        for (int col=0; col<9; col++)
            if (board[i][col]==c)
                return false;

        // check block
        for(int row=i/3*3; row<i/3*3+3; row++)
            for (int col=j/3*3; col<j/3*3+3; col++)
                if (board[row][col]==c)
                    return false;

        return true;
    }
}

// 以后再来看吧...
// 赶时间
// 总觉得暴力回溯有点蠢