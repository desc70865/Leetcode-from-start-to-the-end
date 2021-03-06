/* 
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 */

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for (int[] edge: edges) unionFind.union(edge[0], edge[1]);
        return unionFind.res;
    }
}

class UnionFind {
    public int[] res;
    public int[] roots;

    public UnionFind(int size) {
        res = new int[2];
        this.roots = new int[size];
        for (int i = 0; i < size; i++) {
            roots[i] = i;
        }
    }

    public int find(int x) {
        if (roots[x] != x) {
            roots[x] = find(roots[x]);
        }
        return roots[x];
    }
    
    public void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (root1 != root2) {
            roots[root2] = root1;
        } else {
            res[0] = x;
            res[1] = y;
        }
    }
}



class Solution {
    int[] p;
    int[] ans;

    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length + 1;
        p = new int[len];
        for (int i = 1; i < len; i++) {
            p[i] = i;
        }
        ans = new int[2];
        for (int[] edge: edges) {
            union(edge[0], edge[1]);
        }
        return ans;
    }

    private void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x == y) {
            ans[0] = a;
            ans[1] = b;
        } else {
            p[y] = x;
        }
    }

    private int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }
}