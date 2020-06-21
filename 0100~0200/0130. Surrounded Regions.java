/* 
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

class Solution {
    private int m, n;
    private char[][] board;
    public void solve(char[][] board) {
        
        if(board == null || board.length == 0 || board[0].length == 0) return;
        
        this.board = board;
        m = board.length-1; n = board[0].length-1;
        
        for (int i = 0; i <= m; i++) {
            helper(i, 0);
            helper(i, n);
        }
        for (int j = 1; j < n; j++) {
            helper(0, j);
            helper(m, j);
        }
        
        /* for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (board[i][j] != 'X') board[i][j] += 9; // F -> O -> X
            }
        } */
        
        flipA2B('O', 'X');
        flipA2B('F', 'O');
    }
    
    private void helper(int x, int y) {
        
        if (x < 0 || x > m || y < 0 || y > n) return;
        if (board[x][y] == 'O') {
            board[x][y] = 'F';
            helper(x-1, y);
            helper(x+1, y);
            helper(x, y-1);
            helper(x, y+1);   
        }
    }
    
    private void flipA2B(char A, char B){
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (board[i][j] == A) board[i][j] = B;
            }
        }
    }
}