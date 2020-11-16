/* 
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).

 

Example 1:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
Example 2:


Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false
 

Constraints:

1 <= maze.length, maze[i].length <= 100
maze[i][j] is 0 or 1.
start.length == 2
destination.length == 2
0 <= startrow, destinationrow <= maze.length
0 <= startcol, destinationcol <= maze[i].length
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The maze contains at least 2 empty spaces.
 */

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length, columns = maze[0].length;
        boolean[][] visited = new boolean[rows][columns];
        visited[start[0]][start[1]] = true;
        return depthFirstSearch(maze, visited, start, destination);
    }

    public boolean depthFirstSearch(int[][] maze, boolean[][] visited, int[] start, int[] destination) {
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        int row = start[0], column = start[1];
        int rows = maze.length, columns = maze[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int stopRow = row, stopColumn = column;
            int curRow = row + direction[0], curColumn = column + direction[1];
            while (curRow >= 0 && curRow < rows && curColumn >= 0 && curColumn < columns && maze[curRow][curColumn] == 0) {
                stopRow = curRow;
                stopColumn = curColumn;
                curRow += direction[0];
                curColumn += direction[1];
            }
            if (!visited[stopRow][stopColumn]) {
                visited[stopRow][stopColumn] = true;
                int[] newStart = {stopRow, stopColumn};
                boolean flag = depthFirstSearch(maze, visited, newStart, destination);
                if (flag)
                    return true;
            }
        }
        return false;
    }
}