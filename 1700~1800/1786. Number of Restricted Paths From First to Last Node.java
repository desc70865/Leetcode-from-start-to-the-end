/* 
There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.

A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.

The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.

Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.

 

Example 1:


Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
Output: 3
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
1) 1 --> 2 --> 5
2) 1 --> 2 --> 3 --> 5
3) 1 --> 3 --> 5
Example 2:


Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
Output: 1
Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 

Constraints:

1 <= n <= 2 * 104
n - 1 <= edges.length <= 4 * 104
edges[i].length == 3
1 <= ui, vi <= n
ui != vi
1 <= weighti <= 105
There is at most one edge between any two nodes.
There is at least one path between any two nodes.
 */

class Solution {
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] e: edges) {
            map.computeIfAbsent(e[0], z -> new ArrayList<>()).add(new int[] {e[1], e[2]});
            map.computeIfAbsent(e[1], z -> new ArrayList<>()).add(new int[] {e[0], e[2]});
        }
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[n] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {n, 0});
        boolean[] v = new boolean[n + 1];
        while (pq.size() > 0) {
            int cur = pq.poll()[0];
            if (v[cur]) continue;
            v[cur] = true;
            if (! map.containsKey(cur)) continue;
            for (int[] arr: map.get(cur)) {
                int next = arr[0];
                dis[next] = Math.min(dis[next], dis[cur] + arr[1]);
                if (v[next]) continue;
                pq.offer(new int[] {next, dis[next]});
            }
        }
        int[][] aux = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            aux[i][0] = i;
            aux[i][1] = dis[i];
        }
        Arrays.sort(aux, (a, b) -> a[1] - b[1]);
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int[] d: aux) {
            if (! map.containsKey(d[0])) continue;
            for (int[] next: map.get(d[0])) {
                if (d[1] > dis[next[0]]) {
                    dp[d[0]] += dp[next[0]];
                }
            }
            dp[d[0]] %= 1_000_000_007;
            if (d[0] == 1) break;
        }
        return (int) dp[1];
    }
}