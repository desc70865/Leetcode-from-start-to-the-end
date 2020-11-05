/* 
There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach the city 0 after reorder.

 

Example 1:



Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:



Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
 

Constraints:

2 <= n <= 5 * 10^4
connections.length == n-1
connections[i].length == 2
0 <= connections[i][0], connections[i][1] <= n-1
connections[i][0] != connections[i][1]
 */

class Solution {
    boolean[] v;
    int[] p;

    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] c: connections) {
            map.get(c[0]).add(c[1]);
            map.get(c[1]).add(c[0]);
        }
        v = new boolean[n];
        v[0] = true;
        p = new int[n];
        Arrays.fill(p, -1);
        dfs(0, map);
        // System.out.println(Arrays.toString(p));
        int res = 0;
        for (int[] c: connections) {
            if (p[c[0]] != c[1]) res++;
        }
        return res;
    }

    private void dfs(int cur, List<List<Integer>> map) {
        for (Integer next: map.get(cur)) {
            if (v[next]) continue;
            v[next] = true;
            p[next] = cur;
            dfs(next, map);
        }
    }
}