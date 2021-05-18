/* 
You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:


Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:


Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 

Constraints:

n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] does not contain i.
If graph[a] contains b, then graph[b] contains a.
The input graph is always connected.
 */

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Deque<Integer> queue = new ArrayDeque<>();
        int[][] dp = new int[1 << n][n];
        for (int[] row: dp) {
            Arrays.fill(row, n * n);
        }
        for (int x = 0; x < n; ++x) {
            queue.offer((1 << x + 4) + x);
            dp[1 << x][x] = 0;
        }
        for (int step = 0; queue.size() > 0; ++step) {
            for (int size = queue.size(); size > 0; --size) {
                int mask = queue.poll();
                int path = mask >> 4;
                int node = mask % 16;
                if (path == (1 << n) - 1) {
                    return step;
                }
                for (int next: graph[node]) {
                    int newPath = path | (1 << next);
                    if (dp[newPath][next] > step + 1) {
                        dp[newPath][next] = step + 1;
                        queue.offer((newPath << 4) + next);
                    }
                }
            }
        }
        return -1;
    }
}