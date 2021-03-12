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
    int[] dirs = new int[] {0, 1, 0, -1, 0};
    int m, n;

    public boolean exist(char[][] board, String word) {
        char[] target = word.toCharArray();
        m = board.length; n = board[0].length;
        if (target.length > m * n) return false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, target, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int x, int y, char[] target, int idx) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (board[x][y] != target[idx]) return false;
        if (idx == target.length - 1) return true;
        board[x][y] ^= 64;
        for (int i = 0; i < 4; i++) {
            if (dfs(board, x + dirs[i], y + dirs[i + 1], target, idx + 1)) {
                board[x][y] ^= 64;
                return true;
            }
        }
        board[x][y] ^= 64;
        return false;
    }
}