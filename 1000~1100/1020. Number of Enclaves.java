/* 
Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:

Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: 
There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
Example 2:

Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: 
All 1s are either on the boundary or can reach the boundary.
 

Note:

1 <= A.length <= 500
1 <= A[i].length <= 500
0 <= A[i][j] <= 1
All rows have the same size.
 */

class Solution {
    int m, n;
    int[][] dirs = new int[][] { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };

    public int numEnclaves(int[][] A) {
        m = A.length; n = A[0].length;
        for (int j = 0; j < n - 1; j++) dfs(A, 0, j);
        for (int j = 1; j < n; j++) dfs(A, m - 1, j);
        for (int i = 1; i < m; i++) dfs(A, i, 0);
        for (int i = 0; i < m - 1; i++) dfs(A, i, n - 1);

        int res = 0;
        for (int[] l: A) for (int n: l) res += n;
        return res;
    }

    private void dfs(int[][] A, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (A[i][j] != 1) return;
        A[i][j]--;
        for (int[] dir: dirs) dfs(A, i + dir[0], j + dir[1]);
    }
}