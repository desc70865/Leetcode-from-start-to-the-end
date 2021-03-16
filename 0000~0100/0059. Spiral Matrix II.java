/* 
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */

class Solution {
    int[][] ans;
    int idx = 1;

    public int[][] generateMatrix(int n) {
        ans = new int[n][n];
        dfs(0, n);
        return ans;
    }

    private void dfs(int x, int side) {
        if (side < 1) return;
        if (side == 1) {
            ans[x][x] = idx;
            return;
        }
        for (int j = x; j < x + side - 1; j++) ans[x][j] = idx++;
        for (int i = x; i < x + side - 1; i++) ans[i][x + side - 1] = idx++;
        for (int j = x + side - 1; j > x; j--) ans[x + side - 1][j] = idx++;
        for (int i = x + side - 1; i > x; i--) ans[i][x] = idx++;
        dfs(x + 1, side - 2);
    }
}