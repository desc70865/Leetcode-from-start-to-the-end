/* 
Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).

 

Example 1:

Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
Output: [0,1,-1]
Example 3:

Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
Output: [0,-1,-1]
Example 4:

Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
Output: [0,1,2]
Example 5:

Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
Output: [0,1,1]
 

Constraints:

1 <= n <= 100
red_edges.length <= 400
blue_edges.length <= 400
red_edges[i].length == blue_edges[i].length == 2
0 <= red_edges[i][j], blue_edges[i][j] < n
 */

class Solution {
    static final int RED = 0, BLUE = 1, INF = Integer.MAX_VALUE;

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Set<Integer>[][] graph = new HashSet[2][n];
        for (int i = 0; i < n; i ++) {
            graph[RED][i] = new HashSet<>();
            graph[BLUE][i] = new HashSet<>();
        }
        for (int[] edge: red_edges) {
            graph[RED][edge[0]].add(edge[1]); 
        }
        for (int[] edge: blue_edges) {
            graph[BLUE][edge[0]].add(edge[1]); 
        }
        int[][] dis = new int[2][n];
        for (int i = 1; i < n; i++) {
            dis[RED][i] = INF;
            dis[BLUE][i] = INF;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, RED});
        q.add(new int[]{0, BLUE});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int color = cur[1];
            for (int next: graph[1 - color][node]) {
                if (dis[1 - color][next] == INF) {
                    dis[1 - color][next] = 1 + dis[color][node];
                    q.add(new int[]{next, 1 - color});
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int min = Math.min(dis[RED][i], dis[BLUE][i]);
            ans[i] = min == INF ? -1 : min;
        }
        return ans;
    }
}