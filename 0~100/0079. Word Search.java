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
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (search(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }
    private boolean search(char[][] board, String word, int idx, int i, int j) {
        if (idx == word.length()) return true;
        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != word.charAt(idx)) return false;    
        char c = board[i][j];
        board[i][j] = '#';
        boolean res = search(board, word, idx + 1, i - 1, j) 
                || search(board, word, idx + 1, i + 1, j)
                || search(board, word, idx + 1, i, j - 1)
                || search(board, word, idx + 1, i, j + 1);
        board[i][j] = c;
        return res;
    }
}