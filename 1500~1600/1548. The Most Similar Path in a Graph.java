/* 
We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly 3 upper-case English letters given in the string array names. Starting at any city x, you can reach any city y where y != x (i.e. the cities and the roads are forming an undirected connected graph).

You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.

You need to return the order of the nodes in the path with the minimum edit distance, The path should be of the same length of targetPath and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.

The edit distance is defined as follows:



Follow-up: If each node can be visited only once in the path, What should you change in your solution?

 

Example 1:


Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
Output: [0,2,4,2]
Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
[0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
[0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
[0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
Example 2:


Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
Output: [0,1,0,1,0,1,0,1]
Explanation: Any path in this graph has edit distance = 8 with targetPath.
Example 3:



Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
Output: [3,4,5,4,3,2,1]
Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 

Constraints:

2 <= n <= 100
m == roads.length
n - 1 <= m <= (n * (n - 1) / 2)
0 <= ai, bi <= n - 1
ai != bi 
The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
names.length == n
names[i].length == 3
names[i] consists of upper-case English letters.
There can be two cities with the same name.
1 <= targetPath.length <= 100
targetPath[i].length == 3
targetPath[i] consists of upper-case English letters.
 */

class Solution {
    int m, n;
    int[][] dp;
    String[] name, targetPath;
    List<List<Integer>> graph = new ArrayList<>();

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        this.n = n;
        this.m = targetPath.length;
        this.name = names;
        this.targetPath = targetPath;
        this.dp = new int[n][m];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
            graph.add(new ArrayList());
        }
        for (int[] r: roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        int p = -1;
        int minCost = m + 1;
        for (int i = 0; i < n; ++i) {
            int cur = dfs(i, 0);
            if (minCost > cur) {
                minCost = cur;
                p = i;
            }
        }
        List<Integer> path = new ArrayList();
        search(p, 0, path);
        return path;
    }
    
    // List<>: path
    private void search(int idx, int pos, List<Integer> path) {
        if (pos == m) {
            return;
        }
        path.add(idx);
        int cost = name[idx].equals(targetPath[pos]) ? 0 : 1;
        for (int next: graph.get(idx)) {
            if (dfs(idx, pos) == cost + dfs(next, pos + 1)) {
                search(next, pos + 1, path);
                return;
            }
        }
    }
    
    // if put names[idx] at path[pos], return the minimum cost
    private int dfs(int idx, int pos) {
        if (pos == m) {
            return 0;
        }
        if (dp[idx][pos] != -1) {
            return dp[idx][pos];
        }
        int cost = name[idx].equals(targetPath[pos]) ? 0 : 1;
        int ans = m;
        for (int next: graph.get(idx)) {
            ans = Math.min(ans, cost + dfs(next, pos + 1));
        }
        return dp[idx][pos] = ans;
    }
}



class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] r: roads) {
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        int m = targetPath.length;
        int[][] path = new int[m][n];
        Arrays.fill(path[0], -1);
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = targetPath[0].equals(names[i]) ? 0 : 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = m + 1;
                for (int next: graph.get(j)) {
                    int editCost = targetPath[i].equals(names[j]) ? 0 : 1;
                    if (dp[i][j] > dp[i - 1][next] + editCost) {
                        dp[i][j] = dp[i - 1][next] + editCost;
                        path[i][j] = next;
                    }
                }
            }
        }
        int last = -1;
        for (int i = 0, minDist = m + 1; i < n; ++i) {
            if (minDist > dp[m - 1][i]) {
                minDist = dp[m - 1][i];
                last = i;
            }
        }
        List<Integer> ans = new ArrayList(m);
        for (int i = m - 1; last != -1; --i) {
            ans.add(last);
            last = path[i][last];
        }
        Collections.reverse(ans);
        return ans;
    }
}