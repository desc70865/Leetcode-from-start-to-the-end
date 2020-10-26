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
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n;
    char[] str;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        if (word.length() > m * n) return false;
        str = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(board, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean helper(char[][] A, int idx, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n) return false;
        if (A[x][y] != str[idx]) return false;
        if (idx == str.length - 1) return true;
        A[x][y] ^= 64;
        boolean res = false;
        for (int[] dir: dirs) {
            if (helper(A, idx + 1, x + dir[0], y + dir[1])) {
                res = true;
                break;
            }
        }
        A[x][y] ^= 64;
        return res;
    }
}