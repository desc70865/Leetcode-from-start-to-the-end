/* 
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */

class Solution {
    // static final int INF = Integer.MAX_VALUE;
    int[] dir = new int[] {0, 1, 0, -1, 0};
    int m, n;

    public void wallsAndGates(int[][] rooms) {
        m = rooms.length;
        if (m == 0) return;
        n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int x, int y, int dis) {
        if (x < 0 || x >= m || y < 0 || y >= n) return;
        int cur = rooms[x][y];
        if (cur < 0 || cur < dis || cur == dis && cur > 0) return;
        rooms[x][y] = dis;
        for (int i = 0; i < 4; i++) {
            dfs(rooms, x + dir[i], y + dir[i + 1], dis + 1);
        }
    }
}