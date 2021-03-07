/* 
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

 

Example 1:



Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
 */

class Solution {
    int[] dirs = new int[] {0, 1, 0, -1, 0};

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) fresh++;
                else if (grid[i][j] == 2) queue.offer(i * n + j);
            }
        }
        int level = 0;
        while (fresh > 0 && queue.size() > 0) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int x = cur / n;
                int y = cur % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i];
                    if (nx < 0 || nx >= m) continue;
                    int ny = y + dirs[i + 1];
                    if (ny < 0 || ny >= n) continue;
                    if (grid[nx][ny] == 1) {
                        grid[nx][ny] = 0;
                        fresh--;
                        queue.offer(nx * n + ny);
                    }
                }
            }
            level++;
        }
        return fresh == 0 ? level : -1;
    }
}