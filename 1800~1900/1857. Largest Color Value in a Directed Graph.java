/* 
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

 

Example 1:



Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
Example 2:



Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.
 

Constraints:

n == colors.length
m == edges.length
1 <= n <= 105
0 <= m <= 105
colors consists of lowercase English letters.
0 <= aj, bj < n
 */

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        char[] color = colors.toCharArray();
        int n = color.length;
        int[] in = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e: edges) {
            ++in[e[1]];
            graph.computeIfAbsent(e[0], z -> new ArrayList<>()).add(e[1]);
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        int cnt = 0;
        int[][] f = new int[n][26];
        while (queue.size() > 0) {
            ++cnt;
            int u = queue.poll();
            ++f[u][color[u] - 'a'];
            if (! graph.containsKey(u)) {
                continue;
            }
            for (int v: graph.get(u)) {
                --in[v];
                for (int c = 0; c < 26; ++c) {
                    f[v][c] = Math.max(f[v][c], f[u][c]);
                }
                if (in[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (cnt != n) {
            return -1;
        }
        int ans = 0;
        for (int[] e: f) {
            for (int p: e) {
                ans = Math.max(ans, p);
            }
        }
        return ans;
    }
}