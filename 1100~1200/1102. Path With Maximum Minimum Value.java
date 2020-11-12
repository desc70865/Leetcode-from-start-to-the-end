/* 
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

 

Example 1:



Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 
Example 2:



Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:



Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
 

Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9
 */

class Solution {
    int m, n;
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int maximumMinimumPath(int[][] A) {
        m = A.length;
        n = A[0].length;

        int L = 0;
        int R = A[0][0];
        while (L <= R) {
            int M = L + R >> 1;
            if (dfs(A, M, 0, 0, new boolean[m][n])) L = M + 1;
            else R = M - 1;
        }
        return L - 1;
    }

    private boolean dfs(int[][] A, int min, int x, int y, boolean[][] v) {
        if (x < 0 || y < 0 || x >= m || y >= n || A[x][y] < min || v[x][y]) return false;
        if (x == m - 1 && y == n - 1) return true;
        v[x][y] = true;
        for (int[] dir: dirs) {
            if (dfs(A, min, x + dir[0], y + dir[1], v)) return true;
        }
        return false;
    }
}