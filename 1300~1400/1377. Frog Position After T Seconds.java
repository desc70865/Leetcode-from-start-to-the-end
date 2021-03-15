/* 
Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.

Return the probability that after t seconds the frog is on the vertex target.

 

Example 1:



Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
Output: 0.16666666666666666 
Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after second 1 and then jumping with 1/2 probability to vertex 4 after second 2. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666. 
Example 2:



Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
Output: 0.3333333333333333
Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after second 1. 
Example 3:

Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
Output: 0.16666666666666666
 

Constraints:

1 <= n <= 100
edges.length == n - 1
edges[i].length == 2
1 <= ai, bi <= n
1 <= t <= 50
1 <= target <= n
Answers within 10-5 of the actual value will be accepted as correct.
 */

class Solution {
    int[] list = new int[100];
    int size = 0;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n == 1) return 1;
        if (target == 1) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e: edges) {
            map.computeIfAbsent(e[0], z -> new ArrayList<>()).add(e[1]);
            map.computeIfAbsent(e[1], z -> new ArrayList<>()).add(e[0]);
        }
        dfs(map, 1, target, 0, new boolean[101]);
        if (t < size) return 0;
        if (t > size && map.get(target).size() > 1) return 0;
        double ans = 1.0;
        for (int i = 0; i < size; i++) {
            ans /= map.get(list[i]).size() - (i == 0 ? 0 : 1);
        }
        return ans;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int cur, int target, int idx, boolean[] v) {
        list[idx] = cur;
        v[cur] = true;
        if (cur == target) {
            size = idx;
            return true;
        }
        if (! map.containsKey(cur)) return false;
        for (int next: map.get(cur)) {
            if (v[next]) continue;
            if (dfs(map, next, target, idx + 1, v)) return true;
        }
        return false;
    }
}