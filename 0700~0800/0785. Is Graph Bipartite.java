/* 
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/is-graph-bipartite/solution/hao-jia-huo-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    int[] p;
    public boolean isBipartite(int[][] graph) {
        int N = graph.length;
        int size = 2 * N + 1;
        p = new int[size];
        for (int i = 0; i < size; i++) p[i] = i;
        for (int i = 0; i < N; i++) {
            int x = find(i);
            int P = find(i + N);
            for (int q: graph[i]) {
                int y = find(q);
                if (x == y) return false;
                p[x] = p[find(q + N)];
                p[y] = p[P];
            }
        }
        return true;
    }

    private int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }
}



class Solution {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n; ++i) {
            if (color[i] != UNCOLORED) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(i);
            color[i] = RED;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                int cNei = color[node] == RED ? GREEN : RED;
                for (int neighbor : graph[node]) {
                    if (color[neighbor] == UNCOLORED) {
                        queue.offer(neighbor);
                        color[neighbor] = cNei;
                    } else if (color[neighbor] != cNei) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}



class Solution {
    Boolean[] color;

    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        color = new Boolean[len];
        for (int i = 0; i < len; i++) {
            if (color[i] == null && ! dfs(i, true, graph)) return false;
        }
        return true;
    }

    private boolean dfs(int idx, boolean c, int[][] graph) {
        if (color[idx] != null) return color[idx] == c;
        color[idx] = c;
        for (int next: graph[idx]) {
            if (! dfs(next, ! c, graph)) return false;
        }
        return true;
    }
}