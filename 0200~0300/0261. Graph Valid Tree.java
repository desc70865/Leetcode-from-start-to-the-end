/* 
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */

class Solution {
    int[] p;

    public boolean validTree(int n, int[][] edges) {
        int len = edges.length;
        if (len != n - 1) return false;
        p = new int[n];
        for (int i = 1; i < n; i++) {
            p[i] = i;
        }
        for (int[] edge: edges) {
            if (! union(edge[0], edge[1])) return false;
        }
        return true;
    }

    private int find(int x) {
        return x == p[x] ? x : (p[x] = find(p[x]));
    }

    private boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        p[a] = b;
        return true;
    }
}