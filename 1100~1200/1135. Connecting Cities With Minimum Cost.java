/* 
There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.

 

Example 1:



Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:



Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.
 

Note:

1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
 */

class Solution {
    int[] parent;
    
    // Minimum Spanning Tree, MST
    // @Kruskal
    // greedy + unionfind
    public int minimumCost(int N, int[][] connections) {
        // sort connections by cost from small to large
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        parent = new int[N+1];
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
        }

        int cost = 0;
        for (int[] edge: connections) {
            if (union(edge[0], edge[1])) {
                cost += edge[2];
            }
        }
        // System.out.println(Arrays.toString(parent));

        int root = find(1);
        for (int i = 2; i <= N; i++) {
            if (root != find(i))
                return -1;
        }
        return cost;
    }

    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[a] = b;
        return true;
    }
}