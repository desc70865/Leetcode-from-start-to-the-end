/* 
There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.

Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1. 

 

Example 1:



Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:



Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
Example 4:

Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
Output: 0
 

Constraints:

1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
There are no repeated connections.
No two computers are connected by more than one cable.
 */

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (n - 1 > len) return -1;
        UnionFind uf = new UnionFind(n);
        for (int[] c: connections) {
            uf.union(c[0], c[1]);
        }
        return uf.size;
    }
}

class UnionFind {
    int[] p;
    int size;

    public UnionFind(int n) {
        this.p = new int[n];
        this.size = n - 1;
        for (int i = 1; i < n; i++) {
            p[i] = i;
        }
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        else if (a < b) p[b] = a;
        else p[a] = b;
        this.size--;
    }
}