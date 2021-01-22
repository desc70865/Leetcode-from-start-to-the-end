/* 
Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

 

Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 

Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct.
 */

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] graph = new int[m][4];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < 3; ++j) {
                graph[i][j] = edges[i][j];
            }
            graph[i][3] = i;
        }
        Arrays.sort(graph, (u, v) -> u[2] - v[2]);

        // 计算 value
        UnionFind ufStd = new UnionFind(n);
        int value = 0;
        for (int i = 0; i < m; ++i) {
            if (ufStd.union(graph[i][0], graph[i][1])) {
                value += graph[i][2];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            ans.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < m; ++i) {
            // 判断是否是关键边
            UnionFind uf = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; ++j) {
                if (i != j && uf.union(graph[j][0], graph[j][1])) {
                    v += graph[j][2];
                }
            }
            if (uf.setCount != 1 || (uf.setCount == 1 && v > value)) {
                ans.get(0).add(graph[i][3]);
                continue;
            }

            // 判断是否是伪关键边
            uf = new UnionFind(n);
            uf.union(graph[i][0], graph[i][1]);
            v = graph[i][2];
            for (int j = 0; j < m; ++j) {
                if (i != j && uf.union(graph[j][0], graph[j][1])) {
                    v += graph[j][2];
                }
            }
            if (v == value) {
                ans.get(1).add(graph[i][3]);
            }
        }
      
        return ans;
    }
}

// 并查集模板
class UnionFind {
    int[] parent;
    int[] size;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }
    
    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        } else if (size[x] < size[y]) {
            parent[x] = y;
            size[y] += size[x];
        } else {
            parent[y] = x;
            size[x] += size[y];
        }
        setCount--;
        return true;
    }
    
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}