/* 
In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

 

Example 1:

Input: [[0,1],[1,0]]


Output: 2

Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]


Output: 4

 

Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
 */

class Solution {
    int[][] dirs = new int[][] {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int len = grid.length;
        if (grid[0][0] == 1 || grid[len - 1][len - 1] == 1) return -1;
        if (len == 1) return 1;
        Queue<int[]> queue = new LinkedList<>();
        int row = 0;
        int col = 0;
        queue.add(new int[] {row, col});
        grid[row][col] = 1;
        int res = 1;
        while (! queue.isEmpty()) {
            res++;
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int[] dir: dirs) {
                    row = cur[0] + dir[0];
                    col = cur[1] + dir[1];
                    if (row < 0 || row >= len || col < 0 || col >= len || grid[row][col] == 1) continue;
                    if (row == len - 1 && col == len - 1) return res;
                    queue.add(new int[] {row, col});
                    grid[row][col] = 1;
                }
            }
        }
        return -1;
    }
}



class Solution {
    int[][] dirs = new int[][] {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
    int[][] A;
    int len;

    public int shortestPathBinaryMatrix(int[][] grid) {
        len = grid.length;
        if (grid[0][0] == 1 || grid[len - 1][len - 1] == 1) return -1;
        if (len < 3) return len;
        this.A = grid;
        Set<Node> start = new HashSet<>();
        start.add(new Node(0, 0));
        Set<Node> end = new HashSet<>();
        end.add(new Node(len - 1, len - 1));
        return bfs(start, end, 1);
    }

    private int bfs(Set<Node> L, Set<Node> R, int counter) {
        if (L.isEmpty() || R.isEmpty()) return -1;
        counter++;
        for (Node n: L) {
            A[n.x][n.y] = 1;
        }
        Set<Node> nextSet = new HashSet<>();
        for (Node cur: L) {
            for (int[] dir: dirs) {
                int x = cur.x + dir[0];
                int y = cur.y + dir[1];
                if (x < 0 || y < 0 || x >= len || y >= len || A[x][y] == 1) continue;
                Node next = new Node(x, y);
                if (R.contains(next)) return counter;
                nextSet.add(next);
            }
        }
        if (nextSet.size() > R.size())
            return bfs(R, nextSet, counter);
        else
            return bfs(nextSet, R, counter);
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (! (obj instanceof Node)) return false;
        Node o = (Node) obj;
        return x == o.x && y == o.y;
    }

    public int hashCode() {
        return (x << 7) + y;
    }
}