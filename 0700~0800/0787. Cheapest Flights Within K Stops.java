/* 
There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 

Constraints:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
 */

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight: flights)
            graph[flight[0]][flight[1]] = flight[2];
        Map<Integer, Integer> best = new HashMap();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, src});

        while (! pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0], k = info[1], place = info[2];
            if (k > K+1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE))
                continue;
            if (place == dst)
                return cost;
            for (int nei = 0; nei < n; ++nei) if (graph[place][nei] > 0) {
                int newcost = cost + graph[place][nei];
                if (newcost < best.getOrDefault((k+1) * 1000 + nei, Integer.MAX_VALUE)) {
                    pq.offer(new int[]{newcost, k+1, nei});
                    best.put((k+1) * 1000 + nei, newcost);
                }
            }
        }
        return -1;
    }
}

// ↑ Dijkstra

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dist = new int[2][n];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        dist[0][src] = dist[1][src] = 0;

        for (int i = 0; i <= K; ++i)
            for (int[] flight: flights)
                dist[i & 1][flight[1]] = Math.min(
                        dist[i & 1][flight[1]], 
                        dist[~i & 1][flight[0]] + flight[2]);

        return dist[K & 1][dst] < INF ? dist[K & 1][dst] : -1;
    }
}

// ↑ dp

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dist = new int[n];
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[src] = 0;
        for (int i = 0; i <= K; i++) {
            int[] di = dist.clone();
            for (int[] f: flights) {
                int a = f[0], b = f[1], w = f[2];
                if (dist[b] > di[a] + w) {
                    dist[b] = di[a] + w;
                }
            }
        }
        if (dist[dst] == 0x3f3f3f3f) {
            return - 1;
        }
        return dist[dst];
    }
}