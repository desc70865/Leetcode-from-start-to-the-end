/* 
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        UnionFind uf = new UnionFind(N + 1);
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < N; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (uf.parent[node2] != node2) {
                conflict = i;
            } else {
                uf.parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) cycle = i;
                else uf.union(node1, node2);
            }
        }
        if (conflict < 0) return edges[cycle];
        int[] p = edges[conflict];
        if (cycle < 0) return p;
        else return new int[] {uf.parent[p[1]], p[1]};
    }
}

class UnionFind {
    int[] ancestor;
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        ancestor = new int[n];
        for (int i = 0; i < n; i++) {
            ancestor[i] = i;
            parent[i] = i;
        }
    }

    public void union(int index1, int index2) {
        ancestor[find(index1)] = find(index2);
    }

    public int find(int index) {
        if (ancestor[index] != index) {
            ancestor[index] = find(ancestor[index]);
        }
        return ancestor[index];
    }
}