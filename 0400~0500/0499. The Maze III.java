/* 
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.

Given the m x n maze, the ball's position ball and the hole's position hole, where ball = [ballrow, ballcol] and hole = [holerow, holecol], return a string instructions of all the instructions that the ball should follow to drop in the hole with the shortest distance possible. If there are multiple valid instructions, return the lexicographically minimum one. If the ball can't drop in the hole, return "impossible".

If there is a way for the ball to drop in the hole, the answer instructions should contain the characters 'u' (i.e., up), 'd' (i.e., down), 'l' (i.e., left), and 'r' (i.e., right).

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).

 

Example 1:


Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [0,1]
Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
Example 2:


Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [3,0]
Output: "impossible"
Explanation: The ball cannot reach the hole.
Example 3:

Input: maze = [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]], ball = [0,4], hole = [3,5]
Output: "dldr"
 

Constraints:

m == maze.length
n == maze[i].length
1 <= m, n <= 100
maze[i][j] is 0 or 1.
ball.length == 2
hole.length == 2
0 <= ballrow, holerow <= m
0 <= ballcol, holecol <= n
Both the ball and the hole exist in an empty space, and they will not be in the same position initially.
The maze contains at least 2 empty spaces.
 */

class Solution {
    static int[] dx = new int[]{0, -1, 1, 0};
    static int[] dy = new int[]{1, 0, 0, -1};
    static char[] dirStr = {'d', 'l', 'r', 'u'};

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        String[][] path = new String[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                path[i][j] = "";
            }
        }
        int[][] dp = new int[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(ball);
        while (! queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = pos[0], nx = pos[1];
                for (; 0 <= ny + dy[i] && ny + dy[i] < m && 0 <= nx + dx[i]
                    && nx + dx[i] < n && maze[ny + dy[i]][nx + dx[i]] == 0; ny += dy[i], nx += dx[i]) {
                    if (ny == hole[0] && nx == hole[1]) {
                        break;
                    }
                }
                if (ny == pos[0] && nx == pos[1]) {
                    continue;
                }
                int step = dp[pos[0]][pos[1]] + Math.abs(ny - pos[0]) + Math.abs(nx - pos[1]);
                String str = path[pos[0]][pos[1]] + dirStr[i];
                if (dp[ny][nx] == 0 || dp[ny][nx] > step || dp[ny][nx] == step && str.compareTo(path[ny][nx]) < 0) {
                    dp[ny][nx] = step;
                    path[ny][nx] = str;
                    if (ny == hole[0] && nx == hole[1]) {
                        continue;
                    }
                    queue.offer(new int[] {ny, nx});
                }
            }
        }
        return path[hole[0]][hole[1]].length() == 0 ? "impossible" : path[hole[0]][hole[1]];
    }
}



class Solution {
    int m = 0, n = 0;
    int[][] maze;
    int[] hole;
    int[][] dp;
    String res = "";

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return "impossible";
        }
        this.m = maze.length;
        this.n = maze[0].length;
        this.maze = maze;
        this.hole = hole;
        this.dp = new int[m][n];
        for (int[] row: dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[ball[0]][ball[1]] = 0;
        dfs(ball, new StringBuilder(), -1, 0);
        return res.length() == 0 ? "impossible" : res;
    }

    static char[] dirStr = {'d', 'l', 'r', 'u'};
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    int minLen = Integer.MAX_VALUE;

    public void dfs(int[] pos, StringBuilder path, int prev, int step) {
        for (int i = 0; i < 4; ++i) {
            if (prev + i == 3) {
                continue;
            }
            int x = pos[0];
            int y = pos[1];
            int cntStep = step;
            while (0 <= x + dx[i] && x + dx[i] < m && 0 <= y + dy[i] && y + dy[i] < n && maze[x + dx[i]][y + dy[i]] == 0) {
                x += dx[i];
                y += dy[i];
                ++cntStep;
                if (cntStep >= minLen) {
                    return;
                }
                if (x == hole[0] && y == hole[1]) {
                    res = new StringBuilder(path).append(dirStr[i]).toString();
                    minLen = cntStep;
                    return;
                }
            }
            if ((x != hole[0] || y != hole[1]) && (cntStep < dp[x][y])) {
                path.append(dirStr[i]);
                dp[x][y] = cntStep;
                dfs(new int[]{x, y}, path, i, cntStep);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}