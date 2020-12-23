/* 
There are n cities numbered from 1 to n. You are given an array edges of size n-1, where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi. There exists a unique path between each pair of cities. In other words, the cities form a tree.

A subtree is a subset of cities where every city is reachable from every other city in the subset, where the path between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in one subtree that is not present in the other.

For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the subtree is equal to d.

Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum distance between any two cities is equal to d.

Notice that the distance between the two cities is the number of edges in the path between them.

 

Example 1:



Input: n = 4, edges = [[1,2],[2,3],[2,4]]
Output: [3,4,0]
Explanation:
The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
No subtree has two nodes where the max distance between them is 3.
Example 2:

Input: n = 2, edges = [[1,2]]
Output: [1]
Example 3:

Input: n = 3, edges = [[1,2],[2,3]]
Output: [2,1]
 

Constraints:

2 <= n <= 15
edges.length == n-1
edges[i].length == 2
1 <= ui, vi <= n
All pairs (ui, vi) are distinct.
 */

class Solution {
    static final int INF = Integer.MAX_VALUE;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        int[] dp = new int[1 << n];
        for (int[] edge: edges) {
            dist[edge[0] - 1][edge[1] - 1] = 1;
            dist[edge[1] - 1][edge[0] - 1] = 1;
            dp[(1 << edge[0] - 1) + (1 << edge[1] - 1)] = 1;
        }
        // Floyed
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        for (int j = 1; j < dp.length; j++) {
            if (dp[j] == 0) continue;
            for (int i = 0; i < n; i++) {
                if (((1 << i) & j) != 0 || dp[j + (1 << i)] != 0) continue;
                for (int k = 0; k < n; k++) {
                    if (((1 << k) &j ) != 0 && dist[i][k] == 1) {
                        dp[j + (1 << i)] = dp[j];
                        break;
                    }
                }
                if (dp[j + (1 << i)] == 0) continue;
                for (int k = 0; k < n; k++) {
                    if (((1 << k) & j) != 0) {
                        dp[j + (1 << i)] = Math.max(dp[j + (1 << i)], dist[i][k]);
                    }
                }
            }
        }
        int[] ans = new int[n - 1];
        for (int j = 0; j < dp.length; j++) {
            if (dp[j] != 0) ans[dp[j] - 1]++;
        }
        return ans;
    }
}