/* 
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

 

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

Constraints:

|x| + |y| <= 300
 */

class Solution {
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x + y == 1) {
            return 3;
        }
        int res = Math.max(Math.max((x + 1) / 2, (y + 1) / 2), (x + y + 2) / 3);
        res += (res ^ x ^ y) & 1;
        return res;
    }
}



class Solution {
    private static final int BOUND = 5;
    private boolean[][] visited = new boolean[BOUND << 1][BOUND << 1];
    private int[][] directions = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    public int minKnightMoves(final int x, final int y) {
        int[] now = fastMove(x, y);
        int steps = Integer.MAX_VALUE;

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, now[2]));
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            if (position.x == now[0] && position.y == now[1]) {
                steps = Math.min(steps, position.steps);
                continue;
            }
            move(position, queue);
        }

        return steps;
    }

    private int[] fastMove(final int x, final int y) {
        int steps = 0;
        int newX = Math.abs(x);
        int newY = Math.abs(y);
        while (newX >= BOUND || newY >= BOUND) {
            if (newX > newY) {
                newX -= 2;
                newY -= newY > 2 ? 1 : -1;
            } else {
                newX -= newX > 2 ? 1 : -1;
                newY -= 2;
            }
            ++steps;
        }
        return new int[]{newX, newY, steps};
    }

    private void move(final Position position, final Queue<Position> queue) {
        for (final int[] direction : directions) {
            int newX = position.x + direction[0];
            int newY = position.y + direction[1];
            if (Math.abs(newX) >= BOUND || Math.abs(newY) >= BOUND) {
                continue;
            }
            if (!visited[newX + BOUND][newY + BOUND]) {
                visited[newX + BOUND][newY + BOUND] = true;
                queue.add(new Position(newX, newY, position.steps + 1));
            }
        }
    }

    private static final class Position {
        private int x;
        private int y;
        private int steps;

        private Position(final int x, final int y, final int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }
}