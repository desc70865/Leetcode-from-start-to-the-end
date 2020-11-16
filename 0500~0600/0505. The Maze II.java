/* 
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.

 

Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */

class Solution {
    private Queue<int[]> queue;
    private int[][] dis;
    int m, n;

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        queue = new LinkedList<>();
        dis = new int[m][n];
        for (int[] line: dis) Arrays.fill(line, Integer.MAX_VALUE);
        dis[start[0]][start[1]] = 0;
        bfs(maze, start, destination);
        return dis[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dis[destination[0]][destination[1]];
    }

    private void bfs(int[][] maze, int[] start, int[] dest) {
        if ((start[0] == dest[0]) && (start[1] == dest[1])) {
            return;
        }

        //左边
        int nLeft = start[1];
        int nLeftStep = dis[start[0]][start[1]];
        while ((nLeft > 0) && (0 == maze[start[0]][nLeft - 1])) {
            nLeft--;
            nLeftStep++;
        }
        if (nLeftStep < dis[start[0]][nLeft]) {
            dis[start[0]][nLeft] = nLeftStep;
            queue.add(new int[]{start[0], nLeft});
        }

        //右边
        int nRight = start[1];
        int nRightStep = dis[start[0]][start[1]];
        while ((nRight < n - 1) && (0 == maze[start[0]][nRight + 1])) {
            nRight++;
            nRightStep++;
        }
        if (nRightStep < dis[start[0]][nRight]) {
            dis[start[0]][nRight] = nRightStep;
            queue.add(new int[]{start[0], nRight});
        }

        //上边
        int nUp = start[0];
        int nUpStepCount = dis[start[0]][start[1]];
        while ((nUp > 0) && (0 == maze[nUp - 1][start[1]])) {
            nUp--;
            nUpStepCount++;
        }
        if (nUpStepCount < dis[nUp][start[1]]) {
            dis[nUp][start[1]] = nUpStepCount;
            queue.add(new int[]{nUp, start[1]});
        }

        //下边
        int nDown = start[0];
        int nDownStepCount = dis[start[0]][start[1]];
        while ((nDown < m - 1) && (0 == maze[nDown + 1][start[1]])) {
            nDown++;
            nDownStepCount++;
        }
        if (nDownStepCount < dis[nDown][start[1]]) {
            dis[nDown][start[1]] = nDownStepCount;
            queue.add(new int[]{nDown, start[1]});
        }

        while (!queue.isEmpty()) {
            int[] arrPopStart = queue.poll();
            bfs(maze, arrPopStart, dest);
        }
    }
}