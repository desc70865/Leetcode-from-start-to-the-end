/* 
You are given an m x n grid grid of values 0, 1, or 2, where:

each 0 marks an empty land that you can pass by freely,
each 1 marks a building that you cannot pass through, and
each 2 marks an obstacle that you cannot pass through.
You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 

Example 1:


Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
Example 2:

Input: grid = [[1,0]]
Output: 1
Example 3:

Input: grid = [[1]]
Output: -1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is either 0, 1, or 2.
There will be at least one building in the grid.
 */

class Solution {
    static final int INF = 1_000_000_007;
    int[] dirs = {0, 1, 0, -1, 0};
    int m, n;

    public int shortestDistance(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int min = INF;
        int building = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    building++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    min = Math.min(min, bfs(grid, i, j, building));
                }
            }
        }
        return min == INF ? -1 : min;
    }

    private int bfs(int[][] grid, int startX, int startY, int building) {
        int ans = 0;
        boolean[][] v = new boolean[m][n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(startX * n + startY);
        v[startX][startY] = true;
        int step = 1;
        while (queue.size() > 0 && building > 0) {
            int size = queue.size();
            while (size-- > 0 && building > 0) {
                int coordinate = queue.poll();
                int x = coordinate / n;
                int y = coordinate % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i], ny = y + dirs[i + 1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || v[nx][ny]) continue;
                    if (grid[nx][ny] == 1) {
                        building--;
                        ans += step;
                    } else if (grid[nx][ny] == 0) {
                        queue.offer(nx * n + ny);
                    }
                    v[nx][ny] = true;
                }
            }
            step++;
        }
        return building == 0 ? ans : INF;
    }
}

// while buildingNum << emptyNum

class Solution {
    static final int INF = (int) 1e4 + 1;
    int[] dirs = {0, 1, 0, -1, 0};
    int m, n;
    int[][] ans;
    int[][] cnt;

    public int shortestDistance(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int building = 0;
        this.cnt = new int[m][n];
        this.ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    building++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (! bfs(grid, i, j, building)) {
                        return -1;
                    }
                }
            }
        }
        int min = INF;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (cnt[i][j] != building) continue;
                    min = Math.min(min, ans[i][j]);
                }
            }
        }
        return min == INF ? -1 : min;
    }

    private boolean bfs(int[][] grid, int startX, int startY, int building) {
        boolean[][] v = new boolean[m][n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(startX * n + startY);
        v[startX][startY] = true;
        int step = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size-- > 0) {
                int coordinate = queue.poll();
                int x = coordinate / n;
                int y = coordinate % n;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i], ny = y + dirs[i + 1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || v[nx][ny]) continue;
                    if (grid[nx][ny] == 0) {
                        queue.offer(nx * n + ny);
                        ans[nx][ny] += step;
                        cnt[nx][ny]++;
                    } else if (grid[nx][ny] == 1) {
                        building--;
                    } else if (grid[nx][ny] == 2) {
                        ;
                    }
                    v[nx][ny] = true;
                }
            }
            step++;
        }
        return building == 1;
    }
}