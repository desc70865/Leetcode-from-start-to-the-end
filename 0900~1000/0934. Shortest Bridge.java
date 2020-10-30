/* 
In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: A = [[0,1],[1,0]]
Output: 1
Example 2:

Input: A = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Constraints:

2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
 */

class Solution {
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n;
    Queue<Node> queue;
    int res;
    public int shortestBridge(int[][] A) {
        m = A.length;
        n = A[0].length;
        queue = new LinkedList<>();
        res = 0;

        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) continue;
                dfs(A, i, j);
                bfs(A);
                return res;
            }
        }
        return -1;
    }

    private void bfs(int[][] A) {
        while (! queue.isEmpty()) {
            res++;
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                for (int[] dir: dirs) {
                    int x = node.x + dir[0];
                    int y = node.y + dir[1];
                    if (f(A, x, y)) continue;
                    if (A[x][y] == 1) return;
                    queue.offer(new Node(x, y));
                    A[x][y] = 2;
                }
            }
        }
    }

    private void dfs(int[][] A, int x, int y) {
        if (f(A, x, y)) return;
        if (A[x][y] == 0) {
            queue.offer(new Node(x, y));
            return;
        }
        A[x][y] = 2;
        for (int[] dir: dirs) {
            dfs(A, x + dir[0], y + dir[1]);
        }
    }

    private boolean f(int[][] A, int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n || A[x][y] == 2;
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}