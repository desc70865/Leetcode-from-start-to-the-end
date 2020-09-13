/* 
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 

Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */

class Solution {
    private int raw, col;
    private char[][] board;
    private char[] str;
    
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        
        this.board = board;
        str = word.toCharArray();
        raw = board.length;
        col = board[0].length;
        
        for (int i = 0; i < raw; i++) {
            for (int j = 0; j < col; j++) {
                if (search(str, 0, i, j)) return true;
            }
        }
        
        return false;
    }
    private boolean search(char[] word, int idx, int i, int j) {
        if (idx == word.length) return true;
        if (outOfBoundary(i, j) || board[i][j] != word[idx]) return false;
        
        char c = board[i][j];
        board[i][j] = '#';
        
        boolean res = search(word, idx + 1, i - 1, j) 
                    || search(word, idx + 1, i + 1, j)
                    || search(word, idx + 1, i, j - 1)
                    || search(word, idx + 1, i, j + 1);
        
        board[i][j] = c;
        return res;
    }
    
    private boolean outOfBoundary(int i, int j) {
        return i < 0 || i >= raw || j < 0 || j >= col;
    }
}

// ???

class Solution {
   public boolean exist(char[][] board, String word) {
        int[][] tryCount = new int[board.length][board[0].length];
        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0, tryCount, visited)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i,int[][] tryCount, boolean[][] visited) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i] || tryCount[y][x]>121 || visited[y][x] ) return false;
        tryCount[y][x]++;
        board[y][x] ^= 256;
        visited[y][x] = true;
        boolean exist = exist(board, y, x+1, word, i+1,tryCount, visited)
            || exist(board, y, x-1, word, i+1,tryCount, visited)
            || exist(board, y+1, x, word, i+1,tryCount, visited)
            || exist(board, y-1, x, word, i+1,tryCount, visited);
        board[y][x] ^= 256;
        visited[y][x] = false;   
        return exist;
    }
}