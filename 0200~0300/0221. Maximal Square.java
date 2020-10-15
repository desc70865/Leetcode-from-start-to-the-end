/* 
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
 */

class Solution {
    public int maximalSquare(char[][] M) {
        int m = M.length;
        if (m == 0) return 0;
        int n = M[0].length;
        int[][] t = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[i][j] = M[i][j] - '0';
            }
        }
        int N = helper(t);
        return N * N;
    }

    private int helper(int[][] M) {
        int m = M.length, n = M[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) res += M[0][i];
        for (int i = 1; i < m; i++) res += M[i][0];
        if (res > 0) res = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (M[i][j] == 0) continue;
                M[i][j] += Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1]);
                res = Math.max(res, M[i][j]);
            }
        }
        return res;
    }
}