/* 
A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:

-2: turn left 90 degrees
-1: turn right 90 degrees
1 <= x <= 9: move forward x units
Some of the grid squares are obstacles. 

The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])

If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)

Return the square of the maximum Euclidean distance that the robot will be from the origin.

 

Example 1:

Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: robot will go to (3, 4)
Example 2:

Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 

Note:

0 <= commands.length <= 10000
0 <= obstacles.length <= 10000
-30000 <= obstacle[i][0] <= 30000
-30000 <= obstacle[i][1] <= 30000
The answer is guaranteed to be less than 2 ^ 31.
 */

class Solution {
    Set<String> obstacle;
    int res = 0;
    int[] p = new int[] {1, -1};
    int[][] dirs = new int[][] { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    public int robotSim(int[] commands, int[][] obstacles) {
        obstacle = new HashSet<>();
        for (int[] o: obstacles) obstacle.add(o[0] + "@" + o[1]);
        dfs(0, 0, 1, commands, 0);
        return res;
    }

    private void dfs(int x, int y, int dir, int[] A, int idx) {
        if (idx == A.length) return;
        if (A[idx] < 0) {
            dir = (dir + p[A[idx] + 2] + 4) % 4;
            dfs(x, y, dir, A, idx + 1);
            return;
        }
        for (int i = 1; i <= A[idx]; i++) {
            x += dirs[dir][0];
            y += dirs[dir][1];
            if (obstacle.contains(x + "@" + y)) {
                x -= dirs[dir][0];
                y -= dirs[dir][1];
                break;
            }
            res = Math.max(res, x * x + y * y);
        }
        dfs(x, y, dir, A, idx + 1);
    }
}