/* 
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 

Example 1:



Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2
 

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */

class Solution {
    // #Edges = O(V^2), PQ O(ElogV) = O(V^2logV)
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] G = new int[N + 1][N + 1];
        int INF = (int) 1e9;
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= N; j++)
                G[i][j] = i == j ? 0 : INF;
        for (int[] e: times)
            G[e[0]][e[1]] = e[2];
        
        int[] dis = G[K].clone();
        boolean[] vis = new boolean[N + 1];
        
        for (int i = 0; i < N - 1; i++) {
            int curMin = INF, p = -1;
            for (int j = 1; j <= N; j++) {
                if (! vis[j] && dis[j] < curMin) {
                    curMin = dis[j];
                    p = j;
                }
            }
            vis[p] = true;
            for (int j = 1; j <= N; j++)
                if (! vis[j] && dis[j] > dis[p] + G[p][j])
                    dis[j] = dis[p] + G[p][j];
        }
        
        int ans = 0;
        for (int i = 1; i <= N; i++)
            ans = Math.max(ans, dis[i]);
        return ans == INF ? -1 : ans;
    }
}