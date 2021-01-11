/* 
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
 */

class Solution {
    static final int INF = 200;

    public int maxDistance(int[][] grid) {
        int len = grid.length;
        int[][] map = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 1) continue;
                map[i][j] = INF;
                if (i - 1 >= 0) {
                    map[i][j] = Math.min(map[i][j], map[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    map[i][j] = Math.min(map[i][j], map[i][j - 1] + 1);
                }
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= 0; j--) {
                if (grid[i][j] == 1) continue;
                if (i + 1 < len) {
                    map[i][j] = Math.min(map[i][j], map[i + 1][j] + 1);
                }
                if (j + 1 < len) {
                    map[i][j] = Math.min(map[i][j], map[i][j + 1] + 1);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, map[i][j]);
                }
            }
        }

        return ans == INF ? -1 : ans;
    }
}