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
    int M, N;
    public int orangesRotting(int[][] grid) {
        M = grid.length; N = grid[0].length;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] >= 2) {
                        trans(grid, i, j);
                    } 
                }
            }
        }
    }
    
    private void trans(int[][] grid, int i, int j) {
        rot(grid, i-1, j);
        rot(grid, i+1, j);
        rot(grid, i, j-1);
        rot(grid, i, j+1);
    }
    
    private void rot(int[][] grid, int i, int j) {
        if (i < 0 || i >= M || j < 0 || j >= N) return;
        grid[i][j] <<= 1;
    }
}

// ??? 保留, 更新

class Solution {
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 1) {
                    count++;
                } else if (grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                }
            }
        }
        int round = 0;
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] orange = queue.poll();
                int r = orange[0];
                int c = orange[1];
                if (r-1 >= 0 && grid[r-1][c] == 1) {
                    grid[r-1][c] = 2;
                    count--;
                    queue.add(new int[]{r-1, c});
                }
                if (r+1 < M && grid[r+1][c] == 1) {
                    grid[r+1][c] = 2;
                    count--;
                    queue.add(new int[]{r+1, c});
                }
                if (c-1 >= 0 && grid[r][c-1] == 1) {
                    grid[r][c-1] = 2;
                    count--;
                    queue.add(new int[]{r, c-1});
                }
                if (c+1 < N && grid[r][c+1] == 1) {
                    grid[r][c+1] = 2;
                    count--;
                    queue.add(new int[]{r, c+1});
                }
            }
        }
        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }
}